pipeline {
    agent {
        // Equivalent to "docker build -f Dockerfile -t faktura:latest .
        dockerfile {
            filename 'Dockerfile'
            dir '.'
            label ''
            additionalBuildArgs '-t faktura:latest'
            args ''
        }
    }
    stages {
        stage('Deploy') {
            steps {
                sh 'docker run --rm --name faktura.exe -p 8080:8080 faktura:latest'
            }
        }
    }
}
