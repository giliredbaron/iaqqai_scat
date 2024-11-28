#!/bin/bash 

echo "Starting build" 

# Docker Hub credentials 
#DOCKER_USERNAME="yourusername"
#DOCKER_PASSWORD="yourpassword"
DOCKERHUB_REPO="giliredbaron"
targets="app1 app2 nginx"

for target in $targets
do
	if [ -d "$target" ]; then
		echo "Processing $target..."
		(cd "$target" && docker build -t "${target}-image" . && docker tag ${target} ${DOCKERHUB_REPO}/${target}:latest && docker push ${DOCKERHUB_REPO}/${target}:latest )
	else 
		echo "Directory $target does not exist"
		exit 666
	fi
done
