#! /bin/sh

docker run --name rabbitmq --hostname=rabbitmq --network=interna \
--restart=always \
-e RABBITMQ_ERLANG_COOKIE='biscoitodecoelho' \
-e RABBITMQ_DEFAULT_USER=thegrid \
-e RABBITMQ_DEFAULT_PASS=thegrid \
-v /srv/rabbitmq/:/var/lib/rabbitmq \
-v /etc/localtime:/etc/localtime:ro \
-p 36317:5672 \
-p 36318:15672 \
-d rabbitmq:management-alpine
