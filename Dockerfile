# 베이스 이미지
FROM amazoncorretto:17

# 빌드 인수
ARG JAR_FILE=build/libs/dart-0.0.1-SNAPSHOT.jar

# 애플리케이션 JAR 파일 복사
COPY ${JAR_FILE} /dart.jar

# 애플리케이션 설정 파일 복사
COPY src/main/resources/application-dev.yml /config/application-dev.yml

# ENTRYPOINT 설정
ENTRYPOINT ["java", "-Dspring.config.location=file:/config/application-dev.yml", "-Dspring.profiles.active=dev", "-jar", "/dart.jar"]
