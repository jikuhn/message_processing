# Message Processing

## Compiling and running the project

Messaging Processing application is a Java project built by gradle (tested with
gradle version 4.2). To compile and run tests do:

    gradle build
    
To execute the application with a sample data do:

    gradle run
    
## Dependencies

There is no external jar required to execute the application. Three dependent jar 
libraries are introduced for testing. But in fact, two of those libraries are "transitive"
dependencies for Spock testing framework.