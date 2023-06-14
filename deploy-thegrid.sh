#! /bin/sh

docker ps -a | awk '{ print $1,$2 }' | grep magnoabreu/thegrid:1.0 | awk '{print $1 }' | xargs -I {} docker rm -f {}
docker rmi magnoabreu/thegrid:1.0

docker build --tag=magnoabreu/thegrid:1.0 --rm=true .

docker run --name thegrid --hostname=thegrid --network=interna \
--restart=always \
-e RABBITMQ_ERLANG_COOKIE='biscoitodecoelho' \
-e RABBITMQ_DEFAULT_USER=administrator \
-e RABBITMQ_DEFAULT_PASS=thegrid \
-e DB_HOST=thegrid-db \
-e DB_PORT=5432 \
-e DB_USERNAME=postgres \
-e DB_PASSWORD=admin \
-e DB_NAME=thegrid \
-v /srv/thegrid:/var/lib/rabbitmq/mnesia \
-v /etc/localtime:/etc/localtime:ro \
-p 36316:5672 \
-p 36317:15672 \
-p 36318:8080 \
-p 36319:15692 \
-d magnoabreu/thegrid:1.0
