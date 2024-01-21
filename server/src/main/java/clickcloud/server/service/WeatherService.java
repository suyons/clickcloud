package clickcloud.server.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import clickcloud.server.dto.Weather;
import clickcloud.server.mybatis.MybatisMapper;

@Service
public class WeatherService {

    @Autowired
    private WeatherApiService weatherApiService;

    @Autowired
    private MybatisMapper mybatisMapper;

    //전체 도시 날씨 데이터 업데이트------------------------------------------------------
    public void updateAllWeather() {


        //모든 도시 목록 가져오기
        List<Integer> cityIds = mybatisMapper.getCityId();

        for(int city_id : cityIds) {
            //city_id로 날씨 정보 가져오기
            String weatherData = weatherApiService.getWeatherData(city_id);
            saveWeatherDB(weatherData);
        
        }
    }

    // 받아온 날씨 데이터 DB에 저장
    public void saveWeatherDB(String weatherData){
        try {
            //JSON 데이터 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(weatherData); //readTree() : JSON 데이터를 나타내는 문자열을 파싱하여 JsonNode 객체로 변환

            int weather_id = jsonNode.path("sys").path("id").asInt();
            int city_id = jsonNode.path("id").asInt();
            String w_title = jsonNode.path("weather").get(0).path("main").asText();
            String w_description = jsonNode.path("weather").get(0).path("description").asText();
            double temp_now = jsonNode.path("main").path("temp").asDouble();
            double temp_feels = jsonNode.path("main").path("feels_like").asDouble();
            double temp_min = jsonNode.path("main").path("temp_min").asDouble();
            double temp_max = jsonNode.path("main").path("temp_max").asDouble();
            int pressure = jsonNode.path("main").path("pressure").asInt();
            int humidity = jsonNode.path("main").path("humidity").asInt();
            double wind_speed = jsonNode.path("wind").path("speed").asDouble();
            int wind_deg = jsonNode.path("wind").path("deg").asInt();
            Double rain_1h = jsonNode.path("rain").path("1h").asDouble();
            Double snow_1h = jsonNode.path("snow").path("1h").asDouble();
            int cloud = jsonNode.path("clouds").path("all").asInt();
            int sunrise = jsonNode.path("sys").path("sunrise").asInt();
            int sunset = jsonNode.path("sys").path("sunset").asInt();
            int time_update = jsonNode.path("dt").asInt();
            
            //데이터 베이스에 저장하는 로직 
            WeatherDB(weather_id, city_id, w_title, w_description, temp_now, temp_feels, temp_min, temp_max, pressure,
            humidity, wind_speed, wind_deg, rain_1h, snow_1h, cloud, sunrise, sunset, time_update);

        } catch (JsonProcessingException e) {
        // 예외 처리: JSON 데이터 파싱 중에 오류가 발생한 경우
            e.printStackTrace(); 
    }
    
    }

    //mybatis로 db저장
    private void WeatherDB(
        int weather_id, int city_id, String w_title, String w_description, double temp_now, double temp_feels, double temp_min,
        double temp_max, int pressure, int humidity, double wind_speed, int wind_deg, Double rain_1h, Double snow_1h, int cloud,
        int sunrise, int sunset, int time_update){

        //MyBatis 사용해서 DB에 저장
        Weather weather = new Weather();
        
         //sys의 id가 없는 경우가 있다 => weather_id가 중복됨 ㅠ
         if(weather_id != 0){
            weather.setWeather_id(weather_id);
         } else {
            Random random = new Random();
            int randomId = random.nextInt(1000000); // 적절한 범위로 조절
            int randomWeatherId = randomId;
            weather.setWeather_id(randomWeatherId);
            System.out.println("weather_id가 0이므로 랜덤 값을 부여했습니다");
         }
        
        weather.setCity_id(city_id);
        weather.setW_title(w_title);
        weather.setW_description(w_description);
        weather.setTemp_now(temp_now);
        weather.setTemp_feels(temp_feels);
        weather.setTemp_min(temp_min);
        weather.setTemp_max(temp_max);
        weather.setPressure(pressure);
        weather.setHumidity(humidity);
        weather.setWind_speed(wind_speed);
        weather.setWind_deg(wind_deg);
        weather.setRain_1h(rain_1h);
        weather.setSnow_1h(snow_1h);
        weather.setCloud(cloud);
        weather.setSunrise(sunrise);
        weather.setSunset(sunset);
        weather.setTime_update(time_update);
        

        mybatisMapper.insertWeather(weather);

    }
    

    // 특정 도시 날씨 

}
