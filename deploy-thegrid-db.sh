#! /bin/bash

docker run --name thegrid-db --hostname=thegrid-db --network=interna \
--restart=always \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASS=admin \
-e POSTGRES_DBNAME=thegrid \
-e ALLOW_IP_RANGE='0.0.0.0/0' \
-p 36432:5432 \
-v /etc/localtime:/etc/localtime:ro \
-v /srv/thegrid-db/:/var/lib/postgresql/ \
-d kartoza/postgis:14-3.3


