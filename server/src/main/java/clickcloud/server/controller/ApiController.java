package clickcloud.server.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	// @Autowired를 통한 의존성 주입은 불가능하므로 생성자를 통해 의존성 주입
	public ApiController(MybatisMapper mybatisMapper, WeatherService weatherService,
			WeatherApiService weatherApiService) {
		this.mybatisMapper = mybatisMapper;
		this.weatherApiService = weatherApiService;
	}

	/**
	 * @function globalWeather
	 * @param Integer timestamp
	 * @return timestamp가 null이면 현재 시간 기준으로 1시간 전까지의 모든 도시의 날씨 정보를 반환
	 * @return timestamp가 null이 아니면 timestamp 기준으로 1시간 전까지의 모든 도시의 날씨 정보를 반환
	 * @test POST /api/global?time=1705542420
	 */
	@PostMapping(value = "/global", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GlobalWeather> globalWeather(@RequestParam(name = "time", required = false) Integer timestamp) {
		return mybatisMapper.getAllByTime(timestamp != null ? timestamp : System.currentTimeMillis() / 1000);
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
			@RequestParam(name = "time", required = false) Integer timestamp) {
		// 도시 이름 검색 먼저 하고, 없으면 OpenWeatherMap에서 시간 검색하기
		LocalWeather localweather = mybatisMapper.searchName(city_name, timestamp != null ? timestamp : System.currentTimeMillis() / 1000);
		if(localweather != null)
			return localweather;
		else
			return weatherApiService.getWeatherByName(city_name);
	}

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String mysqlTest() {
        String test = mybatisMapper.getTest();
        System.out.println("[Debug] COUNT: " + test);
        return test;
    }
}
