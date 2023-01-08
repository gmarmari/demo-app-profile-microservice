# demo-app-profile-microservice

Just a demo project.
A microservice for the user profile of my demo spring application.
A user can read / write / update / delete its personal data, addresses and payment methods.

 # Java
 It requires Java version 17

# Build the App

```
./mvnw clean package
./mvnw spring-boot:run
 ```

### Run with test profile
```
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```
 
 
 ### Or with docker
 ```
 docker-compose up --build

 ```
 
 # Database
 This app is with postgres configured. See application.properties.
 
 If you need a database local you can use
 ```
 docker-compose up -d profile-database
  ```
 
 Or use the "test" spring profile: 
 
  ```SPRING_PROFILES_ACTIVE=test ```
  
  With this profile a h2 databse will be used.
 
 # Api Docu
 
 * http://localhost:8003/swagger-ui/index.html
 * http://localhost:8003//v3/api-docs/
 
 # Demo App
 
 ## Architecture

![demo-app](https://user-images.githubusercontent.com/42716524/211201373-b0fd0c31-5c82-4d87-ade3-a6f9bc2c9334.png)

#### Created with `http://www.plantuml.com/plantuml/uml/`
URL to decode:
```
//www.plantuml.com/plantuml/png/dLBDIWCn4BxdAORUnmVeeHHKF8cYuANq49EfhcutCfaeHVhkagLhqPcoIsyX-NxopJVBB9wbR5ldWoI6sLEXYxSCeMseanduuUXSQ5E9yEq0LZTUwClly97uimcqTdlN9I6Gz05fez_dS5sOtZrSQgqxZiGPRvl0AGzw0D6BV_QPUj0HTdMuhf3zVrijfQt_UDzpYYN8cFk06x5NQcFPAsFJj3HYVyIDsLVGTFT3E_X0BqqMtgaQ5U1NhRuGzyEiie2uiDR2gQkAj8k2EN0gGZP7JtSogn-AJNBsJn2DVJlr0KHpFquu8-esxJcjei-l5DUuq4Nq2VepiPrXEby7EqF1pZfPO4bTBDls1m00
```

## Start Demo App
If you have docker on your system you can start the demo App this way:
* Clone demo app Repositories:
```
git clone https://github.com/gmarmari/demo-app-gateway-service.git
git clone https://github.com/gmarmari/demo-app-orders-microservice.git
git clone https://github.com/gmarmari/demo-app-products-microservice.git
git clone https://github.com/gmarmari/demo-app-profile-microservice.git
git clone https://github.com/gmarmari/demo-app-service-registry.git
```

* Execute clean package and docker-compose up for every project:

```
cd demo-app-service-registry
./mvnw clean package 
docker-compose up -d --build

cd ../demo-app-orders-microservice
./mvnw clean package 
docker-compose up -d --build

cd ../demo-app-products-microservice
./mvnw clean package 
docker-compose up -d --build

cd ../demo-app-profile-microservice
./mvnw clean package 
docker-compose up -d --build

cd ../demo-app-gateway-service
./mvnw clean package 
docker-compose up -d --build
```

* Open `localhost:8080` in your browser to test the app
