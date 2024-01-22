package clickcloud.server.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class ScheduledService {
    /*
     * cron = "초 분 시 일 월 요일"
     * 매 시각 1분 0초에 실행
     * 초 = * 으로 설정되면 매 시 1분 매 초마다 실행되므로 0으로 설정해야 한다.
     */
    @Scheduled(cron = "0 1 * * * *")
    public void updateWeather1() {
        System.out.println("[Debug] Part 1 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "0 2 * * * *")
    public void updateWeather2() {
        System.out.println("[Debug] Part 2 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "0 3 * * * *")
    public void updateWeather3() {
        System.out.println("[Debug] Part 3 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "0 4 * * * *")
    public void updateWeather4() {
        System.out.println("[Debug] Part 4 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
    
    @Scheduled(cron = "0 5 * * * *")
    public void updateWeather5() {
        System.out.println("[Debug] Part 5 updated at "
         + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    };
}
