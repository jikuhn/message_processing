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

## Processing Requirements

There is a set of classes which are dedicated to message processing - _processors_.
Chain of Responsibility design pattern is used. Each processor class does one
dedicated task.

* All sales must be recorded

See *RecorderProcessor* which records messages with sales.

* All messages must be processed

See *MessageProcessor* abstract class and its *process()* method.

* After every 10th message received your application should log a report detailing the number
  of sales of each product and their total value.

See *ContinuousReportProcessor*.

* After 50 messages your application should log a report of the adjustments that have been made to each sale type

See *FinalLogProcessing*.

Messages are generated randomly. There is *MessageReader* interface to implement different way how to get messages. 

  