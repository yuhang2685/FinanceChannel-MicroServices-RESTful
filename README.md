## My-Finance-Channel-Service
Author: Dr. YUHANG ZHAO

[![PyPI license](https://img.shields.io/pypi/l/ansicolortags.svg)](https://pypi.python.org/pypi/ansicolortags/)
[![Open Source Love svg3](https://badges.frapsoft.com/os/v3/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

### Overview
The finance web service manages user data and processes real-time finance market data retrieved from "Alpha Vantage".

### Features
- User watchlists are maintained in `MySQL DB`
- `JPA / Hibernate` for data management
- `Microservice` architecture
- `RESTful APIs` for data communication among internal microservices and with external web services / UI
- `Eureka service registry` to hide back-end microservices from external requests
- Powered by `Spring Boot`

### Architecture
![Architecture](https://github.com/yuhang2685/My-Finance-Channel-Service/blob/master/SystemArchitecture.jpg)
- `Alphavantege-API-client`: The microservice to process real-time finance data retrieved from Alpha Vantage
- `DB Service`: The microservice to maintain user watchlists in MySQL DB through JPA/Hibernate
- `Stock Service`: The microservice exposed to the external requests
- `Eureka Service`: The service registry

### Related Projects
Web UI resides at [Stock-WatchList-Angular](https://github.com/yuhang2685/Stock-WatchList-Angular)

### Environment
- Java 1.8
- Spring Boot v2.1.7.RELEASE
- MySQL 8.0
- spring-cloud Greenwich.SR2

### License
[![PyPI license](https://img.shields.io/pypi/l/ansicolortags.svg)](https://pypi.python.org/pypi/ansicolortags/)
