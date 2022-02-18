#!/bin/bash
#export KAFKAADRESS="kafka:29092"
cd .. &&
./gradlew build &&
docker build . -f DockerfileConnect -t connectimage &&
docker build . -t predictitconsumer &&
docker build . -f DockerfileTwitterConsumer -t twitterconsumer &&
docker-compose -f docker-compose-local.yml up --force-recreate --renew-anon-volumes -d &&
#docker exec predictitstream_kafka_1 cub kafka-ready -b localhost:9092 1 20
cd bashscripts &&
sleep 60 &&
export BEARER_TOKEN
sh ./connect_predictit.sh
sh ./connect_twitter.sh