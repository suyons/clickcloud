package clickcloud.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import clickcloud.server.dto.Weather;
import clickcloud.server.mybatis.MybatisMapper;

@Service
public class WeatherService {

	@Autowired
	private WeatherApiService weatherApiService;

	@Autowired
	private MybatisMapper mybatisMapper;

	// 전체 도시 20개씩 DB에 업데이트
	public void updateAllWeather(int n) {
		// 모든 도시 아이디(100개)
		List<Integer> cityIds = mybatisMapper.getAllCityId();

		// 도시 목록 n ~ n + 20 개의 id 가져오기
		for (int i = n; i < n + 20; i++) {
			// city_id로 날씨 정보 가져오기
			String weatherData = weatherApiService.getWeatherById(cityIds.get(i));
			// 날씨 테이블에 저장하기
			parse_before_insert(weatherData);
		}
	}

	// 특정 도시의 날씨 데이터에서 필요한 데이터만 JSON형식으로 불러오기
	public String parseJson(String weatherData) {
		try {
			// JSON 데이터 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			// readTree() : JSON 데이터를 나타내는 문자열을 파싱하여 JsonNode 객체로 변환
			JsonNode jNode = objectMapper.readTree(weatherData);

			String city_name = jNode.path("name").asText();
			String country_name = jNode.path("sys").path("country").asText();
			String w_title = jNode.path("weather").get(0).path("main").asText();
			String w_description = jNode.path("weather").get(0).path("description").asText();
			double temp_now = jNode.path("main").path("temp").asDouble();
			double temp_feels = jNode.path("main").path("feels_like").asDouble();
			double temp_min = jNode.path("main").path("temp_min").asDouble();
			double temp_max = jNode.path("main").path("temp_max").asDouble();
			int pressure = jNode.path("main").path("pressure").asInt();
			int humidity = jNode.path("main").path("humidity").asInt();
			double wind_speed = jNode.path("wind").path("speed").asDouble();
			int wind_deg = jNode.path("wind").path("deg").asInt();
			Double rain_1h = jNode.path("rain").path("1h").asDouble();
			Double snow_1h = jNode.path("snow").path("1h").asDouble();
			int cloud = jNode.path("clouds").path("all").asInt();
			int sunrise = jNode.path("sys").path("sunrise").asInt();
			int sunset = jNode.path("sys").path("sunset").asInt();
			int time_update = jNode.path("dt").asInt();
			int timezone = jNode.path("timezone").asInt();

			JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
			ObjectNode jsonNode = nodeFactory.objectNode();

			jsonNode.put("city_name", city_name);
			jsonNode.put("country_name", country_name);
			jsonNode.put("w_title", w_title);
			jsonNode.put("w_description", w_description);
			jsonNode.put("temp_now", temp_now);
			jsonNode.put("temp_feels", temp_feels);
			jsonNode.put("temp_min", temp_min);
			jsonNode.put("temp_max", temp_max);
			jsonNode.put("pressure", pressure);
			jsonNode.put("humidity", humidity);
			jsonNode.put("wind_speed", wind_speed);
			jsonNode.put("wind_deg", wind_deg);
			jsonNode.put("rain_1h", rain_1h);
			jsonNode.put("snow_1h", snow_1h);
			jsonNode.put("cloud", cloud);
			jsonNode.put("sunrise", sunrise);
			jsonNode.put("sunset", sunset);
			jsonNode.put("time_update", time_update);
			jsonNode.put("timezone", timezone);

			// JsonNode를 문자열로 변환
			String jsonString = objectMapper.writeValueAsString(jsonNode);
            return jsonString;
			// return jsonNode;
		} catch (JsonProcessingException e) {
			// 예외 처리: JSON 데이터 파싱 중에 오류가 발생한 경우
			e.printStackTrace();
			return null;
		}
	}

	// 받아온 날씨 데이터 DB에 저장
	public void parse_before_insert(String weatherData) {
		try {
			// JSON 데이터 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(weatherData); // readTree() : JSON 데이터를 나타내는 문자열을 파싱하여 JsonNode
																	// 객체로 변환

			// weather_id는 기존 테이블의 id 가져오기
			// Integer weather_id = mybatisMapper.getWeatherId();

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

			// 데이터 베이스에 저장 - Weather 테이블
			insert_weather(null, city_id, w_title, w_description, temp_now, temp_feels, temp_min, temp_max, pressure,
					humidity, wind_speed, wind_deg, rain_1h, snow_1h, cloud, sunrise, sunset, time_update);

		} catch (JsonProcessingException e) {
			// 예외 처리: JSON 데이터 파싱 중에 오류가 발생한 경우
			e.printStackTrace();
		}

	}

	// api로 받아온 weather 데이터 DB에 저장
	private void insert_weather(Integer weather_id, int city_id, String w_title, String w_description, double temp_now,
			double temp_feels, double temp_min, double temp_max, int pressure, int humidity, double wind_speed,
			int wind_deg, Double rain_1h, Double snow_1h, int cloud, int sunrise, int sunset, int time_update) {

		// MyBatis 사용해서 DB에 저장
		Weather weather = new Weather();

		// 기존 테이블에 weather_id null이면 1반환 null아니면 +1 반환
		// if (weather_id == null) {
		// 	weather.setWeather_id(1);
		// 	System.out.println("weather_id가 0이므로 1을 부여했습니다");
		// } else {
		// 	weather_id += 1;
		// 	weather.setWeather_id(weather_id);
		// }
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
}
