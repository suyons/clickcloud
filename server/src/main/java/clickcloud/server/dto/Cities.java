package clickcloud.server.dto;

import lombok.Data;

//DTO

@Data
public class Cities {
    int city_id;
    String country_id;
    String city_name;
    double latitude;
    double longitude;
    int timezone;
}
