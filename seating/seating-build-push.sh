#!/bin/bash
VAR="${1:-mbirnhak}"

chmod +x gradlew
./gradlew clean build -x test
docker buildx build --push --platform linux/amd64,linux/arm64 --tag "$VAR"/seating:v1 .