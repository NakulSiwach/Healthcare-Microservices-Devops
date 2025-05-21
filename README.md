# Doctor-Patient Appointment Management System

This project is a robust and scalable **doctor-patient appointment booking system** architected using **Spring Boot microservices** and deployed using a full **DevOps CI/CD pipeline**. It enables seamless patient-doctor interactions through an efficient appointment booking interface, real-time email notifications, and service observability. This system is designed to replicate real-world healthcare service needs in a modular, fault-tolerant, and scalable manner.

Each microservice is deployed independently using **Docker containers** and orchestrated through **Kubernetes**, while **RabbitMQ** handles asynchronous messaging and **MySQL** manages persistent data. Services communicate securely and efficiently through **REST APIs** and a centralized **Spring Cloud Gateway**. All microservices are registered with **Eureka Server** for service discovery and intercommunication.

The CI/CD pipeline is powered by **Jenkins**, which triggers on GitHub pushes to build Docker images and deploy them via **Ansible playbooks** into a Kubernetes cluster. **Prometheus** and **Grafana** are integrated for system health monitoring, while logs are centrally collected and visualized using the **ELK stack (Elasticsearch, Logstash, Kibana)**.

This system covers everything from patient and doctor registration to appointment scheduling and automated email notifications, demonstrating full-stack engineering using modern cloud-native and DevOps technologies.

---

## Microservices

- **Patient Service** – Handles CRUD operations for patient profiles.
- **Doctor Service** – Manages doctor data and availability.
- **Appointment Service** – Coordinates appointments between doctors and patients.
- **Notification Service** – Sends out confirmation emails using Gmail and OAuth2.

---

## Tech Stack

- **Backend**: Java 17, Spring Boot, Spring Cloud
- **Containerization**: Docker, Docker Compose
- **Orchestration**: Kubernetes with Deployments, Services, Ingress, ConfigMaps
- **Monitoring**: Prometheus for scraping, Grafana for visualization
- **CI/CD**: Jenkins + GitHub Webhook + Ansible for K8s deployment
- **Database**: MySQL (each service has its own DB)
- **Broker**: RabbitMQ for asynchronous messaging

---

## CI/CD Workflow

1. **Code Commit** – Developer pushes code to GitHub.
2. **Jenkins Trigger** – Webhook initiates Jenkins build.
3. **Docker Build** – Jenkins builds Docker images and pushes to Docker Hub.
4. **Ansible Deploy** – Ansible playbook deploys YAML files to Kubernetes.
5. **Monitoring Active** – Metrics collected by Prometheus, visualized on Grafana.

---

## Asynchronous Flow

- **AppointmentService** publishes messages to RabbitMQ.
- **NotificationService** listens to `appointment.notification.queue`.
- Messages are routed via `appointment.exchange` with `appointment.email` routing key.
- Upon receiving, an email is sent with JavaMailSender configured via OAuth2.

---

## Kubernetes Components

- `Deployment` for each microservice
- `ClusterIP`/`NodePort` services for internal/external access
- `Ingress` routes for URL-based routing
- `Secrets` and `ConfigMaps` for secure configuration
- `RabbitMQ`, `MySQL`, and monitoring tools run in dedicated pods

---

## Monitoring & Logs

### 🔍 Prometheus + Grafana
- Scrapes actuator metrics from each service
- Tracks heap memory, thread count, HTTP response times, etc.

---


---

## External Links

- **DockerHub (All Services)**  
  👉 [https://hub.docker.com/u/nakulsiwach](https://hub.docker.com/u/nakulsiwach)

- **Postman API Collection**  
  👉 [https://red-capsule-927028.postman.co/workspace/My-Workspace~f5afb104-dcbd-4e38-8e63-ec68638b54c8/folder/17588066-0b526aef-602b-4c7c-827e-706959fe68da?action=share&creator=17588066&ctx=documentation](https://red-capsule-927028.postman.co/workspace/My-Workspace~f5afb104-dcbd-4e38-8e63-ec68638b54c8/folder/17588066-0b526aef-602b-4c7c-827e-706959fe68da?action=share&creator=17588066&ctx=documentation)


