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

    public WeatherController(MybatisMapper mybatisMapper) {
        this.mybatisMapper = mybatisMapper;
    }

    @GetMapping(value = "/getAllWeather", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BriefWeather> getAllWeather() {
        return mybatisMapper.getAllWeather();
    }

    @PostMapping(value = "/searchName/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailedWeather searchName(@PathVariable("city_name") String city_name) {
        return mybatisMapper.searchName(city_name);
    }
}
