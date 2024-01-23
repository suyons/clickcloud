package clickcloud.server.dto;

// Lombok: getter, setter 등 자동으로 생성
import lombok.Data;

@Data
public class GlobalWeather {
    private String city_name;
    private String w_title;
    private double latitude;
    private double longitude;
}
