
echo "Stopping tgBot service container"
docker stop bot-tg-container 
 
echo "Stopping MessageProcessing  service container"
docker stop message-processing-container

echo "Stopping kafka service containeer"
docker stop kafka-server
