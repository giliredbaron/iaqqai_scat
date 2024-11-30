
# IAQ QAI SCAT Project

This project consists of two applications, **App1** and **App2**, and an NGINX gateway to route requests. It also includes Jenkins for building and deploying the project. The project can be built locally using Docker Compose or from pre-built Docker images stored on DockerHub.

---

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

---

## Getting Started

### Prerequisites

- Install Docker and Docker Compose.
- If using Jenkins, ensure Jenkins is configured and running.

### Building and Running the Project

#### Build Locally
To build and run the project locally, use:
```bash
docker-compose up --build
```

#### Run from DockerHub
To run the project using pre-built images:
```bash
docker-compose -f docker-compose_from_dockerHub.yml up
```

#### Run with Jenkins
To use Jenkins for building and deploying the project:
1. Start Jenkins:
   ```bash
   docker-compose -f docker-compose_jenkins.yml up
   ```
2. Configure Jenkins to run the job defined in the `Jenkinsfile`.

---

## Jenkins Pipeline

When using Jenkins to build the project:

1. **User Input**: The pipeline prompts the user to select a release version to build.
2. **Build Process**: Jenkins:
   - Builds Docker images for **App1** and **App2**.
   - Pushes the images to DockerHub.
3. **Verification**: Images are tagged appropriately and uploaded to DockerHub.

---

## Testing the Applications

### App1
To test **App1**, navigate to:
```text
https://<server_ip>/app1
```
**Expected Output**:  
A web page displaying `APP1`.

### App2
To test **App2**, navigate to:
```text
https://<server_ip>/app2
```
**Expected Output**:  
An "Upgrade Required" message (as **App2** uses WebSocket connections).

#### Testing App2 with `wscat`
You can test **App2** using `wscat`:
```bash
wscat -c wss://<server_ip>/app2 --no-check
```
**Example Output**:
```text
Connected (press CTRL+C to quit)
< Welcome to the WebSocket server!
> hi
< Hello, you sent -> hi
```

In the logs, you will see entries similar to:
```text
app2-1   | Client connected
app2-1   | Received: hi
nginx-1  | 172.21.0.1 - - [30/Nov/2024:21:32:55 +0000] "GET /app2 HTTP/1.1" 101 60 "-" "-"
app2-1   | Client disconnected
```

---

## Logs and Troubleshooting

### NGINX Logs
NGINX logs can be found under the container logs or configured log directory.

### Jenkins Logs
Jenkins logs and job statuses are accessible through the Jenkins UI.

---

## Notes
- Make sure to configure environment variables before running the project.
- Use the provided Docker Compose files for different environments as needed.
