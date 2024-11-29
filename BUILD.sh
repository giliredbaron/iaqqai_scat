#!/bin/bash 

echo "Starting build" 

# Docker Hub credentials 
DOCKERHUB_REPO="giliredbaron"
targets="app1 app2 nginx"

# Define defaults to "latest" if BUILD_ID is not set
BUILD_VERSION="${BUILD_ID:-latest}"

for target in $targets
do
	if [ -d "$target" ]; then
		echo "Processing $target..."
		(cd "$target" && docker build -t "${target}-image" . && docker tag ${target} ${DOCKERHUB_REPO}/${target}:${BUILD_VERSION}  && docker push ${DOCKERHUB_REPO}/${target}:${BUILD_VERSION})
	else 
		echo "Directory $target does not exist"
		exit 666
	fi
done
