package clickcloud.server.service;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// import java.text.SimpleDateFormat;

@Service
public class WeatherApiService {
    @Value("${weather.apikey}")
    private String apiKey;

    public String getWeatherData(int cityId) {
        // OpenWeatherMap API 엔드포인트 URL
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&appid=" + apiKey;

        //API 호출
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        //JSON 데이터 출력(테스트용)
        System.out.println(response);

        return response;
    }
}