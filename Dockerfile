FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/loja-virtual-bff-0.0.1-SNAPSHOT.jar /app/loja-virtual-bff.jar

EXPOSE 8484

CMD ["java", "-jar", "/app/loja-virtual-bff.jar"]