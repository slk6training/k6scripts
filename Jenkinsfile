pipeline {
    agent any
    stages {
        stage('Performance Testing') {
            steps {
                echo 'Installing k6'
                sh 'pwd'
                sh 'ls -al'
                sh 'sudo chmod +x setup_k6.sh'
                sh 'ls -al'
                sh './setup_k6.sh'
                echo 'Running K6 performance tests...'
                sh 'k6 run petStore/main.js'
            }
        }
    }
}
