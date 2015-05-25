#!/bin/bash
cd "$(dirname "$0")"

cp target/*.jar TestServer/plugins/

screen -r history -X stuff "reload$(printf \\r)"
