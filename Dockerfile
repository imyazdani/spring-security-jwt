FROM openjdk:17-jdk-slim
RUN mkdir -p /app
COPY target/*.jar /app/spring-security-jwt.jar
EXPOSE 8080
WORKDIR /app/
CMD ["java","-jar","spring-security-jwt.jar"]