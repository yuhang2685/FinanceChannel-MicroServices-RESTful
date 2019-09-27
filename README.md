# Stock-Price-Viewer-Microservices
Author: Dr. YUHANG ZHAO

## Overview
The finance web service retrieves and process real-time finance data from Alpha Vantage.

## Features
- User watchlist are maintained
- JPA / Hibernate for automatically maintaining data
- Individual services are implemented as Microservice
- RESTful APIs for data communication between services
- Eureka for microservice registration
- Powered by Spring Boot

## Architecture
- Alphavantege-API-client: The microservice to retrieve and process real-time finance data from Alpha Vantage
- db-service: The microservice to maintain MySQL DB through JPA/Hibernate
- stock-service: The microservice exposure to external requests 
- eureka-service: Manage microservices

## Related Projects
Front-end resides at [Stock-WatchList-Angular](https://github.com/yuhang2685/Stock-WatchList-Angular)

## Environment
- Java 1.8
- Spring Boot v2.1.7.RELEASE
- MySQL 8.0
- spring-cloud Greenwich.SR2
