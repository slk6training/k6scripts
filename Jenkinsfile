pipeline {
    agent any
    stages {
        stage('Performance Testing') {
            steps {
                echo 'Installing k6'
                sh 'sudo -S chmod +x setup_k6.sh'
                sh './setup_k6.sh'
                echo 'Running K6 performance tests...'
                sh 'k6 run petStore/main.js'
            }
        }
    }
}
