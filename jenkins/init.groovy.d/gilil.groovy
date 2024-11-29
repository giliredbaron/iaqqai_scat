pipeline {
    agent any
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
        stage('Clone Repository') {
            steps {
                script {
                    // Ensure the branch name is correctly formatted
                    def branchName = params.BRANCH.replaceAll(/^origin\//, '') // Remove 'origin/' prefix if needed
                    echo "Cloning from https://github.com/giliredbaron/iaqqai_scat.git on branch ${branchName} GILI"
                    
                    // Correct git step without redundant 'origin/' prefix
                    git branch: branchName,
                        url: 'https://github.com/giliredbaron/iaqqai_scat.git',
                        credentialsId: 'your-credentials-id'
                }
            }
        }
        stage('Build') {
            steps {
                echo "Building the project from branch ${params.BRANCH}"
                // Add your build steps here
            }
        }
    }
}

