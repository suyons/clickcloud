package clickcloud.server.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private Map<String, Weather> weatherMap = new HashMap<>();
    
    @Scheduled(fixedRate = 3600000)
    public void updateWeather() {
        System.out.println("Updating weather...");
    }
}
