package clickcloud.server.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clickcloud.server.dto.BriefWeather;
import clickcloud.server.dto.DetailedWeather;
import clickcloud.server.mybatis.MybatisMapper;
import clickcloud.server.service.WeatherService;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private final MybatisMapper mybatisMapper;
    private final WeatherService weatherService;

    //생성자
    public WeatherController(MybatisMapper mybatisMapper, WeatherService weatherService) {
        this.mybatisMapper = mybatisMapper;
        this.weatherService = weatherService;
    }

    //GET - 전체 날씨 조회 api / 주요 100개 도시만 가져온다. (기존에 저장된 테이블에서 가져오면 됨)
    @GetMapping(value = "/getAllWeather", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BriefWeather> getAllWeather() {
        // 전체 날씨 업데이트 - 1시간마다 해야함(아직 구현X - 날씨데이터 아예 없을 때 첫 생성만 해놓음)
        weatherService.updateAllWeather();
        // DB에서 전체 날씨 정보 데이터 가져오기
        return mybatisMapper.getAll();
    }

    //POST - 세부 날씨 조회 api(검색) - 기존 저장된 데이터 + 오픈웨더api 데이터 중에서 찾는다. 
    @PostMapping(value = "/searchName/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailedWeather searchName(@PathVariable("city_name") String city_name) {

        // 만약 기존 DB에 데이터가 없다면 api로 새로운 데이터 DB에 저장하고 => return문 :  데이터 가져오기
        if(mybatisMapper.searchName(city_name) == null){ //★
            weatherService.getSearchedData(city_name);
        }
        // 만약 기존 DB에 데이터가 있다면 그냥 바로 날씨 세부 정보 데이터 가져오기
        return mybatisMapper.searchName(city_name);
    }
}
