CREATE DATABASE CLICKCLOUD;

CREATE USER 'clickcloud'@'%' IDENTIFIED BY '{PASSWORD}';

GRANT ALL PRIVILEGES ON CLICKCLOUD.* to 'clickcloud'@'%';

SHOW GRANTS FOR 'clickcloud'@'%';