package clickcloud.server.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import clickcloud.server.dto.GlobalWeather;
import clickcloud.server.dto.LocalWeather;
import clickcloud.server.mybatis.MybatisMapper;
import clickcloud.server.service.WeatherApiService;
import clickcloud.server.service.WeatherService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiController {
    private final MybatisMapper mybatisMapper;
    private final WeatherApiService weatherApiService;
    private final WeatherService weatherService;

    // @Autowired를 통한 의존성 주입은 불가능하므로 생성자를 통해 의존성 주입
    public ApiController(MybatisMapper mybatisMapper, WeatherService weatherService,
            WeatherApiService weatherApiService) {
        this.mybatisMapper = mybatisMapper;
        this.weatherApiService = weatherApiService;
        this.weatherService = weatherService;
    }

    /**
     * @function globalWeather
     * @param Integer timestamp
     * @return timestamp가 null이면 현재 시간 기준으로 1시간 전까지의 모든 도시의 날씨 정보를 반환
     * @return timestamp가 null이 아니면 timestamp 기준으로 1시간 전까지의 모든 도시의 날씨 정보를 반환
     * @test POST /api/global?time=1705542420
     */
    @PostMapping(value = "/global", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GlobalWeather> globalWeather(@RequestParam(name = "time", required = false) Long timestamp) {
        System.out.println("[Debug] POST global / time: " + timestamp);
        List<GlobalWeather> weatherList = mybatisMapper.getAllByTime(timestamp != null ? timestamp : System.currentTimeMillis() / 1000);
        if(weatherList.size() == 0) {
            weatherList = mybatisMapper.getAllLatest();
        }
        return weatherList;
    }

    /**
     * @function localWeather
     * @param String  city_name
     * @param Integer timestamp
     * @return timestamp가 null이면 현재 시간 기준으로 1시간 전까지의 해당 도시의 날씨 정보를 반환
     * @return timestamp가 null이 아니면 timestamp 기준으로 1시간 전까지의 해당 도시의 날씨 정보를 반환
     * @test POST /api/local?name=seoul&time=1705542420
     */
    @PostMapping(value = "/local", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object localWeather(@RequestParam(name = "name", required = true) String city_name,
            @RequestParam(name = "time", required = false) Long timestamp) {
        System.out.println("[Debug] POST local / name: " + city_name + ", time: " + timestamp);
        // DB에서 도시명&시간 검색
        LocalWeather localWeather = mybatisMapper.searchName(city_name,
                timestamp != null ? timestamp : System.currentTimeMillis() / 1000);
        // 검색 결과가 없으면 OpenWeatherMap에서 가져와서 해당 지역의 현재 날씨를 DB에 저장
        if (localWeather == null)
            return weatherService.parseJson(weatherApiService.getWeatherByName(city_name));
        // 검색 결과가 있다면 해당 지역의 지정한 시각 날씨를 반환
        return localWeather;
    }
}
