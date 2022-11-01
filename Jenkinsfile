pipeline {
    agent { dockerfile true }
    stages {
        stage('Build') { 
            steps {
                sh 'docker build -t faktura:latest .'
            }
        }
    }
}
