package clickcloud.server.controller;

import java.sql.Time;
import java.time.Instant;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import clickcloud.server.dto.BriefWeather;
import clickcloud.server.mybatis.MybatisMapper;
import clickcloud.server.service.WeatherApiService;
import clickcloud.server.service.WeatherService;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private final MybatisMapper mybatisMapper;
    private final WeatherService weatherService;
    private final WeatherApiService weatherApiService;


    //생성자
    public WeatherController(MybatisMapper mybatisMapper, WeatherService weatherService, WeatherApiService weatherApiService) {
        this.mybatisMapper = mybatisMapper;
        this.weatherService = weatherService;
        this.weatherApiService = weatherApiService;
    }

    //GET - 전체 날씨 조회 api / 주요 100개 도시 (기존에 저장된 테이블에서)
    @GetMapping(value = "/getAllWeather", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BriefWeather> getAllWeather() {
        int currentTime = (int) Instant.now().getEpochSecond(); //현재 시간 (초단위) 가져오기
        if(mybatisMapper.getAll(currentTime) == null){ //데이터 dt가 현재 시간보다 한시간 전에 저장된 
            weatherService.firstUpdateWeather();
        }
        // DB에 이미 정보 있으면 가장 최신 정보(time_update사용) 조회
        return mybatisMapper.getAll(currentTime);
    }

    //POST - 특정도시 세부 날씨 조회 api(검색) - 기존 저장된 데이터 + 오픈웨더api 데이터 중에서 찾는다. => 근데 GET도 잘됨
    @PostMapping(value = "/searchName/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getDataByName(@PathVariable("city_name") String city_name) {

        // 만약 기존 DB에 데이터가 있다면  데이터 불러오기
        if(mybatisMapper.searchName(city_name) != null){ 
           return mybatisMapper.searchName(city_name);
        }  // 없다면 api로 불러와서 보여주기
        String weatherData = weatherApiService.getWeatherByName(city_name); //api로 날씨데이터 받아오기

        try {
            return weatherService.getWeatherByName(weatherData);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }
}
