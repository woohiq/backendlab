# Docker base image (OpenJDK 21 기준)
FROM eclipse-temurin:21-jdk

# JAR 파일을 컨테이너로 복사
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]
