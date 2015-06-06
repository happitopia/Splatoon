#!/bin/bash
cd "$(dirname "$0")"

echo "Stopping server...."
screen -x history -X stuff "stop$(printf \\r)"
echo "Done"
sleep 2
