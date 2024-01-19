DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
    city_id INT NOT NULL UNIQUE,
    country_id CHAR(2) NOT NULL CHECK (REGEXP_LIKE(country_id, '[A-Z]{2}')),
    city_name VARCHAR(50) NOT NULL,
    pos_x DECIMAL(7, 4) NOT NULL,
    pos_y DECIMAL(7, 4) NOT NULL,
    timezone INT NOT NULL,
    PRIMARY KEY (city_id),
    FOREIGN KEY (country_id) REFERENCES countries(country_id)
);

SELECT * FROM cities;

DELETE FROM cities WHERE 1 = 1;

INSERT INTO cities
VALUES (
    /* city_id */ 1835848,
    /* country_id */ 'KR',
    /* name */ 'Seoul',
    /* pos_x */ 37.5683,
    /* pos_y */ 126.9778,
    /* timezone */ 32400);

INSERT INTO cities
VALUES (
    /* city_id */ 2643743,
    /* country_id */ 'GB',
    /* name */ 'London',
    /* pos_x */ -0.1257,
    /* pos_y */ 51.5085,
    /* timezone */ 0
);

INSERT INTO cities
VALUES (
    /* city_id */ 524901,
    /* country_id */ 'RU',
    /* name */ 'Moscow',
    /* pos_x */ 37.6156,
    /* pos_y */ 55.7522,
    /* timezone */ 10800
);