#!/bin/bash
cd "$(dirname "$0")"

cd TestServer

screen -dmS history java -jar spigot-*.jar

