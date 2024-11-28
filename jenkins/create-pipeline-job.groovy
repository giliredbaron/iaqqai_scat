pipelineJob('docker-pipeline') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://your-repo-url.git')
                    }
                    branch('main')
                }
            }
        }
    }
}

