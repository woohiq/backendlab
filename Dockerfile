# 🔧 1단계: Maven 빌드용 이미지 사용 (권한 문제 없음)
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 작업 디렉토리 설정
WORKDIR /app

# 전체 프로젝트 복사
COPY . .

# Maven 빌드 실행 (mvn 사용 → mvnw 아님!)
RUN mvn clean package -DskipTests

# 🚀 2단계: 실행용 Java 이미지를 사용
FROM eclipse-temurin:21-jdk

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 복사
COPY --from=build /app/target/*.jar app.jar

# JAR 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
