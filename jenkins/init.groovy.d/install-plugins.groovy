import jenkins.model.*

// Ensure Jenkins is fully initialized before installing plugins
def jenkinsInstance = Jenkins.getInstanceOrNull()
if (jenkinsInstance == null) {
    println "Jenkins instance is not initialized yet. Exiting script."
    return
}

// Define the plugins to install
def pluginsToInstall = ["job-dsl", "git", "github", "github-branch-source"]

println "Installing required plugins: ${pluginsToInstall.join(', ')}"

// Install plugins using the Jenkins CLI (jenkins-plugin-cli)
def command = "bash -c 'jenkins-plugin-cli --plugins ${pluginsToInstall.join(' ')}'"
def process = command.execute()
process.waitForProcessOutput(System.out, System.err)

// Check if the process completed successfully
if (process.exitValue() == 0) {
    println "Plugins installed successfully. Restarting Jenkins to apply changes..."
    jenkinsInstance.safeRestart() // Optionally restart Jenkins after plugin installation
} else {
    println "Plugin installation failed with exit code: ${process.exitValue()}"
}

// Wait for Jenkins to be ready after restart
jenkinsInstance = Jenkins.getInstanceOrNull()
while (jenkinsInstance == null) {
    println "Waiting for Jenkins to restart..."
    sleep(1000)
    jenkinsInstance = Jenkins.getInstanceOrNull()
}

println "Jenkins is now ready with installed plugins."

