package clickcloud.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import clickcloud.server.dto.BriefWeather;
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
        JsonNode jsonNode = objectMapper.readTree(weatherData);

        //추출한 정보로 전체 날씨 정보 조회
        String cityName = jsonNode.path("name").asText();
        String weatherTitle = jsonNode.path("weather").get(0).path("main").asText();
        double latitude = jsonNode.path("coord").path("lat").asDouble();
        double longitude = jsonNode.path("coord").path("lon").asDouble();

        //데이터 베이스에 저장하는 로직 
        WeatherDB(cityName, weatherTitle, latitude, longitude);

        } catch (JsonProcessingException e) {
        // 예외 처리: JSON 데이터 파싱 중에 오류가 발생한 경우
        e.printStackTrace(); 
    }
    
    }

    //mybatis로 db저장
    private void WeatherDB(String cityName, String weatherTitle, double latitude, double longitude){
        //MyBatis 사용해서 DB에 저장
        BriefWeather briefWeather = new BriefWeather();
        briefWeather.setCity_name(cityName);
        briefWeather.setW_title(weatherTitle);
        briefWeather.setLatitude(latitude);
        briefWeather.setLongitude(longitude);

        mybatisMapper.insertWeather(briefWeather);

    }
    

    // 특정 도시 날씨 

}
