package clickcloud.server.dto;

import lombok.Data;

@Data
public class GlobalWeather {
    private String city_name;
    private String w_title;
    private double latitude;
    private double longitude;

    // Lombok을 통해 getter, setter, toString 등을 자동으로 생성
}
