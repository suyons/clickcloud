/*
 * MySQl 프로시저 또는 Java 메서드를 정의하기 이전에
 * 먼저 SELECT 쿼리를 작성하여 정상적으로 동작하는지 확인한다.
*/

-- 테이블에 저장된 모든 도시의 날씨 제목과 좌표 불러오기
SELECT city_name,
    w_title,
    latitude,
    longitude
FROM weather_2024
    JOIN cities USING (city_id);

/*
 * 도시 이름으로 검색하면 해당 도시의 모든 정보 불러오기
 * 
 * 시간 정보는 시차가 이미 반영된 값으로 저장되어 있으므로
 * timezone을 더할 필요가 없다.
*/
SELECT
    /* 위치 */
    city_name,
    country_name,
    /* 날씨 */
    w_title,
    w_description,
    temp_now,
    temp_feels,
    temp_min,
    temp_max,
    pressure,
    humidity,
    wind_speed,
    wind_deg,
    rain_1h,
    snow_1h,
    cloud,
    /* 시간 */
    sunrise,
    sunset,
    time_update,
    timezone
FROM weather_2024
    JOIN cities USING (city_id)
    JOIN countries USING (country_id)
WHERE city_name = 'Seoul';


SELECT DISTINCT city_name, w_title, latitude, longitude
FROM weather_2024
    JOIN cities USING (city_id)
    JOIN countries USING (country_id)
ORDER BY time_update DESC
LIMIT 100;