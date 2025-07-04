pipeline {
    agent any
    stages {
        stage('git-pull') {
            steps {
               git branch: 'main', url: 'https://github.com/abhilashmwaghmare/CDEC-b39-pipeline-terra.git'
            }
        }
         stage('terraform-init') {
            steps {
               sh '''
                cd instance
                terraform init '''
               
            }
        }
        stage('terraform-plan') {
            steps {
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws-cred', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {

              sh '''
                cd instance
                terraform plan '''
               
            }
        }
    }   
    
        stage('terraform-apply') {
            steps {
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws-cred', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
               sh '''
                cd instance
                terraform apply --auto-approve '''
               
            }
        }
    }

    }     
}  