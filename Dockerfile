version: '3.8'

services:
  app1:
    build: ./app1
    ports:
      - "3000:3000"

  app2:
    build: ./app2
    ports:
      - "3001:3001"

  nginx:
    build: ./nginx
    ports:
      - "443:443"
    depends_on:
      - app1
      - app2

#  jenkins:
#    image: jenkins/jenkins:lts
#    user: root
#    ports:
#      - "8080:8080"
#      - "50000:50000"
#    volumes:
#      - jenkins_home:/var/jenkins_home
#      - /var/run/docker.sock:/var/run/docker.sock
#
