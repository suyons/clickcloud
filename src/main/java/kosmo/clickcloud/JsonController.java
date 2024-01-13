package kosmo.clickcloud;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class JsonController {
    @GetMapping("/weather")
    public String getGlobalWeatherString() {
        return new String();
    }
    
}
