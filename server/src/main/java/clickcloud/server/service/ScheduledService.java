package clickcloud.server.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    @Autowired
    private WeatherService weatherService;

    // 매 시간마다 업데이트
    // @Scheduled(cron = "0 * * * * *")
    public void updateWeatherHourly() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather();
    }
}
