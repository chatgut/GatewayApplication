# GatewayApplication
Gateway service that act as a single entry point for all client requests to the backend services in a microservices architecture.

## Architecture
### The Gateway Application routes client requests to various microservices, such as:
* Auth Service: Handles authentication
* Micro Post Service: Manages posts
* Worth Reading Service: Manages reading recommendations
* Micro URL Shortener Service: Shortens URLs
* Image Service: Manages images
* User Service: Manages user information
---
## How to run
* mvn clean package
* docker compose up --build gateway -d
---
### Ports:
Gateway run on port:8081
<br>

**Needs :**
  - MySQL on port: 3306
  - MongoDB on port: 27017
  - RabbitMQ on ports: 15672, 5672
---
## Services used in application
**AuthService**
<https://github.com/chatgut/AuthService2>

**Chat messages**
<https://github.com/chatgut/microPostService>

**Like messages**
<https://github.com/chatgut/worthreadingservice>

**Url-Shortener from frontend**
<https://github.com/chatgut/micro-url-shortener-service> 

**Images upload**
<https://github.com/chatgut/ImageServiceBoV>

**User profiles**
<https://github.com/chatgut/UserService>


