pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('qui_dockerhub')
    }
    parameters {
        gitParameter(
            name: 'BRANCH',
            type: 'PT_BRANCH',
            defaultValue: 'main',
            branchFilter: '.*',
            selectedValue: 'DEFAULT',
            quickFilterEnabled: true
        )
    }
    stages {
        stage('Determine Branch') {
            steps {
                script {
                    branchName = params.BRANCH.replaceAll(/^origin\//, '')
                    repoUrl = "https://github.com/giliredbaron/iaqqai_scat.git"
                }
            }
        }
        stage('Clone Repository') {
            steps {
                script {
                    echo "Cloning from ${repoUrl} branch ${branchName}"
                    git branch: branchName,
                        url: repoUrl,
                        credentialsId: 'your-credentials-id'
                }
                sh 'ls -l && cat README.md'
            }
        }
        stage('Build Docker Images') {
            steps {
                script {
                    echo "Building the project from branch ${params.BRANCH} AND ${branchName}"
                    sh 'echo ${DOCKER_HUB_CREDENTIALS} | docker login -u giliredbaron --password-stdin && ./BUILD.sh'
                }
            }
        }
    }
    post {
        always {
            script {
                sh 'docker-compose down'
                sh 'docker logout'
            }
        }
    }
}

