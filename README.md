# WordSearchService
Word Search Service App


Spring Boot based application that uses Rest Call to return a random word count or can be extended to user passing in a queryParam to search specific word.

prerequisite:
  Maven 3
  Java 8
  

Command to build and run :
1. open terminal / putty window (for mac users / linux user)
2. cd to the project directory
3. run command mvn test spring-boot:run

By default, Spring Boot runs on port 8080

Open browser and navigate to : http://localhost:port/randomWord
  
The above REST call with no query parameter returns random word searched. You can add the following query parameter to refine the search and also find occurences of the word count

http://localhost:8080/randomWord?wordToSearch=aaliis&searchOccurrences=true


Following Spring Boot actuator feature can be used to check the health, metrics and other features that are handy
http://localhost:8080/actuator/health
http://localhost:8080/actuator/metrics

http://localhost:8080/actuator
