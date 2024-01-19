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

@RestController
@RequestMapping("/api")
public class WeatherController {
    private final MybatisMapper mybatisMapper;

    //생성자
    public WeatherController(MybatisMapper mybatisMapper) {
        this.mybatisMapper = mybatisMapper;
    }

    //GET - 전체 날씨 조회 api / 주요 100개 도시만 가져온다. (기존에 저장된 테이블에서 가져오면 됨)
    @GetMapping(value = "/getAllWeather", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BriefWeather> getAllWeather() {
        return mybatisMapper.getAllWeather();
    }

    //POST - 세부 날씨 조회 api(검색) - 기존 저장된 데이터 + 오픈웨더api 데이터 중에서 찾는다. 
    //바디파라미터..?@ResponseBody
    @PostMapping(value = "/searchName/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailedWeather searchName(@PathVariable("city_name") String city_name) {
        return mybatisMapper.searchName(city_name);
    }
}
