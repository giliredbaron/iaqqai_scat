#!/bin/bash 


# Docker Hub credentials 
DOCKERHUB_REPO="giliredbaron"
TARGETS="app1 app2 nginx"

# Define defaults to "latest" if BUILD_ID is not set
BASE_VERSION="1.2"
BUILD_VERSION="${BASE_VERSION}.${BUILD_ID:-latest}"

echo "Starting build ${BUILD_VERSION}" 
for target in $TARGETS
do
	if [ -d "$target" ]; then
		echo "Processing $target..."
		cd "$target" && docker build -t "${target}-image" .
		docker tag ${target}-image ${DOCKERHUB_REPO}/${target}:${BUILD_VERSION}  && docker push ${DOCKERHUB_REPO}/${target}:${BUILD_VERSION}
		docker tag ${target}-image ${DOCKERHUB_REPO}/${target}:latest  && docker push ${DOCKERHUB_REPO}/${target}:latest
	else 
		echo "Directory $target does not exist"
		exit 666
	fi
	
	cd - 
done
