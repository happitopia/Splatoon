#!/bin/bash
cd "$(dirname "$0")"

mkdir -p TestServer/build
mkdir -p TestServer/plugins

cd TestServer
rm spigot-*.jar

cd build

echo "Downloading installer"
curl https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -o BuildTools.jar

echo "Building Spigot"
java -jar BuildTools.jar

cp spigot-*.jar ..
