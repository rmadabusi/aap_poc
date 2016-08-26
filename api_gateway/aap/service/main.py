from __future__ import division
from flask import Flask, request
import json
from api_gateway.aap.service.implementations.main_services import get_status, get_family_details, get_colors_details, pet_usage_metrics
from urllib import unquote

__author__ = "Treselle"

app = Flask(__name__)


#Get all service status
@app.route("/status", methods=["GET"])
def api_status():
    result = get_status()
    return json.dumps(result)

#For getting family details based on Pet family ids
@app.route('/pets/families/<id>', methods=["GET"])
def api_family_details(id):
    result = get_family_details(id)
    return result

#For getting color details based on Pet color ids
@app.route('/pets/colors/<id>', methods=["GET"])
def api_color_details(id):
    result = get_colors_details(id)
    return result

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


#Main Function
if __name__ == '__main__':
    app.run(host="localhost", port=3000)