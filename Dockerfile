FROM openjdk:8-jre-alpine

RUN apk update && apk upgrade \
   && apk add --no-cache ttf-dejavu \
   && apk add --no-cache msttcorefonts-installer \
   && update-ms-fonts && fc-cache -f


COPY target/treport*.jar treport.jar
CMD ["/usr/bin/java", "-jar","-Duser.timezone=Asia/Qatar","-Djava.awt.headless=true" ,"-Djava.security.egd=file:/dev/./urandom" , "-Dspring.profiles.active=default", "treport.jar"]
VOLUME /tmp
VOLUME /logs
VOLUME /uploads
EXPOSE 9071
