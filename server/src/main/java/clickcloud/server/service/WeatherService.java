package clickcloud.server.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

//비즈니스 로직 

@Service
public class WeatherService {

    @Scheduled(fixedRate = 10000) //날씨 업데이트 될때마다 시간 알려줌 
    public void updateWeather() {
        System.out.println("[Debug] Weather updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    }


}