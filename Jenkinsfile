pipeline {
    agent any
    triggers {
        githubPush()
    }

    environment {
        GITHUB_REPO_URL = 'https://github.com/NakulSiwach/Healthcare-Microservices-Devops.git'
        DOCTOR_DOCKER_IMAGE = 'nakulsiwach/doctor-service'
        PATIENT_DOCKER_IMAGE = 'nakulsiwach/patient-service'
        APPOINTMENT_DOCKER_IMAGE = 'nakulsiwach/appointment-service'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def doctorImage = docker.build("${DOCTOR_DOCKER_IMAGE}", './DoctorService/')
                    def patientImage = docker.build("${PATIENT_DOCKER_IMAGE}", './PatientService/')
                    def appointmentImage = docker.build("${APPOINTMENT_DOCKER_IMAGE}", './AppointmentService/')
                }
            }
        }

        stage('Scan Docker Images') {
            steps {
                script {
                    sh "trivy image ${DOCTOR_DOCKER_IMAGE} || true"
                    sh "trivy image ${PATIENT_DOCKER_IMAGE} || true"
                    sh "trivy image ${APPOINTMENT_DOCKER_IMAGE} || true"
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        docker.image("${DOCTOR_DOCKER_IMAGE}").push()
                        docker.image("${PATIENT_DOCKER_IMAGE}").push()
                        docker.image("${APPOINTMENT_DOCKER_IMAGE}").push()
                    }
                }
            }
        }

        stage('Apply Kubernetes Manifests with Ansible') {
            steps {
                script {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
                        ansiblePlaybook(
                            playbook: 'ansible-playbook.yaml',
                            inventory: 'inventory'
                        )
                    }
                }
            }
        }
    }
}
