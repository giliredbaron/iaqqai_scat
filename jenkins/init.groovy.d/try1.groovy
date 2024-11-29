import jenkins.model.*
import org.jenkinsci.plugins.workflow.job.*
import hudson.plugins.git.*

// Define the job name and parameters
def jobName = "DynamicBranchPipeline GILILI"
def repoUrl = "https://github.com/giliredbaron/iaqqai_scat.git"

// Define the pipeline script
def pipelineScript = """


pipeline {
    agent any
    parameters {
        gitParameter(name: 'BRANCH',
                     description: 'Select a branch',
                     type: 'PT_BRANCH',
                     defaultValue: 'main',
                     branchFilter: '.*',
                     selectedValue: 'DEFAULT',
                     quickFilterEnabled: true)
    }
    
    
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    def branchName = params.BRANCH
                    echo "Cloning from \${repoUrl} on branch \${branchName} GILILI"
                    //git branch: 'origin/main', url: 'https://github.com/giliredbaron/iaqqai_scat.git'
                    checkout scmGit(branches: [[name: '**']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/giliredbaron/iaqqai_scat.git']])
                
                }
            }
        }
        stage('Build') {
            steps {
                echo "Building the project from \${repoUrl} on branch \${params.BRANCH}"
                // Add your build commands here
            }
        }
    }
}
"""

// Print pipeline script for verification
println(pipelineScript)

