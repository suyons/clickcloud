package clickcloud.server.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class WeatherService {
    @Scheduled(cron = "0 * * * * *")
    public void updateWeather1() {
        System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "10 * * * * *")
    public void updateWeather2() {
        System.out.println("[Debug] Part 2 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "20 * * * * *")
    public void updateWeather3() {
        System.out.println("[Debug] Part 3 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "30 * * * * *")
    public void updateWeather4() {
        System.out.println("[Debug] Part 4 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "40 * * * * *")
    public void updateWeather5() {
        System.out.println("[Debug] Part 5 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "50 * * * * *")
    public void updateWeather6() {
        System.out.println("[Debug] Part 6 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
}