# Contract Testing Boilerplate

Contract Testing Boilerplate has two microservices developed using Spring Boot in a maven multi-module project.

  - Date Provider MicroService - /provider/validDate - Validates whether given date is a valid date or not
  - Age Consumer MicroService - /age-calculate - Returns age of a person based on given date
  
  
![Microservices](images/microservices.png)

## What is Contract Testing?
An integration contract test is a test at the boundary of an external service verifying that it meets the contract expected by a consuming service.


## What is PACT?
Pact is a contract testing tool. Contract testing is a way to ensure that services (such as an API provider and a client) can communicate with each other. Without contract testing, the only way to know that services can communicate is by using expensive and brittle integration tests.

