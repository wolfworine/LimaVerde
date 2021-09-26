# sp-mailing

Mailing Service.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

# Setup the Application

Run the command:

```shell
mvn clean install
``` 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `pe.com.prima.spmailing.SpMailingApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run -P [PROFILE]
```
Or 

```shell
java -jar -Dspring.profiles.active=[PROFILE] [JAR]
```

Now that the application is running, you can test it.

### Accessing Resources:

Use `curl` for example.
Now you have 1 REST Web Services you can test:

[POST] `http://localhost:8080/mailing/v1/237697` Web service that sends emails to a list of users.

[source,sh]
----
$ curl -s -d '[{"email": "gprieto@soaint.com","additionalValues": {"name": "Giselle","phoneNumber":"+535362316"}]' -H "Content-Type: application/json" -X POST http://localhost:8080/mailing/v1/237697
----

The reply should be none.

## Test, coverage with Jacoco and Sonarqube

To execute the application tests run the command:

```shell
mvn test
```
### Coverage with Jacoco and Sonarqube

Keep your terminal on root folder of project, and then execute this maven command to connect with Sonarqube.

```shell
mvn sonar:sonar
```

Or

```shell
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<the-generated-token>
```

### Build docker image

```shell
docker build -t channel/sp-mailing .
```

### Run the container

```shell
docker run -p 8080:8080 channel/sp-mailing:latest
```