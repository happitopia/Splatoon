#!/bin/bash
cd "$(dirname "$0")"

echo "Showing Log"
echo "Control+C to exit"
sleep 2
tail -n 100 -f TestServer/logs/latest.log