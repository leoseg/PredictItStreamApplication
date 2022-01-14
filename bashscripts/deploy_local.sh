#!/bin/bash
export KAFKAADRESS="kafka:29092"
cd .. &&
./gradlew build &&
docker build . -f DockerfileConnect -t seeger16/connectimage &&
docker build . -t seeger16/predictitconsumer &&
docker-compose -f docker-compose-local.yml up --force-recreate --renew-anon-volumes -d &&
docker exec predictitstream_kafka_1 cub kafka-ready -b localhost:9092 1 20
cd bashscripts &&
sh ./connect_predictit.sh