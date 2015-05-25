#!/bin/bash
cd "$(dirname "$0")"

cd TestServer

echo "Starting server..."
screen -dmS history java -jar spigot-*.jar
echo "Done"
sleep 2
