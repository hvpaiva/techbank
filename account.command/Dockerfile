FROM maven:3.8.6-eclipse-temurin-19

RUN apt-get update && apt-get -y upgrade

RUN apt-get install -y inotify-tools dos2unix

ENV HOME=/app

RUN mkdir -p $HOME

WORKDIR $HOME