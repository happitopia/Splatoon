#!/bin/bash
cd "$(dirname "$0")"

cp target/*.jar TestServer/plugins/

screen -x history -X stuff "reload$(printf \\r)"
