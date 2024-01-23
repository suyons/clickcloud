package clickcloud.server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import clickcloud.server.dto.*;

@Mapper
public interface MybatisMapper {
    /**
     * @param timestamp (시간 단위)
     * @return List<GlobalWeather> (WHERE update_time BETWEEN #{timestamp} - 1시간 AND #{timestamp})
     */
    List<GlobalWeather> getAllByTime(@Param("timestamp") long timestamp);

    /**
     * @param city_name (영문 입력)
     * @param timestamp (시간 단위)
     * @return LocalWeather (WHERE city_name = #{city_name} AND timestamp AND timestamp + 30분)
     */
    LocalWeather searchName(@Param("city_name") String city_name, @Param("timestamp") long timestamp);
    
    List<Integer> getAllCityId();

	void insertWeather(Weather weather);

	Integer getWeatherId();

    String getTest();
}