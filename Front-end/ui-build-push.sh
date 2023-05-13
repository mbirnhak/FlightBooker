#!/bin/bash
VAR="${1:-mbirnhak}"

docker buildx build --push --platform linux/amd64,linux/arm64 --tag "$VAR"/front-end:v1 .