// Install the Job DSL plugin
//Jenkins.instance.pluginManager.install('job-dsl')

// Install Job DSL plugin using the correct method
println "222 Installing Job DSL Plugin..."
//Jenkins.instance.updateCenter.getPlugin('job-dsl').deploy()

// Optionally, wait for the plugin to be installed and Jenkins to restart
//Jenkins.instance.safeRestart()


println "Executing init-script.sh..."

def process = 'bash jenkins-plugin-cli --plugins "job-dsl git"'.execute()
process.waitForProcessOutput(System.out, System.err)

if (process.exitValue() == 0) {
    println "Script executed successfully."
} else {
    println "Script execution failed with exit code: ${process.exitValue()}"
}


//// Ensure Jenkins is fully initialized before using it
//def jenkinsInstance = Jenkins.getInstance()
//println "Installing Job DSL Plugin..."
//
//// Install the Job DSL plugin using the proper Jenkins plugin manager
//jenkinsInstance.updateCenter.getPlugin('job-dsl').deploy()

// Optionally restart Jenkins if necessary after installation
jenkinsInstance.safeRestart()
