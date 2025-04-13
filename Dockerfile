# Java 21 base image
FROM eclipse-temurin:21-jdk AS build

# 프로젝트 파일 복사
COPY . /app
WORKDIR /app

# Maven 빌드 실행 (target/*.jar 생성)
RUN ./mvnw clean package -DskipTests

# ------------------------
# 실제 실행용 이미지로 옮기기
FROM eclipse-temurin:21-jdk

# build 단계에서 생성한 JAR 복사
COPY --from=build /app/target/*.jar app.jar

# 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
