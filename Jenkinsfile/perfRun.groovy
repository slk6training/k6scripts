pipeline {
    agent any
    options {
        timeout(time: 90, unit: 'MINUTES')
        disableConcurrentBuilds()
    }
    parameters {
        string(name: 'branch', defaultValue: 'origin/develop', description: 'Git branch', trim: true)
        string(name: 'RAMP_UP', defaultValue: '30', description: 'Ramp up (or warm up) duration in seconds', trim: true)
        string(name: 'HOLD_DURATION', defaultValue: '120', description: 'Hold duration in seconds', trim: true)
        string(name: 'TEAR_DOWN', defaultValue: '10', description: 'Tear down (or cool down) duration in seconds', trim: true)
        choice(name: 'TARGET_LOAD', choices: ['1', '0.75', '0.60', '0.50', '0.25', '0.10', '1.25', '1.50', '2'], description: '1 for 100% GA, 0.5 for 50% GA and likewise')
    }
    environment {
        PURPOSE="LOAD-TEST"
        LG_COUNT="1"
        SERVICE_ID="ps"
        k6_DOCKER_IMAGE="loadimpact/k6:0.30.0"
    }
    stages {
        stage('Init') {
            steps {
                script {
                    echo 'Hello world!'
                    loadScripts()
                   // commonScripts.cancelPreviousBuilds()
                }
            }
        }
        stage('Setup') {
            steps {
                dir('petStore') {
                   sh 'yarn k6:setup'
                }
                sh script: 'sudo chown -R $USER:$(id -g -n) .', label: "Set file system ownership"
                sh script: 'sudo chmod -R 775 .', label: "Set file system permissions"
            }
        }
        stage('Test') {
            steps {
                sh script: "docker run --name k6-docker-${env.SERVICE_ID} --rm -v $WORKSPACE/petStore:/perf -w /perf --ipc host --network host -p 8125:8125/udp -e RAMP_UP=${RAMP_UP} -e HOLD_DURATION=${HOLD_DURATION} -e TEAR_DOWN=${TEAR_DOWN} -e TARGET_LOAD=${TARGET_LOAD} -i ${env.k6_DOCKER_IMAGE} --tag test_run_id=${env.SERVICE_ID}_${currentBuild.number} --quiet run main.js", label: "Run perf test"
            }
            post {
                always {
                    sh script: "docker rm -f \$(docker ps --filter name=k6-docker-${env.SERVICE_ID} --format {{.Names}}) || true", label: "Remove k6 docker container"
                }
            }
        }
    }
}

// void loadScripts() {
//     commonScripts = load 'Infrastructure/Jenkinsfiles/Common.groovy'
// }
