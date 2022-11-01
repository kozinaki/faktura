pipeline {
    agent {
        docker {
            image 'bellsoft/liberica-openjdk-debian:17'
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh './run.sh && ./gradlew bootJar' 
            }
        }
    }
}
