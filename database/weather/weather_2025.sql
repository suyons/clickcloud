-- event scheduler ON (ROOT 계정으로 실행)
SET GLOBAL event_scheduler = ON;

-- 2025-01-01 00:00:00에 실행되는 이벤트 생성

CREATE EVENT create_weather_table_2025
ON SCHEDULE AT '2025-01-01 00:00:00'
DO
BEGIN
    SET @sql = 'CREATE TABLE weather_2025 (
        weather_id INT NOT NULL UNIQUE,
        city_id INT NOT NULL UNIQUE,
        w_title VARCHAR(20) NOT NULL,
        w_description VARCHAR(50) NOT NULL,
        temp_now DECIMAL(4, 2) NOT NULL,
        temp_feels DECIMAL(4, 2) NOT NULL,
        temp_min DECIMAL(4, 2) NOT NULL,
        temp_max DECIMAL(4, 2) NOT NULL,
        pressure INT NOT NULL,
        humidity SMALLINT NOT NULL,
        wind_speed DECIMAL(4, 2) NOT NULL,
        wind_deg SMALLINT NOT NULL,
        rain_1h DECIMAL(4, 2),
        snow_1h DECIMAL(4, 2),
        cloud SMALLINT NOT NULL,
        sunrise INT NOT NULL,
        sunset INT NOT NULL,
        time_update INT NOT NULL,
        PRIMARY KEY (weather_id),
        FOREIGN KEY (city_id) REFERENCES cities(city_id)
    )';

    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END;
