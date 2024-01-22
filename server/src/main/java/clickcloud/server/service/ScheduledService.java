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

    // 매 시간 1분마다 20개 요청
    @Scheduled(cron = "0 54 * * * *") //
    public void updateWeatherHourly1() {
         System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather(0); //0~19
        System.out.println("20개 업데이트 완료");
    }

    // 매 시간 2분마다 20개 요청
    @Scheduled(cron = "0 55 * * * *") //
    public void updateWeatherHourly2() {
         System.out.println("[Debug] Part 2 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather(20);  //20~39
        System.out.println("20개 업데이트 완료");
    }

    // 매 시간 3분마다 20개 요청
    @Scheduled(cron = "0 56 * * * *") //
    public void updateWeatherHourly3() {
         System.out.println("[Debug] Part 3 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather(40); //40 ~ 59
        System.out.println("20개 업데이트 완료");
    }

    // 매 시간 3분마다 20개 요청
    @Scheduled(cron = "0 57 * * * *") //
    public void updateWeatherHourly4() {
         System.out.println("[Debug] Part 4 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather(60); //60 ~ 79
        System.out.println("20개 업데이트 완료");
    }

    // 매 시간 4분마다 20개 요청
    @Scheduled(cron = "0 58 * * * *") //
    public void updateWeatherHourly5() {
         System.out.println("[Debug] Part 5 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
         
        weatherService.updateAllWeather(80); //80 ~ 99
        System.out.println("20개 업데이트 완료");
    }

}
