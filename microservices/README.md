-----------------------------------------
Run Microservices Demo with Docker 
-----------------------------------------
	
This project creates microservice demo system in Docker containers. The services are implemented in Java using SpringBoot & Spring Actuator.

It uses five microservices:

1. Populate Clan Names. 
2. Populate Family Names for the Pets.
3. Populate the Colors of the Pets.
4. Storing Usage Metrics data.
5. Actuator Services.

Technologies
--------------
API Gateway - Flask & Python.
Microservice  -	Spring Boot, Spring Actuator & Java.
Data Store - ElasticSearch.
Metrics UI	- Kibana.

How To Run
---------------
The demo can be run with Docker Machine and Docker Compose.

Remarks on the Code
--------------------
The servers for the infrastruture are pretty simple thanks to Spring Cloud:

 - aap/pets/clans is the Clans Service to Populate Pet Names, Family Names & Color Names of the respective Pets. 
 - aap/pets/families is the Family Service to populate the family name/names for the given family id/ids. 
 - app/pets/colors is the Color Service to populate the color name/names for the given color id/ids.
 - aap/pets/status is responsible to give the status of the service. It is designed to return true if the service is alive. 


Restful Microservice Definitions
---------------------------------
 
Pet Family Service 
This service runs in 2001 port. Use the below link to run the service. We can pass the family ids in the parameter.

host: {ip}
port: 2001
path: /aap/pets/families/
family id: 1
Service Url: http://54.164.45.232:2003/aap/pets/families/1
For multiple families we can pass family ids as comma seperated values
http://54.164.45.232:2001/aap/pets/families/1,970,971,972,973,974


Pet Color Service
This service runs in 2002 port. Use the below link to run the service. We can pass the family ids in the parameter.

host: {ip}
port: 2002
path: /aap/pets/colors/
color id: 1
Service Url: http://54.164.45.232:2002/aap/pets/colors/1
For multiple color we can pass color ids as comma seperated values
http://54.164.45.232:2002/aap/pets/colors/1,45,46,47,59


Pet Clan Service
This service runs in 2003 port. Use the below link to run the service. We can pass the family ids in the parameter.

host: {ip}
port: 2003
path: /aap/pets/clans
family id: 1
Service Url: http://54.164.45.232:2003/aap/pets/clans/1
For multiple Clan we can pass clan ids as comma seperated values
Service Url: http://54.164.45.232:2003/aap/pets/clans/1,2,3,4,5,6 


Pet Usage Metric Service
This service is a POST service, this services allows to post the metric data collected during the request action travelling through the API gateway.

host: {ip}
port: 2000
path: /aap/pets/usagemetrics
Service Url: http://54.164.45.232:2000/aap/pets/usagemetrics - POST service 
		
		
Spring Actuator Services
These services are provided by Spring Actuator package. These service can be used to understand the Health, Metric details of the services.

Spring Actuator Mapping Service: This service will print the mapping details of the available services.
host: {ip}
port: 2000
path: /aap/mappings
Service Url: http://54.164.45.232:2000/aap/mappings.

Spring Actuator Health Service:	This Service will show the health status of the services.
host: {ip}
port: 2000
path: /aap/mappings
Service Url: http://54.164.45.232:2000/aap/health

Spring Actuator Metrics Service:  This service prints the metric details of the respective services.
host: {ip}
port: 2000
path: /aap/mappings
Service Url: http://54.164.45.232:2000/aap/metrics.
