# PollingStation




## Technologies
* Spring Boot 3.0
* Maven
* Swagger
* Lombok
* Mysql

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:


* Build the project: mvn clean install
* Create docker image: docker build -t task .
* Instantiate a database and start the application: docker-compose up -d
* Shutdown the application: docker-compose down -v


* The application will be available at http://localhost:6161.
* Swagger endpoint http://localhost:6161/swagger-ui/index.html#/
