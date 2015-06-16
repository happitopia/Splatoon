#!/bin/bash
cd "$(dirname "$0")"

echo "Stopping server...."
screen -x splatoon -X stuff "stop$(printf \\r)"
echo "Done"
sleep 2
