echo "Starting kafka service containeer"
docker start kafka-server

echo "waiting for 100 seconds while kafka starts"
sleep 100

echo "Starting tgBot service container"
docker start bot-tg-container 
 
echo "Starting MessageProcessing  service container"
docker start message-processing-container
