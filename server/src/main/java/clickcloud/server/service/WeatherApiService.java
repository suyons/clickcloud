package clickcloud.server.service;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// import java.text.SimpleDateFormat;

@Service
public class WeatherApiService {
    @Value("${weather.apikey}")
    private String apiKey;

    //cityId로 url 생성
    public String getWeatherToId(int cityId) {
        // OpenWeatherMap API 엔드포인트 URL
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?units=metric&id=" + cityId + "&appid=" + apiKey;

        try {
            // API 호출 - apiUrl GET 요청을 보내고, 해당 요청에 대한 응답을 문자열로 받음
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);

            // JSON 데이터 출력(테스트용)
            System.out.println(response);

            return response;
        } catch (RestClientException e) {
            // API 호출 실패 시 예외 처리
            e.printStackTrace();
            return null; // 또는 예외 처리에 맞게 반환값 설정
        }
    }

    //cityName으로 url 생성
    public String getDataToName(String cityName){
        // OpenWeatherMap API 엔드포인트 URL
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=" + cityName + "&appid=" + apiKey;

        try {
            // API 호출 - cityName GET 요청을 보내고, 해당 요청에 대한 응답을 문자열로 받음
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);

            // JSON 데이터 출력(테스트용)
            System.out.println(response);

            return response;
        } catch (RestClientException e) {
            // API 호출 실패 시 예외 처리
            e.printStackTrace();
            return null; // 또는 예외 처리에 맞게 반환값 설정
        }
    }
}