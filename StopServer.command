#!/bin/bash
cd "$(dirname "$0")"

echo "Stopping server...."
screen -r history -X stuff "stop$(printf \\r)"
echo "Done"
sleep 5