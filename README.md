**Quiz Application**

**Problem Statement**
Description: The application aims to provide a dynamic and interactive platform for quizzes, catering to educators, students, and quiz enthusiasts.
Challenge: Originally a monolithic app, it was refactored to microservices to address scalability and maintenance challenges.

**Tech Stack**
Java: Core programming language.
Spring Boot: Framework for building backend services.
Lombok Library: Reduces boilerplate code.
Spring JPA: For database interactions.
PostgreSQL: Relational database management system.

**Features**

Dynamic Question Generation: Generate quizzes with random questions based on category.

RESTful APIs: Expose endpoints for creating, updating, and managing quizzes and questions.

Microservices Architecture: Scalable design with decoupled services for quizzes and questions.

Score Calculation: Validate user responses and calculate scores efficiently.

Service Discovery: Integration with Netflix Eureka for microservice registration and lookup.

API Gateway: Centralized routing and load balancing using Spring Cloud Gateway.

**End Product**
The backend was developed without a user interface. Testing of API endpoints and functionality, including adding questions, creating quizzes, retrieving quiz questions, and getting quiz scores, was performed using Postman and web browsers.

**Future Work and Ideas**
Create User Interface: Design a user-friendly interface for quiz creation and participation.

User Roles: Implement roles such as Admin (create quizzes) and Users (take quizzes).

Security: Implement OAuth/JWT for secure login.

Service Registry: Explore using Consul for service discovery.
Caching: Use Redis for caching frequently used quiz data.
Health Checks: Add endpoints to monitor service status.
