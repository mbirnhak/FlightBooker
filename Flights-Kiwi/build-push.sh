#!/bin/bash

docker context create buildx-build
docker buildx create --use buildx-build
chmod +x gradlew
./gradlew build
docker buildx build --push --platform linux/amd64,linux/arm64 --tag mbirnhak/flights:v1 .