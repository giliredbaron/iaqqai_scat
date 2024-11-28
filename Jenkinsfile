pipeline {
    agent any
    parameters {
        string(name: 'BRANCH_TAG', defaultValue: 'main', description: 'Branch or Tag to build')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH_TAG}", url: 'https://your-repo-url.git'
            }
        }
        stage('Build and Push') {
            steps {
                sh '''
                docker-compose build
                docker-compose push
                '''
            }
        }
    }
}

