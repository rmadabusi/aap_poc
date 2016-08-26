from __future__ import division
from flask import Flask
from aap.service.implementations.main_services import get_family_details

__author__ = "Treselle"

app = Flask(__name__)

#For getting family details based on Pet family ids
@app.route('/pets/families/<id>', methods=["GET"])
def api_family_details(id):
    result = get_family_details(id)
    return result

#Main Function
if __name__ == '__main__':
    app.run(host="localhost", port=3000)