/*
    Get all weather data by time
    @param clientTimestamp: timestamp from client
    @return GLOBAL WEATHER DATA AT THE TIME
*/

-- 1. 프로시저가 이미 존재하는 경우 삭제
DROP PROCEDURE IF EXISTS GetAllByTime;

-- 2. 프로시저 생성
CREATE PROCEDURE GetAllByTime(IN clientTimestamp INT)
BEGIN
    SET @sql = CONCAT('SELECT DISTINCT city_name, w_title, latitude, longitude 
        FROM weather_', YEAR(FROM_UNIXTIME(clientTimestamp)), ' JOIN cities USING (city_id) 
        JOIN countries USING (country_id)'
        , ' WHERE time_update BETWEEN ', clientTimestamp - 3600, ' AND ', clientTimestamp);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END

-- 3. 프로시저 실행 테스트
CALL GetAllByTime(1705542420);

/*
    Search weather data by city name and time
    @param cityName: city name
    @param clientTimestamp: timestamp from client
    @return LOCAL WEATHER DATA AT THE TIME
*/

-- 1. 프로시저가 이미 존재하는 경우 삭제
DROP PROCEDURE IF EXISTS SearchNameByTime;

-- 2. 프로시저 생성
CREATE PROCEDURE SearchNameByTime(IN cityName VARCHAR(50), IN clientTimestamp INT)
BEGIN
    SET @sql = CONCAT('SELECT DISTINCT city_name, country_name, w_title, w_description
        , temp_now, temp_feels, temp_min, temp_max, pressure, humidity
        , wind_speed, wind_deg, rain_1h, snow_1h, cloud, sunrise, sunset
        , time_update, timezone FROM weather_', YEAR(FROM_UNIXTIME(clientTimestamp))
        , ' JOIN cities USING (city_id) JOIN countries USING (country_id)'
        , "WHERE city_name = '", cityName, "' AND time_update BETWEEN "
        , clientTimestamp - 3600, ' AND ', clientTimestamp);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END

-- 3. 프로시저 실행 테스트
CALL SearchNameByTime('Seoul', 1705542420);