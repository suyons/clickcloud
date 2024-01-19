package clickcloud.server.dto;

import lombok.Data;

@Data
public class DetailedWeather {
    private String city_name;
    private String country_name;
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
    private String sunrise;
    private String sunset;
    private String time_update;
    private String timezone;
}
