Swiffer Service
====================

Spring batch app that reads, processes and reports on files of a specific structure

# To setup in IntelliJ:
1. In mac terminal, navigate to your git directory and enter 'git clone https://github.com/mmyrland/swiffer.git'

2. Open Intellij and close all projects

3. Select “Import Project”

4. Select the 'build.gradle' under the 'swiffer' directory you just cloned

5. In the resulting dialog, check the following boxes/radio buttons:
     * Use Auto Import
     * Create directories automatically
     * Select “Use default grade wrapper (recommended)”

6. Ensure your Gradle JVM is pointed to your 1.8 directory

7. Hit OK and get to work.

## To build the application:
* From the root directory, run `./gradlew build` (for Unix systems) or `gradlew.bat build` (for Windows systems)


## To run the application:
* From the root directory, run `./gradlew -Dspring.profiles.active=local bootRun` (for Unix systems) or `gradlew.bat bootRun` (for Windows systems)
* When you see a message like, `Started mmyrland.Application in *n* seconds` the application is running. You can access it
  via `http://localhost:8080`, and with `ctrl-c` to quit the application.


API Documentation - REST Docs
====================
## Api documentation:
* From project directory, run `./gradlew build jar`
* Create an application-local.properties file in the main resources directory using the application-local.properties.template as a guide
* Run `java -jar -Dspring.profiles.active=local build/libs/swiffer-service.jar`
* Go to http://localhost:8080/docs/index.html