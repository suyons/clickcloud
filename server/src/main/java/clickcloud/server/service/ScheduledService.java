package clickcloud.server.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    @Autowired
    private WeatherService weatherService;

    //1분에 60개 요청할 수 있다. 따라서 매시간 1분 0초가 되는 시점에 한번에 가져오는 게 아니라 
    //100개의 요청을 20개씩 나눠서 5분동안 1분간격으로 업데이트할거임

    // 매 시간마다 업데이트
    @Scheduled(cron = "0 1 * * * *") //
    public void updateWeatherHourly1() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather();
    }

    @Scheduled(cron = "0 2 * * * *") //
    public void updateWeatherHourly2() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather();
    }

    @Scheduled(cron = "0 3 * * * *") //
    public void updateWeatherHourly3() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather();
    }

    @Scheduled(cron = "0 4 * * * *") //
    public void updateWeatherHourly4() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather();
    }

    @Scheduled(cron = "0 5 * * * *") //
    public void updateWeatherHourly5() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather();
    }

}
