package clickcloud.server.dto;

import lombok.Data;

//DTO

@Data
public class BriefWeather {
    private String city_name;
    private String w_title;
    private double latitude;
    private double longitude;
}
