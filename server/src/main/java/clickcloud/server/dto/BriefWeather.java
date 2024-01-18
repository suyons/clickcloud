package clickcloud.server.dto;

import lombok.Data;

@Data
public class BriefWeather {
    private String city_name;
    private String w_title;
    private double pos_x;
    private double pos_y;
}
