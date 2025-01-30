# RESTful Application with HATEOAS, Docker, PostgreSQL, and Kubernetes

This project demonstrates the integration of modern technologies for developing, deploying, and testing RESTful applications. Using **Spring Boot**, **Docker**, **PostgreSQL**, and **Kubernetes**, the application implements **HATEOAS** principles to enable efficient and dynamic interaction with REST services. Additionally, the project explores containerization, data persistence, and container orchestration in a cluster environment. This lab provides hands-on experience with modern development and deployment practices, preparing you to tackle real-world challenges in building scalable and robust applications.

## Key Features

- **RESTful API**: Implements HATEOAS principles for dynamic navigation and interaction.
- **Containerization**: Uses Docker to containerize the application and PostgreSQL database for portability and isolation.
- **Data Persistence**: Integrates with PostgreSQL for efficient data storage.
- **Kubernetes Orchestration**: Deploys the application and database in a Kubernetes cluster for automated container management.
- **Scalability**: Demonstrates horizontal scaling in Kubernetes to handle increased workloads.
- **GitOps**: Uses ArgoCD for continuous deployment and synchronization with a Git repository.

## Technologies Used

### Backend
- **Spring Boot**: Framework for building RESTful APIs in Java.
- **PostgreSQL**: Relational database for persistent data storage.
- **Maven**: Dependency management and build tool.
- **HATEOAS**: Implements hypermedia-driven REST APIs.

### Frontend
- **Postman**: Tool for testing and debugging REST endpoints.
- **Swagger/OpenAPI**: For API documentation and interactive testing.

### Deployment and Orchestration
- **Docker**: Containerization platform for packaging the application and its dependencies.
- **Kubernetes**: Container orchestration for managing deployments, scaling, and load balancing.
- **Minikube**: Local Kubernetes cluster for development and testing.
- **ArgoCD**: GitOps tool for continuous deployment and synchronization.

### Development Tools
- **IntelliJ IDEA**: IDE for Java development.
- **Git**: Version control system for collaborative development.
- **kubectl**: Command-line tool for interacting with Kubernetes clusters.

## Installation and Setup

### Backend

1. Clone the repository:
   ```bash
   git clone https://github.com/mono789/rest-kubernetes.git
   cd rest-kubernetes
   ```
2. Install dependencies with Maven:
    ```
   mvn clean install
     ```
4. Run the Spring Boot application:
   ```
   mvn spring-boot:run
   ```
   The server will be available at http://localhost:8080.
### Database
1. Pull the PostgreSQL Docker image:
   ```
   docker pull postgres
   ```
3. Run the PostgreSQL container:
   ```
   docker run --name postgres-db -e POSTGRES_PASSWORD=yourpassword -d -p 5432:5432 postgres
   ```
5. Configure the database connection in the Spring Boot application (application.properties).


### Kubernetes Deployment
1. Start Minikube:
  ```
  minikube start
  ```
2. Deploy the application and PostgreSQL to Kubernetes:
  ```
  kubectl apply -f kubernetes-deployment.yaml
  ```
3. Verify the deployment:
  ```
  kubectl get pods
  ```
4. Expose the service:
  ```
  minikube service restoct-service
  ```
### ArgoCD Setup (GitOps)

1. Create the ArgoCD namespace:
  ```
  kubectl create namespace argocd
  ```
2. Install ArgoCD:
  ```
  kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
  ```
3. Get the initial admin password:
  ```
  kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 --decode
  ```
4. Port-forward the ArgoCD server:
  ```
  kubectl port-forward svc/argocd-server -n argocd 8080:443
  ```
5. Access ArgoCD at ```https://localhost:8080``` and log in with the admin credentials.

6. Create an ArgoCD application manifest (application-argocd.yaml) and apply it:
  ```
  kubectl apply -f application-argocd.yaml
  ```
## Project Structure
 ```
project/
├── backend/                  # Backend code in Spring Boot
│   ├── src/                  # Application source code
│   └── pom.xml               # Maven configuration
│   ├── restoct-deployment.yaml
│   └── postgres-deployment.yaml
└── README.md                 # Project documentation
 ```

## Usage
1. Access the application via the exposed Kubernetes service.
2. Use Postman or Swagger to interact with the RESTful API.
3. Test the endpoints for creating, listing, and retrieving flight information.

## Example API Endpoints
* Create a Flight: POST /flights

* List All Flights: GET /flights

* Get Flight by ID: GET /flights/{id}

* Get Top-Rated Flights: GET /flights/top

## Scaling with Kubernetes
To demonstrate horizontal scaling:

1. Update the number of replicas in the ```restoct-deployment.yaml``` file:
  ```
  replicas: 4
  ```
2. Apply the changes:
  ```
  kubectl apply -f restoct-deployment.yaml
  ```
3. Monitor the load distribution and resource usage:
  ```
  kubectl get pods
  kubectl top pods
  ```
## Conclusion
This project highlights the importance of modern development and deployment practices, including:

* Containerization: Ensures portability and consistency across environments.

* Kubernetes Orchestration: Provides automated scaling, load balancing, and fault tolerance.

* HATEOAS: Enhances API usability by enabling dynamic navigation.

* GitOps: Simplifies continuous deployment and synchronization with Git repositories.

* By increasing the number of replicas in Kubernetes, the system demonstrated improved resilience, scalability, and performance under load. However, it also highlighted the need for effective resource monitoring and optimization to balance performance and cost.
