# Forecast Reader

## Considerations

Springboot framework was chosen for the solution. The credentials received from ``openweathermap`` to the the file, were added to the``application.yml`` file. 

Once Springboot was chosen, tried to understand the API offered by: ``openweathermap``
Some online tools used to generate the classes that helps to retrieve the data. The data is received in JSON format after, a``openweathermap`` API call is made. 

Tested using: ``London`` as parameter.

For testing, the happy path was just considered. 

Two services were used, each one with its own concern: 

* **WeatherRestService**: Responsible for getting the REST endpoint from the API. 
    
* **ForecastService** Responsible for processing all the data and calculate the average. 

In testing, a load JSON mechanism was used. Once the file is opened the content is loaded into``ResponseDto`` intance which is the root object ``openweathermap`` from the response
from the API.

If there is an error on the input it will return ``400 code`` on sucesss it will return ``200 code`` and if there is an error on the API it will return ``500 code``

To calculate the average of the following dates, it considered the dates after the current date (tomorrow included) and 3 days were counted. 

For testing purposes, I created 2 Provider Components:
* TimeStampProvider to get the current time and avoid used PowerMockito in testing
* ClientBuilderProvider to get the JAX-RS client intance and avoid using PowerMockito in testing

### Requirements
You must have jsdk 1.8 installed and the latest gradle version.

### Ins

The installation process will compile the code and run the tests, open a console and
run 

```sh
    gradle build
```

### Run the Application

The installation process will compile the code and run the tests, open a console and
run 

```sh
    gradle booRun
```

Open the browser and open the following URL
```http://localhost:8080/swagger-ui.html```

``localhost`` could vary depending on where the application has been installed. Please replace it accordingly.

### Libs and frameworks used 

The next are the libs and tools used to work properly:

* [Springboot] - makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss
* [Gradle] - Gradle helps teams build, automate and deliver better software, faster.
* [Swagger] - Swagger aides in development across the entire API lifecycle, from design and documentation, to test and deployment.
* [JAX-RS] - High-level interfaces and annotations used to create RESTful service resources.
* [Project Lombok] - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.

### To Do (things to improve)

* Improve tests, just happy path test were created,  add more scenarios 
* Fix WeatherRestServiceUnitTest test
* Improve controller input validation
* Manage the exception better when the connection to the ``openweathermap`` API fails
* Manage exceptions when a city doesn't exist







[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Springboot]: <https://github.com/spring-projects/spring-boot>
   [Gradle]: <https://gradle.org>
   [Swagger]: <https://swagger.io/>
   [JAX-RS]: <https://github.com/eclipse-ee4j/jaxrs-api>
   [Project Lombok]: <https://projectlombok.org/>
