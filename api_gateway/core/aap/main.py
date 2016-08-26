from __future__ import division
from flask import Flask
import json
from aap.service.implementations.main_services import get_status, get_family_details

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

#Main Function
if __name__ == '__main__':
    app.run(host="localhost", port=3000)