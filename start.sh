#! /bin/bash

nohup docker-entrypoint.sh rabbitmq-server &
java -jar /opt/thegrid-1.0.war
