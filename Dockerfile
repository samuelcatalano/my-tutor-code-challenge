FROM openjdk:11.0.5-jre-slim

MAINTAINER Samuel Catalano <samuel.catalano@gmail.com>

RUN mkdir -p /usr/share/mytutor && \
mkdir /var/run/mytutor && \
mkdir /var/log/mytutor

COPY /target/my-tutor-0.0.1-SNAPSHOT.jar /usr/share/mytutor/my-tutor-0.0.1-SNAPSHOT.jar

WORKDIR /usr/share/mytutor/
EXPOSE 8080 8787

ENV TZ=Europe/London
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "-jar","my-tutor-0.0.1-SNAPSHOT.jar"]