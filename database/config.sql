-- 데이터베이스 clickcloud가 이미 존재한다면 삭제
DROP DATABASE IF EXISTS clickcloud;

-- 데이터베이스 clickcloud 생성
CREATE DATABASE clickcloud;

/*
 * USER 'clickcloud' 생성
 * '%': 모든 호스트에서 접근을 허용
 * 로컬 환경에서는 Public 상태로 설정했으나,
 * 클라우드 배포 시에는 특정 IP만 접근을 허용하는 것이 좋음
 * AWS RDS - 보안 그룹 규칙 - 인바운드 규칙에서 특정 IP만 접근을 허용할 수 있음
 */
CREATE USER 'clickcloud'@'%' IDENTIFIED BY '{PASSWORD}';

-- USER 'clickcloud'에게 clickcloud 데이터베이스의 모든 권한 부여
GRANT ALL PRIVILEGES ON clickcloud.* TO 'clickcloud'@'%';

-- USER 'clickcloud'의 권한 확인
SHOW GRANTS FOR 'clickcloud'@'%';

/*
 * weather_2025 테이블 생성 이전에 event_scheduler 파라미터를 ON으로 변경해야 함
 * AWS RDS에서 제공하는 계정은 SUPER 권한이 없어서 변경 불가
 * RDS의 파라미터 그룹에서 event_scheduler를 ON으로 변경해야 함
 */
SET GLOBAL event_scheduler = ON;