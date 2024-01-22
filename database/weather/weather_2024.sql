DROP TABLE IF EXISTS weather_2024;

DELETE FROM weather_2024 WHERE 1 = 1;

SELECT * FROM weather_2024;

CREATE TABLE weather_2024 (
    weather_id INT NOT NULL UNIQUE AUTO_INCREMENT,
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
);

/*
 * MOCK DATA
 */

INSERT INTO weather_2024
VALUES (
    /* weather_id */ null,
    /* city_id */ 1835848,
    /* w_title */ 'Clear',
    /* w_description */ 'clear sky',
    /* temp_now */ 2.96,
    /* temp_feels */ -0.39,
    /* temp_min */ 2.69,
    /* temp_max */ 3.66,
    /* pressure */ 1012,
    /* humidity */ 83,
    /* wind_speed */ 3.6,
    /* wind_deg */ 310,
    /* rain_1h */ 2.31,
    /* snow_1h */ NULL,
    /* cloud */ 60,
    /* sunrise */ 1705531499,
    /* sunset */ 1705567137,
    /* time_update */ 1705542138
);

INSERT INTO weather_2024
VALUES (
    /* weather_id */ null,
    /* city_id */ 2643743,
    /* w_title */ 'Clear',
    /* w_description */ 'clear sky',
    /* temp_now */ -3.96,
    /* temp_feels */ -7.39,
    /* temp_min */ -5.96,
    /* temp_max */ -2.9,
    /* pressure */ 1000,
    /* humidity */ 77,
    /* wind_speed */ 2.24,
    /* wind_deg */ 6,
    /* rain_1h */ NULL,
    /* snow_1h */ NULL,
    /* cloud */ 5,
    /* sunrise */ 1705564635,
    /* sunset */ 1705595024,
    /* time_update */ 1705557125
);

INSERT INTO weather_2024
VALUES (
    /* weather_id */ null,
    /* city_id */ 524901,
    /* w_title */ 'Clouds',
    /* w_description */ 'broken clouds',
    /* temp_now */ -16.14,
    /* temp_feels */ -22.47,
    /* temp_min */ -19.99,
    /* temp_max */ -13.76,
    /* pressure */ 1017,
    /* humidity */ 97,
    /* wind_speed */ 2.71,
    /* wind_deg */ 143,
    /* rain_1h */ NULL,
    /* snow_1h */ NULL,
    /* cloud */ 58,
    /* sunrise */ 1705556799,
    /* sunset */ 1705584741,
    /* time_update */ 1705557225
);