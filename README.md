To run this Spring boot application you need to have Java and gradle installed.

On my terminal this command java -version produced this output

This is a confirmation of Java installation (example):

java -version
java version "11.0.8" 2020-07-14 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.8+10-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.8+10-LTS, mixed mode)

This is a confirmation of Gradle installation (example):

gradle -v

------------------------------------------------------------
Gradle 5.6.2
------------------------------------------------------------

Build time:   2019-09-05 16:13:54 UTC
Revision:     55a5e53d855db8fc7b0e494412fc624051a8e781

Kotlin:       1.3.41
Groovy:       2.5.4
Ant:          Apache Ant(TM) version 1.9.14 compiled on March 12 , 2019,
JVM:          11.0.8 (Oracle Corporation 11.0.8+10-LTS)
OS:           Mac OS X 10.16 x86_64

I'm running gradle version Gradle 5.6.2.
gradle build will build and execute test cases.

On the terminal gradle bootRun will launch the application.

Application uses in memory database H2.

Using liquibase for database migration.

On start up liquibase will create and populate database tables.

For purpose of the assessment, I only have 1 table which is stock.

The stock is populated with data on start up.

Unit tests

I created StockServiceTest which evaluates the logic implemented in the StockService.

StockService encompasses the logic to calculateTotal price.

Integration Test

StockControllerTest performs an integration test.
