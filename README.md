# IAQ QAI SCAT Project

This project consists of two applications, **App1** and **App2**, and an NGINX gateway to route requests. It also includes Jenkins for building and deploying the project. The project can be built locally using Docker Compose or from pre-built Docker images stored on DockerHub.

## Project Structure

- **`docker-compose.yml`**  
  Builds the project locally by creating Docker images and running containers.

- **`docker-compose_from_dockerHub.yml`**  
  Uses pre-built images from DockerHub to run the project.

- **`docker-compose_jenkins.yml`**  
  Sets up Jenkins for building the project and running jobs.

- **`Jenkinsfile`**  
  Defines the pipeline to build the project in Jenkins.

- **`BUILD.sh`**  
  Script for building the project. This script can be used in both Jenkins and local environments.  
  - **Required Environment Variables**:
    - `BUILD_ID`: Used to tag the Docker image.  
      - Example: `BUILD_ID=[name_of_test]`  
        The resulting image will be tagged as `1.2.[name_of_test]`.

- **`app1/` and `app2/`**  
  Contain the respective Dockerfiles for building **App1** and **App2**.

- **`nginx/`**  
  Configures NGINX as the gateway to route requests to **App1** and **App2**.

- **`jenkins/`**  
  Sets up Jenkins for building the project.

## Getting Started

### Prerequisites

- Install Docker and Docker Compose.
- If using Jenkins, ensure Jenkins is configured and running.

### Building and Running the Project

#### Build Locally
To build and run the project locally, use:
```bash
docker-compose up --build
