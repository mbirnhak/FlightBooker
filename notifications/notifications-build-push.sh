#!/bin/bash
VAR="${1:-mbirnhak}"

chmod +x gradlew
./gradlew build 
docker buildx build --push --platform linux/amd64,linux/arm64 --tag "$VAR"/notifications:v1 .