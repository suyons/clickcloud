package clickcloud.server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import clickcloud.server.dto.*;

@Mapper
public interface MybatisMapper {
    @Select("SELECT city_name, w_title, latitude, longitude FROM weather JOIN cities USING (city_id)")
	List<BriefWeather> getAllWeather();
    @Select("SELECT city_name, country_name, w_title, w_description, temp_now, temp_feels, temp_min, temp_max, pressure, humidity, wind_speed, wind_deg, rain_1h, snow_1h, cloud, sunrise, sunset, time_update, timezone FROM weather JOIN cities USING (city_id) JOIN countries USING (country_id) WHERE city_name = #{city_name}")
    DetailedWeather searchName(@Param("city_name") String city_name);
}