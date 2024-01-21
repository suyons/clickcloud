package clickcloud.server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import clickcloud.server.dto.*;

//DB에 있던 데이터를 불러옴

@Mapper
public interface MybatisMapper {
    // 여러 도시의 간단한 날씨 정보 데이터를 불러온다 (XML 파일에서 SQL이 정의됨)
    List<BriefWeather> getAll();

    // 특정 도시 이름을 파라미터로 받아 해당 도시의 상세 날씨 정보를 조회한다 (XML 파일에서 SQL이 정의됨)
    DetailedWeather searchName(@Param("city_name") String city_name);

    // 오픈웨더에서 가져온 날씨 정보 DB에 저장
    void insertWeather(BriefWeather briefWeather);

    //모든 city_id 가져오기
    List<Integer> getCityId();
}