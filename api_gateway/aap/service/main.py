from __future__ import division
from flask import Flask, request,jsonify
import json
from api_gateway.aap.service.implementations.main_services import get_status, get_family_details, get_colors_details, pet_usage_metrics, get_cache_clear, get_clan_details
from urllib import unquote
from flasgger import Swagger
from flask.ext.cache import Cache

__author__ = "Treselle"

app = Flask(__name__)
Swagger(app)

# Check Configuring Flask-Cache section for more details
cache = Cache(app,config={'CACHE_TYPE': 'simple'})

#Get all service status
@app.route("/status", methods=["GET"])
def api_status():
    """
    User API
    This resource returns status for the corresponding services
    ---
    tags:
      - status
    responses:
      200:
        description: status for the corresponding services
        schema:
          id: user_response
          properties:
            status:
              schema:
                properties:
                  some_key:
                    type: int
                    description: The username
                    default: some_values
    """
    result = get_status()
    return json.dumps(result)

#For getting family details based on Pet family ids
@app.route('/pets/families/<id>', methods=["GET"])
def api_family_details(id):
    """
    User API
    This is a supplemental API to provide the names that correspond to the IDs returned/submitted along with the pet_search and pet_details Queries for the primary_family_id and secondary_family_id Parameters/Properties.
    ---
    tags:
      - basic pet utilities
    parameters:
      - name: id
        in: path
        description: Provides details about the primary_family_id or secondary_family_id of a pet of a pet, where the value can be a single clan id or multiple clan ids with comma-separation. For example 1 or 1,2,3
        type: string
        required: true
    responses:
      200:
        description: Because it is possible for multiple families to be returned from this API Query, the JSON response will always return an Array of Objects, even if there is only one Object returned
        schema:
          id: user_response
          properties:
            response:
              schema:
                properties:
                  some_key:
                    type: int
                    description: Unexpected error
                    default: some_values
    """
    result = get_family_details(id,cache)
    return jsonify(result)

#For getting color details based on Pet color ids
@app.route('/pets/colors/<id>', methods=["GET"])
def api_color_details(id):
    """
    User API
    The Pet Colors Query shall be sent to the URL /pet_colors (relative to the Base URL defined above) with the following Query Parameters.

    This is a supplemental API to provide the colors that correspond to the IDs returned/submitted along with the pet_search and pet_details Queries for the color_id Parameters/Properties.
    ---
    tags:
      - basic pet utilities
    parameters:
      - name: id
        in: path
        type: string
        description: Provides details about the color_id of a pet, where the value can be a single clan id or multiple clan ids with comma-separation. For example 1 or 1,2,3
        required: true
    responses:
      200:
        description: Because it is possible for multiple colors to be returned from this API Query, the JSON response will always return an Array of Objects, even if there is only one Object returned
        schema:
          id: user_response
          properties:
            response:
              schema:
                properties:
                  some_key:
                    type: int
                    description: Unexpected error
                    default: some_values
    """
    result = get_colors_details(id,cache)
    return jsonify(result)

#For Tracking Usage metrics Details
@app.route("/pets/usage_metrics", methods=["GET", "POST"])
def dsn_api_entity_find_raw():
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

#The clear cache API allows to clear all caches
@app.route('/pets/clear_cache/', methods=["GET"])
def api_cache_clear():
    result = get_cache_clear(cache)
    return jsonify(result)

#The clear cache API allows to clear all caches
@app.route('/clan/clan_id/<id', methods=["GET"])
def api_clan_details(id):
    result = get_clan_details(cache)
    return jsonify(result)


#Main Function
if __name__ == '__main__':
    app.run(host="localhost", port=3000)