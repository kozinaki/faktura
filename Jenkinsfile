pipeline {
    agent { dockerfile true }
    stages {
        stage('Build') { 
            steps {
                sh 'docker build -t faktura:latest .'
                sh 'docker run --rm --name faktura.exe -p 8080:8080 faktura:latest'
            }
        }
    }
}
