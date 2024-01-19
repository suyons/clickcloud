DROP DATABASE clickcloud;

CREATE DATABASE clickcloud;

CREATE USER 'clickcloud'@'%' IDENTIFIED BY '{PASSWORD}';

GRANT ALL PRIVILEGES ON clickcloud.* TO 'clickcloud'@'%';

SHOW GRANTS FOR 'clickcloud'@'%';