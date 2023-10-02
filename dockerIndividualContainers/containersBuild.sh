#!/bin/bash
if [ -n "$1" ] && [ -n "$2" ]
then
echo Hello $1 from $2;
tgbotUsername=$1
tgbotToken=$2
else
echo "Need parameters tgbotUsername and tgbotToken"
echo "No tg bot credentials found, exiting..."
exit 1
fi

echo "Building BotTg service image"
docker build --tag bot-tg-image -f Dockerfile.botTg ..
echo "Building MessageProccessing service image"
docker build --tag message-processing-image -f Dockerfile.messageProcessing ..

echo "Creating kafka service containeer"
if docker network ls | grep app-tier > /dev/null
then
echo "Inner app-tier network exists"
else
echo "Creating inner network app-tier" 
docker network create app-tier --driver bridge
fi

docker create --name kafka-server --hostname kafka-server \
    --network app-tier \
    -e KAFKA_CFG_NODE_ID=0 \
    -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
    -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
    -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
    -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka-server:9093 \
    -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
    bitnami/kafka:latest

echo "Creating tgBot service container"
docker create --name bot-tg-container --network app-tier \
 -e TGBOTCREDENTIALS="--com.barcode.tgbot.bot.username=$tgbotUsername \
 --com.barcode.tgbot.bot.token=$tgbotToken" \
 -e KAFKABOOTSTRAPSERVER="--spring.kafka.bootstrap-servers=kafka-server:9092" \
bot-tg-image

echo "Creating MessageProcessing  service container"
docker create --name message-processing-container --network app-tier \
 -e KAFKABOOTSTRAPSERVER="--spring.kafka.bootstrap-servers=kafka-server:9092" \
message-processing-image 
