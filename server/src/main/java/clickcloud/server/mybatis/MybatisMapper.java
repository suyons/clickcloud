package clickcloud.server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import clickcloud.server.dto.*;

//DB에 있던 데이터를 불러옴

@Mapper
public interface MybatisMapper {
    // 100개 도시 데이터 불러오기
    List<BriefWeather> getAll(int currentTime); 

    //모든 city_id 가져오기
    List<Integer> getAllCityId();

    // 특정 도시 상세 날씨 정보 불러오기
    DetailedWeather searchName(@Param("city_name") String city_name);

    // 오픈웨더에서 가져온 날씨 정보 Weather table에 저장 
    void updateWeather(Weather weather);

    //weather 테이블의 weather_id가져오기 
    Integer getWeatherId();

}