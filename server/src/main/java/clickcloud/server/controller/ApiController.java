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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiController {
    // MybatisMapper 인터페이스는 new를 통해 객체화할 수 없으므로 @Autowired를 통한 의존성 주입 불가능
    private final MybatisMapper mybatisMapper;

    public ApiController(MybatisMapper mybatisMapper) {
        this.mybatisMapper = mybatisMapper;
    }

    /**
     * @function globalWeather
     * @param Integer timestamp
     * @return timestamp가 null이면 현재 시간 기준으로 30분 전부터 30분 후까지의 모든 도시의 날씨 정보를 반환
     * @return timestamp가 null이 아니면 timestamp 기준으로 1시간 전까지의 모든 도시의 날씨 정보를 반환
     * @test GET /api/global?time=1705542420
     */
    @GetMapping(value = "/global", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GlobalWeather> globalWeather(@RequestParam(name = "time", required = false) Integer timestamp) {
        return timestamp == null
                ? mybatisMapper.getAllByTime(System.currentTimeMillis() / 1000)
                : mybatisMapper.getAllByTime(timestamp);
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
    public LocalWeather localWeather(@RequestParam(name = "name", required = true) String city_name,
            @RequestParam(name = "time", required = false) Integer timestamp) {
        return timestamp == null
                ? mybatisMapper.searchName(city_name, System.currentTimeMillis() / 1000)
                : mybatisMapper.searchName(city_name, timestamp);
    }
}
