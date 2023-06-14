FROM rabbitmq:3.12-management
MAINTAINER magno.mabreu@gmail.com

USER root
RUN apt update && apt -y upgrade && mkdir /home/thegrid && apt install -y vim openjdk-11-jdk
COPY ./target/thegrid-1.0.war /opt/
CMD ["/bin/bash"]
ENTRYPOINT ["/opt/start.sh"]
EXPOSE 4369 15672 5672 15691 15692 25672 8080
COPY ./start.sh /opt/