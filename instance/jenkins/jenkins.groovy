pipeline {
    agent any
    stages {
        stage('stage-1-pull') {
            steps {
               git credentialsId: 'b39-cred', url: 'https://github.com/abhilashmwaghmare/student-ui.git'
            }
        }
         stage('stage-2-build') {
            steps {
               sh '/opt/maven/bin/mvn clean package'
            }
        }
        stage('Artifact-s3') {
            steps {
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws-cred', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
            
                   sh 'aws s3 ls'
                   sh 'aws s3 cp /var/lib/jenkins/workspace/b39-pipeline/target/studentapp-2.2-SNAPSHOT.war s3://cdecb39-artifact'
                }
            }
    
        }     
    }    
}