FROM openjdk:8u151

# Usuario para java, se evita root
RUN groupadd -r java && useradd --no-log-init -r -g java java
USER java

# Copia del ejecutable
COPY build/libs/*.jar /opt/app.jar

# Puertoa exponer
EXPOSE 8080

# ENV SPRING_PROFILES_ACTIVE=container

# Ejecutamos la aplicación al iniciar el contenedor
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/app.jar"]