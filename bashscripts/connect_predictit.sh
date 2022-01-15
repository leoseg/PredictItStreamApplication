#!/bin/bash

docker-compose exec -T kafka kafka-topics --create --zookeeper \
zookeeper:2181 --replication-factor 1 --partitions 1 --topic president.boris-stays

docker-compose exec -T kafka kafka-topics --create --zookeeper \
zookeeper:2181 --replication-factor 1 --partitions 1 --topic president.france-president

docker-compose exec -T kafka kafka-topics --create --zookeeper \
zookeeper:2181 --replication-factor 1 --partitions 1 --topic president.italian-president

docker-compose exec -T kafka kafka-topics --create --zookeeper \
zookeeper:2181 --replication-factor 1 --partitions 1 --topic president.hungarian-prime-minister

docker-compose exec -T kafka kafka-topics --create --zookeeper \
zookeeper:2181 --replication-factor 1 --partitions 1 --topic president.european-leader-leaves


curl -i -X POST -H "Accept:application/json"  -H  "Content-Type:application/json" http://localhost:8083/connectors/  -d '{ "name": "source_rest_predict_it_italian_president", "config": { "key.converter":"org.apache.kafka.connect.storage.StringConverter", "value.converter":"org.apache.kafka.connect.storage.StringConverter", "connector.class": "com.tm.kafka.connect.rest.RestSourceConnector", "tasks.max": "1", "rest.source.poll.interval.ms": "600000", "rest.source.method": "GET", "rest.source.url": "https://www.predictit.org/api/marketdata/markets/7663", "rest.source.payload.converter.class": "com.tm.kafka.connect.rest.converter.StringPayloadConverter", "rest.source.properties": "Content-Type:application/json,Accept::application/json", "rest.source.topic.selector": "com.tm.kafka.connect.rest.selector.SimpleTopicSelector", "rest.source.destination.topics": "president.italian-president" } }'

curl -i -X POST -H "Accept:application/json"  -H  "Content-Type:application/json" http://localhost:8083/connectors/  -d '{ "name": "source_rest_predict_it_france_president", "config": { "key.converter":"org.apache.kafka.connect.storage.StringConverter", "value.converter":"org.apache.kafka.connect.storage.StringConverter", "connector.class": "com.tm.kafka.connect.rest.RestSourceConnector", "tasks.max": "1", "rest.source.poll.interval.ms": "600000", "rest.source.method": "GET", "rest.source.url": "https://www.predictit.org/api/marketdata/markets/7360", "rest.source.payload.converter.class": "com.tm.kafka.connect.rest.converter.StringPayloadConverter", "rest.source.properties": "Content-Type:application/json,Accept::application/json", "rest.source.topic.selector": "com.tm.kafka.connect.rest.selector.SimpleTopicSelector", "rest.source.destination.topics": "president.france-president" } }'

curl -i -X POST -H "Accept:application/json"  -H  "Content-Type:application/json" http://localhost:8083/connectors/  -d '{ "name": "source_rest_predict_it_hungary_president", "config": { "key.converter":"org.apache.kafka.connect.storage.StringConverter", "value.converter":"org.apache.kafka.connect.storage.StringConverter", "connector.class": "com.tm.kafka.connect.rest.RestSourceConnector", "tasks.max": "1", "rest.source.poll.interval.ms": "600000", "rest.source.method": "GET", "rest.source.url": "https://www.predictit.org/api/marketdata/markets/7636", "rest.source.payload.converter.class": "com.tm.kafka.connect.rest.converter.StringPayloadConverter", "rest.source.properties": "Content-Type:application/json,Accept::application/json", "rest.source.topic.selector": "com.tm.kafka.connect.rest.selector.SimpleTopicSelector", "rest.source.destination.topics": "president.hungary-president" } }'

curl -i -X POST -H "Accept:application/json"  -H  "Content-Type:application/json" http://localhost:8083/connectors/  -d '{ "name": "source_rest_predict_it_european_leader_leaves", "config": { "key.converter":"org.apache.kafka.connect.storage.StringConverter", "value.converter":"org.apache.kafka.connect.storage.StringConverter", "connector.class": "com.tm.kafka.connect.rest.RestSourceConnector", "tasks.max": "1", "rest.source.poll.interval.ms": "600000", "rest.source.method": "GET", "rest.source.url": "https://www.predictit.org/api/marketdata/markets/7643", "rest.source.payload.converter.class": "com.tm.kafka.connect.rest.converter.StringPayloadConverter", "rest.source.properties": "Content-Type:application/json,Accept::application/json", "rest.source.topic.selector": "com.tm.kafka.connect.rest.selector.SimpleTopicSelector", "rest.source.destination.topics": "president.european-leader-leaves" } }'

curl -i -X POST -H "Accept:application/json"  -H  "Content-Type:application/json" http://localhost:8083/connectors/  -d '{ "name": "source_rest_predict_it_boris-stays", "config": { "key.converter":"org.apache.kafka.connect.storage.StringConverter", "value.converter":"org.apache.kafka.connect.storage.StringConverter", "connector.class": "com.tm.kafka.connect.rest.RestSourceConnector", "tasks.max": "1", "rest.source.poll.interval.ms": "600000", "rest.source.method": "GET", "rest.source.url": "https://www.predictit.org/api/marketdata/markets/7665", "rest.source.payload.converter.class": "com.tm.kafka.connect.rest.converter.StringPayloadConverter", "rest.source.properties": "Content-Type:application/json,Accept::application/json", "rest.source.topic.selector": "com.tm.kafka.connect.rest.selector.SimpleTopicSelector", "rest.source.destination.topics": "president.boris-stays" } }'

