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
                     branchFilter: 'origin/(.*)', 
                     selectedValue: 'DEFAULT', 
                     quickFilterEnabled: true)
    }
    stages {
        stage('Clone Repository') {
            steps {
		// def branchName = params.BRANCH
		def param1 = params.BRANCH
                echo "Cloning from ${branchName} on branch ${branchName} GILILI ${repoUrl} IIII"
		echo "Test params.BRANCH GILII"
		//echo "222Test params.BRANCH GILII"
                // git branch: ${branchName}, url: '${repoUrl}'
            }
        }
        stage('Build') {
            steps {
                echo "Building the project from ${repoUrl} on branch \${params.BRANCH}"
                // Add your build commands here
            }
        }
    }
}
"""

// Create or update the pipeline job
// def job = Jenkins.instance.getItem(jobName) ?: Jenkins.instance.createProject(org.jenkinsci.plugins.workflow.job.WorkflowJob, jobName)
// job.definition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(pipelineScript, true)

// Add SCM configuration
// def scm = new GitSCM(repoUrl)
// scm.branches = [new BranchSpec("*/main")]
// job.setScm(scm)

// job.save()

