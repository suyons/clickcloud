package clickcloud.server.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import clickcloud.server.dto.GlobalWeather;
import clickcloud.server.dto.LocalWeather;
import clickcloud.server.dto.Weather;

import java.lang.Long;

@Mapper
public interface MybatisMapper {
    /**
     * @param timestamp (시간 단위)
     * @return List<GlobalWeather> (WHERE update_time BETWEEN #{timestamp} - 1시간 AND #{timestamp})
     */
    List<GlobalWeather> getAllByTime(@Param("timestamp") Long timestamp);
    List<GlobalWeather> getAllLatest();

    /**
     * @param city_name (영문 입력)
     * @param timestamp (시간 단위)
     * @return LocalWeather (WHERE city_name = #{city_name} AND timestamp AND timestamp + 30분)
     */
    LocalWeather searchName(@Param("city_name") String city_name, @Param("timestamp") Long timestamp);
    
    // 모든 city_id 가져오기
    List<Integer> getAllCityId();

    // OpenWeatherMap에서 가져온 날씨 정보 Weather table에 저장
	void insertWeather(Weather weather);

    // weather 테이블의 weather_id 가져오기
	Integer getWeatherId();

    // 테스트 용도
    String getTest();
}