# Stock-Price-Viewer-Microservices
Author: Dr. YUHANG ZHAO

## Overview
The finance web service manages user watchlist and processes real-time finance market data retrieved from Alpha Vantage.

## Features
- User watchlists are maintained in DB
- JPA / Hibernate for data management
- Microservice architecture
- RESTful APIs for data communication among internal microservices and with external web services / UI
- Eureka service registry to hide back-end microservices from external requests
- Powered by Spring Boot

## Architecture
- Alphavantege-API-client: The microservice is a java library to process real-time finance data retrieved from Alpha Vantage
- DB Service: The microservice to maintain user watchlists in MySQL DB through JPA/Hibernate
- Stock Service: The microservice exposed to the external requests
- Eureka Service: The service registry

## Related Projects
Web UI resides at [Stock-WatchList-Angular](https://github.com/yuhang2685/Stock-WatchList-Angular)

## Environment
- Java 1.8
- Spring Boot v2.1.7.RELEASE
- MySQL 8.0
- spring-cloud Greenwich.SR2
