from __future__ import division
import json
from flask import Flask
from flask import request
from urllib import unquote
from aap.service.implementations.main_services import get_status, get_family_details, get_colors_details, pet_usage_metrics
from flasgger import Swagger
from flask.ext.cache import Cache

__author__ = "Treselle"

app = Flask(__name__)
Swagger(app)
cache = Cache(config={'CACHE_TYPE': 'filesystem', 'CACHE_DIR': '/tmp'})

##################################################################################################
# REST API: AAP- Micro services
#
# Resource: /status
#   GET     -- get service status record

# Resource: /pets/families
#   GET     -- get service for pets family record

# Resource: /pets/colours/
#   GET     -- get service for pets colours record


##################################################################################################

#Get all service status
@app.route("/status", methods=["GET"])
def api_status():
    """
    User API
    This resource returns user information
    ---
    tags:
      - status
    parameters:
      - name: username
        description: status for the corresponding services
        in: path
        type: string
        required: false
    responses:
      200:
        description: status for the corresponding services
        schema:
          id: user_response
          properties:
            status:
              type: int
              description: The username
              default: some_status
    """
    result = get_status()
    return json.dumps(result)

#For getting family details based on Pet family ids
@app.route('/pets/families/<id>', methods=["GET"])
def api_family_details(id):
    """
    User API
    This resource returns basic pet family information
    ---
    tags:
      - basic pet utilities
    parameters:
      - name: id
        in: path
        description: The value corresponding to a primary_family_id or secondary_family_id of a pet
        type: string
        required: true
    responses:
      200:
        description: Because it is possible for multiple families to be returned from this API Query, the JSON response will always return an Array of Objects, even if there is only one Object returned
        schema:
          id: user_response
          properties:
            id:
              type: string
              description: Unexpected error
              default: some_id

    """
    result = get_family_details(id)
    return result

#For getting color details based on Pet color ids
@app.route('/pets/colors/<id>', methods=["GET"])
def api_color_details(id):
    """
    User API
    This resource returns basic pet colours information
    ---
    tags:
      - basic pet utilities
    parameters:
      - name: id
        in: path
        type: string
        description: The value corresponding to a color_id of a pet
        required: true
    responses:
      200:
        description: Because it is possible for multiple colors to be returned from this API Query, the JSON response will always return an Array of Objects, even if there is only one Object returned
        schema:
          id: user_response
          properties:
            id:
              type: int
              description: Unexpected error
              default: some_id
    """
    result = get_colors_details(id)
    return result

#For Tracking Usage metrics Details
@app.route("/pets/usage_metrics", methods=["GET", "POST"])
def dsn_api_entity_find_raw():
    """
    Create a new user
    First line is the summary
    All following lines until the hyphens is added to description
    ---
    tags:
      - users
    parameters:
      - in: body
        name: body
        schema:
          id: User
          required:
            - ip

          properties:
            ip:
              type: string
              description: team for user
    responses:
      201:
        description: User created
        schema:
            type: array
            items:
                $ref: '#/definitions/User'
    """
    params = {}
    if request.method == "GET":
        try:
            params = json.loads(request.args.get("params", "{}"))
        except:
            token_remove_params = (request.query_string.replace ("&", " ").partition("token"))[0]
            params = json.loads(unquote(token_remove_params)[7:])
    elif request.method == "POST":
        params = json.loads(request.data).get("params", "{}")
    entities_recs = pet_usage_metrics(params)
    return entities_recs

#Main Function
if __name__ == '__main__':
    app.run(host="localhost", port=3000)