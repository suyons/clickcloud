DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
    city_id INT NOT NULL UNIQUE,
    country_id CHAR(2) NOT NULL CHECK (REGEXP_LIKE(country_id, '[A-Z]{2}')),
    city_name VARCHAR(50) NOT NULL,
    latitude DECIMAL(7, 4) NOT NULL,
    longitude DECIMAL(7, 4) NOT NULL,
    timezone INT NOT NULL,
    PRIMARY KEY (city_id),
    FOREIGN KEY (country_id) REFERENCES countries(country_id)
);

SELECT * FROM cities;

DELETE FROM cities WHERE 1 = 1;