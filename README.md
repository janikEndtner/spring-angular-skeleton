# Purpose of this project
This is a basic project for creating further projects based on Spring Boot and Angular.
It integrates following concepts:
- Spring Boot with Spring Boot Security
- Angular with Angular Material
- JWT token authentication
- Flyway database migration
- building and running with docker

## How to run for development
1. start database on port 3307
2. start front end
3. start back end

## How to install for deployment with docker
1. build front end: `ng build --prod in web folder`
2. build back end: `mvn install`
3. create docker image from Dockerfile: `docker build -t spring-angular-skeleton:spring-angular-skeleton . ` 
4. run with docker-compose: `docker-compose up`
