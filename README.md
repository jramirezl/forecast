# Forecast Reader

## Considerations

Springboot framework was chosen for the solution. Added the credentials received from ``openweathermap`` to the the file,, ``application.yml``

Once it was choosen springboot,  tried to understand de API offered by: ``openweathermap``
Some online tools used to generate the classes, havig the JSON received from the ``openweathermap`` API as input.

Tested using: ``London`` as parameter.

For testing, the happy path it was just considered. 

Two services were used, each one with its own concern: 

* **WeatherRestService**: Responsible for getting the REST endpoint from the API. 
    
* **ForecastService** Responsible for processing all the data and calculate the average. 

In the testing load the JSON sample file to load the ``ResponseDto`` which is the root object ``openweathermap`` from the response
of the API.

If there is an error on the input it will return ``400 code`` on sucesss it will return ``200 code`` and if is there is an error on the API will return ``500 code``

To calculate the average of the following dates, it considered the dates after the current date (tomorrow included) and 3 days were counted. 

For testing purpose I created 2 Providers Components
* TimeStampProvider to get the current time and avoid to use PowerMockito in testing
* ClientBuilderProvider to get the JAX-RS client and avoid to use PowerMockito in testing

### Requirements

You must have installed jsdk 1.8 and the latest gradle version.

### Installation

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

``localhost`` could vary depending on where you installed the application, please replace it accordingly.

### Libs and frameworks used 

The next are the libs and tools used to work properly:

* [Springboot] - makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss
* [Gradle] - Gradle helps teams build, automate and deliver better software, faster.
* [Swagger] - Swagger aides in development across the entire API lifecycle, from design and documentation, to test and deployment.
* [JAX-RS] - High-level interfaces and annotations used to create RESTful service resources.
* [Project Lombok] - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.

### To Do (things to improve)

* Improve tests, just happy path test were created
* Fix WeatherRestServiceUnitTest test
* Improve controller input validation
* Manage better the exception when the connection to the ``openweathermap`` API fails
* Manage when a city doesn't exist







[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Springboot]: <https://github.com/spring-projects/spring-boot>
   [Gradle]: <https://gradle.org>
   [Swagger]: <https://swagger.io/>
   [JAX-RS]: <https://github.com/eclipse-ee4j/jaxrs-api>
   [Project Lombok]: <https://projectlombok.org/>
