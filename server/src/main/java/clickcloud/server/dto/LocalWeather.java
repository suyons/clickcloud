package clickcloud.server.dto;

// Lombok: getter, setter 등 자동으로 생성
import lombok.Data;

@Data
public class LocalWeather {
    private String city_name;
    private String country_name;
    private double latitude;
    private double longitude;
    private String w_title;
    private String w_description;
    private double temp_now;
    private double temp_feels;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private double wind_speed;
    private int wind_deg;
    private Double rain_1h;
    private Double snow_1h;
    private int cloud;
    private int sunrise;
    private int sunset;
    private int time_update;
    private int timezone;
}
