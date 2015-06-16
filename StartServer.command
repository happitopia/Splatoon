#!/bin/bash
cd "$(dirname "$0")"

cd TestServer

echo "Starting server..."
screen -dmS splatoon java -jar spigot-*.jar
echo "Done"
sleep 2
