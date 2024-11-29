#FROM jenkins/jenkins:lts
#RUN /usr/local/bin/install-plugins.sh job-dsl

FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y sudo
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

# Jenkins Plugins Installation
#RUN jenkins-plugin-cli --plugins "git blueocean github github-branch-source"
#RUN jenkins-plugin-cli --plugins "git github github-branch-source github-api github-organization-folder"
RUN jenkins-plugin-cli --plugins "git github github-branch-source github-api"

# Copy init groovy scripts
#COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/

USER jenkins

