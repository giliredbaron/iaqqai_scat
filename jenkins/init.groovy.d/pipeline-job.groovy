import jenkins.model.*
import hudson.model.*

def jenkins = Jenkins.instance
def jobName = 'docker-pipeline'

if (jenkins.getItem(jobName) == null) {
    println "Creating Jenkins job: ${jobName}"

    def job = jenkins.createProject(FreeStyleProject, jobName)

    def scm = new hudson.plugins.git.GitSCM('https://github.com/giliredbaron/iaqqai.git')
    scm.branches = [new hudson.plugins.git.BranchSpec('*/main')]
    job.scm = scm

    // Add GitHub Hook trigger
    def trigger = new org.jenkinsci.plugins.github.webhook.GitHubPushTrigger()
    job.addTrigger(trigger)

    job.save()
}
