# Menggunakan image OpenJDK untuk aplikasi berbasis Java
FROM openjdk:17-jdk-slim as build

# Mengatur direktori kerja
WORKDIR /app

# Menyalin file pom.xml dan source code ke dalam container
COPY pom.xml .
COPY src ./src

# Menjalankan perintah untuk build aplikasi dengan Maven
RUN ./mvnw clean package -DskipTests

# Menggunakan image OpenJDK lagi untuk menjalankan aplikasi
FROM openjdk:17-jdk-slim

# Menyalin file JAR hasil build ke dalam container
COPY --from=build /app/target/my-springboot-app-0.0.1-SNAPSHOT.jar /app/my-springboot-app.jar

# Menetapkan port yang akan dibuka di container
EXPOSE 8080

# Perintah untuk menjalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "/app/my-springboot-app.jar"]
