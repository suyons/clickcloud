package clickcloud.server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import clickcloud.server.dto.*;

@Mapper
public interface MybatisMapper {
	List<BriefWeather> getAllWeather();
    DetailedWeather searchName(@Param("city_name") String city_name);
}