package clickcloud.server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import clickcloud.server.dto.*;

@Mapper
public interface MybatisMapper {
    /**
     * @function getAll
     * @param timestamp (시간 단위)
     * @return List<BriefWeather> (WHERE update_time BETWEEN #{timestamp} AND #{timestamp} + 30분)
     */
    @Select("CALL GetAllByTime(#{timestamp})")
    List<GlobalWeather> getAllByTime(@Param("timestamp") long timestamp);

    /**
     * @function search
     * @param city_name (영문 입력)
     * @param timestamp (시간 단위)
     * @return DetailedWeather (WHERE city_name = #{city_name} AND timestamp AND timestamp + 30분)
     */
    @Select("CALL SearchNameByTime(#{city_name}, #{timestamp})")
    LocalWeather searchName(@Param("city_name") String city_name, @Param("timestamp") long timestamp);
}