# Adopt A Pet (AAP) Proof of Concept #

## PoC High Level Requirements ##

* Implement the family and colors API endpoints from the Swagger docs using preferred backend
  * Build a responsive that uses these APIs to construct search forms for each clan (cat, dog, etc)	from the sample data provided. The form does not need to be able to submit for this PoC.
  * **Final Deliverables:**
    * Code to implement this microservice with unit and integration tests
    * A docker file for creating a container for the microservice
    * CI scripts


## High Level Interaction Model ##

![Alt text](/AAP%20Tech%20Stack.png?raw=true)


## Technologies Chosen ##
  
Layer		  | Technologies  | Details
------------- | ------------- | -------------
App  		  | AngularJS, JQuery, Underscorejs, SASS, Bootstrap  | Uses AngularJS widgets and directives concept for high modularity. JQuery is used for type ahead feature
API Gateway   | Flask & Python  | A pass-thru proxy API that does caching and other pre or post processing as needed
Microservice   | Spring Boot & Java  | Small services that does one thing. Both family and color API can be done in one microservice but to demonstrate the microservice concept, we made them into 2 microservices and an operational microservice that tracks user access
Data Store   | ElasticSearch  | AAP is basically search based web app and ElasticSearch is the best choice as it provides lot of search capabilities such as full text, keyword text, facets, aggregations, Did you mean, Similar or Related, Geospatial, and so on. Its fast as it is index based and natively stored in JSON which doesn't need lot of resultset transformation
Metrics UI   | Kibana  | A complimentary visualization framework on top of ElasticSearch and can be used to perform creative visualization on the data stored in elasticsearch indices. To demonstrate the capabilities, we have created visualizations on top of clans metadata and also usage metrics
Data Loader   | Java Program  | To populate CSV metadata into ElasticSearch and initializes the indices



  
