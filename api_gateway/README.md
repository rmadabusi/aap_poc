

It contains three services

* pet_family_service
* pet_color_service
* pet_usagemetrics_service
* status


Flasgger (along with Swagger):
==============================

Flasgger provides an extension (Swagger) that inspects the Flask app for endpoints containing YAML docstrings with Swagger 2.0 Operation objects.

Getting started:
================

create a virtualenv using the command "mkvirtualenv AAP_GATEWAY_API".

Installation Dependencies:

pip install flask==0.10
pip install flasgger

Run - python main.py

Web URL:
========

http://54.164.45.232:3000/apidocs/index.html

The page created using the above URL will include the following services:

Basic pet utilities Services:
=============================

* **Families Service:** It provides details about the families based on the clan id.
* **Color Service:** It provides details about the colors based on the clan id.

Status services:
================

**Status Service:** It provides details about the status of all micro services, Database, and Database responses.


