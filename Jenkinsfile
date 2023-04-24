pipeline {
    agent any
    stages {
        stage('Performance Testing') {
            steps {
                echo 'Installing k6'
                sh 'pwd'
                //sh '/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"'
                 sh 'brew install k6'
               // sh 'echo sahani@123 | sudo -S chmod +x setup_k6.sh'
               // sh 'ls -al'
             //   sh 'echo sahani@123 | ./setup_k6.sh'
                echo 'Running K6 performance tests...'
                sh 'k6 run petStore/main.js'
            }
        }
    }
}
