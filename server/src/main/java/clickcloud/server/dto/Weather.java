package clickcloud.server.dto;

import lombok.Data;

//DTO

@Data
public class Weather {
    int weather_id;
    int city_id; 
    String w_title;
    String w_description;
    double temp_now;
    double temp_feels;
    double temp_min;
    double temp_max; 
    int pressure;
    int humidity;
    double wind_speed;
    int wind_deg;
    Double rain_1h;
    Double snow_1h;
    int cloud;
    int sunrise;
    int sunset;
    int time_update;
}
