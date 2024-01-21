DROP TABLE IF EXISTS countries;

CREATE TABLE countries (
    country_id CHAR(2) NOT NULL UNIQUE CHECK (REGEXP_LIKE(country_id, '[A-Z]{2}')),
    country_name VARCHAR(50) NOT NULL UNIQUE,
    continent_id CHAR(2) NOT NULL CHECK (
        continent_id IN ('AF', 'AN', 'AS', 'EU', 'NA', 'OC', 'SA')
    ),
    PRIMARY KEY (country_id)
);

DELETE FROM countries WHERE 1 = 1;

SELECT * FROM countries;