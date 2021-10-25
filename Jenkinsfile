pipeline {
    agent any
    
    stages {
        
   stage('Extrair Codigo Fonte') {
       steps {
         echo 'Extraindo codigo fonte...'
         checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/jonasmzsouza/PluFinderApi']]])
       }
   }

   stage('Build') {
       steps {
           
    echo 'Limpando builds anteriores...'
    sh 'mvn clean'
    echo 'Realizando o Build...'
    withSonarQubeEnv('sonardocker'){
        sh 'mvn -B -DskipTests clean package sonar:sonar'
    }
       }

   }

   stage('Teste') {
       steps {
         echo 'Testando o codigo...'
         sh 'mvn test'
       }
   }
   
    stage('Quality gate') {
         steps {
             script{
                 
            timeout(time: 45, unit: 'MINUTES') { 
                def qualityGate = waitForQualityGate() 
                    if (qualityGate.status != 'OK') {
                    error "O código não está de acordo com as regras do Sonar: ${qualityGate.status}"
              }
             }
       }
   }        
}

       stage('Aprovar o deploy?') {
        steps {
            script {
                timeout(time: 2, unit: "HOURS") {
                    def userInput = input(
                        id: 'userInput', message: 'Aprova o Deploy (Digite "s" ou "sim")?', parameters: [
                        [$class: 'TextParameterDefinition', defaultValue: 'Não', description: 'Realizar Deploy ?', name: 'Executar'] ] )
                        if (userInput != 's' && userInput != 'sim') {
                            echo 'Deploy não aprovado'
                        } else {
                        echo 'Realizando o Deploy em Desenvolvimento...'
                        deploy adapters: [tomcat9(credentialsId: 'devops', path: '', url: 'http://host.docker.internal:8090')], contextPath: 'pluapi', war: '**/*.war'
                        }
                    }
                }
            }
        }
    }
}