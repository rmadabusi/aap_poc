FROM ubuntu:14.04
MAINTAINER TRESELLE

###  Installation of python with related packages ###
RUN apt-get update
RUN apt-get -y install python2.7 python-pip
RUN pip install Flask==0.10 flasgger Flask-Cache 

### Set the pythonpath ### 
ENV PYTHONPATH /api_gateway:/usr/local/lib/python2.7/:/usr/local/lib/python2.7/dist-packages:$PYTHONPATH

### Create a directory and copy the python service files ####
RUN mkdir /api_gateway
COPY dist/. /api_gateway

### Run the main python service file ##
ENTRYPOINT ["/usr/bin/python2.7", "/api_gateway/aap/service/main.py"]


