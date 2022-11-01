pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'docker build -t faktura:latest .'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run --rm --name faktura.exe -p 8080:8080 faktura:latest'
            }
        }
    }
}
