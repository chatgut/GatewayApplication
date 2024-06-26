# GatewayApplication
Gateway service that act as a single entry point for all client requests to the backend services in a microservices architecture.

## Architecture
### The Gateway Application routes client requests to various microservices, such as:
* Micro Post Service: Manages posts
* Worth Reading Service: Manages reading recommendations
* Micro URL Shortener Service: Shortens URLs
* Image Service: Manages images
* User Service: Manages user information
---
## How to run
* Upload frontend (branch gateway-only) from https://github.com/chatgut/frontend
  - Create image for frontend:
  - run mvn clean package
  - docker build -t frontend123:latest .
### In the GatewayApplication
 * run docker compose up --build gateway -d
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


