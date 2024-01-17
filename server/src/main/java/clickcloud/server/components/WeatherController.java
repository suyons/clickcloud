package clickcloud.server.components;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class WeatherController {
    @GetMapping("/weather")
    public String getGlobalWeatherString() {
        return "";
    }
    
    @PostMapping("/search")
    public String postCity(@RequestBody String city) {
        return "";
    }
    
}
