Brief about API Gateway
==============================

API Gateway is a common pattern in microservice that has following characteristics

* To avoid direct client to microservice communication that enables loosely coupled and high cohesive system
* To enable more control to micorservice in managing its lifecycle that includes refactoring, split big microservice 
into small microservices, merge small microservices into a larger one and so on
* Provides single entry point into the system similar to Facade pattern
* Provides responsibilities such as authentication, monitoring, load balancing, caching, request shaping
and management, and static response handling
* Enables request routing, composition, and protocol translation
* Provides necessary API throttling
		
AAP Gateway API Features:
==============================

* AAP Gateway API tries to mimic some of the capabilities of real Gateway
* Provides necessary caching of request and response to reduce load on actual microservices
* Manages the necessary endpoints in a config file. In an actual implementation, Gateway can interact 
with service registries such as consul or kuberneteos to discover the service endpoints. It provides proxy 
to the following services:  pet_family_service, pet_color_service, pet_usagemetrics_service, and status

	https://github.com/rmadabusi/aap_poc/blob/developing/api_gateway/aap/config/config.py
	
* Swagger is exposed on the API Gateway endpoint to perform necessary request and response testing

	http://54.164.45.232:3000/apidocs/index.html
	
* Takes client request and extract necessary parameters to track usage metrics and calls a microservice to 
persist the metrics in the data store. These metrics are visualized using Kibana. This is a typical responsibility 
of an API Gateway that does some request-response payload transformation. Sample code is below:
	
~~~~
def pet_usage_metrics(params):
	url = URL_HEADER_UM+GET_UM_DETAILS
	data = params
	req = urllib2.Request(url)
	req.add_header('Content-Type', 'application/json')
	response = urllib2.urlopen(req, json.dumps(data)).read()
	return response
~~~~
         
Flasgger (along with Swagger):
==============================

Flasgger provides an extension (Swagger) that inspects the Flask app for endpoints containing YAML docstrings with Swagger 2.0 Operation objects.

http://54.164.45.232:3000/apidocs/index.html

Getting started:
================

create a virtualenv using the command "mkvirtualenv AAP_GATEWAY_API".

Installation Dependencies:

pip install flask==0.10
pip install flasgger

Run - python main.py

Basic pet utilities Services:
=============================

* **Families Service:** It provides details about the families based on the clan id.
* **Color Service:** It provides details about the colors based on the clan id.

Status services:
================

**Status Service:** It provides details about the status of all micro services, Database, and Database responses.


