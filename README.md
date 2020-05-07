# Proof of Concept Backend

[![Build Status](https://travis.ibm.com/Luis-Miguel-Mendes/refactor-app-server.svg?token=KydXo9uqTG45DhTUTav4&branch=master)](https://travis.ibm.com/Luis-Miguel-Mendes/refactor-app-server)

## Getting Started

### Installing Postgre SQL database
You need to install Postgre SQL database (database name postgres, user postgres, password postgres on port 5432) or with some other specification (then change server.xml file to reflect that)

### Building application

Application is configured to be build with Maven. To build the application run following command:
```
mvn clean validate install
```
This will compile sources, run unit tests, create WAR file and run integration tests using Docker containers (if you are getting an error about unsuccessful db2 drivers dependency, run just mvn validate to correct it). You need to have Docker installed and running on the machine during build. If you want to skip integration tests, use command:
```
mvn clean validate package
```
After successful build you should be able to locate file `target/or-persist-api.war`.

### Running application using Maven
The application is using Open Liberty server instance that is handled directly by Maven using IBM-made plugin. 

Run server in the background:
```
mvn package liberty:start-server -Pliberty -DskipTests
```
Run server in the foreground:
```
mvn package liberty:run-server -Pliberty -DskipTests
```
After initializing JPA persistance (e.g. by calling some persistance operation) DDL statements are created in target\liberty\wlp\usr\servers\or-persist-api-server\ folder (createDDL.jdbc, dropDDL.jdbc files)
The important directory to check is then `target\liberty\wlp\usr\servers\or-persist-api-server` where you can find for example server and application logs.

To stop the server simply run the command:
```
mvn liberty:stop-server -Pliberty
```
Note: You don't need to manually execute stopping of the server before build execution because that is handled automatically.

### Running application using Docker

#### Create image using CLI
Run following command from project root directory:
```
docker build . -t or-persist-api
```
This will create image `or-persist-api:latest` in local Docker
#### Create image using Maven
Add `docker` profile to Maven build to create image in local Docker:
```
mvn clean package -Pdocker
```
#### Run container
```
docker run -d -p 9080:9080 or-persist-api
```

### Interacting with application
We recommend to use OpenAPI/Swagger:
- http://localhost:9080/openapi/ui/ - to see UI with documentation and test tools
- http://localhost:9080/openapi/ - to see generated file

### Debugging
It is possible to debug running Open Liberty using Remote Debug by connecting to `7777` port.


