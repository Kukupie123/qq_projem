import json as j

import flask as f
from flask import Flask
from flask import jsonify

from service import projectservice

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql+psycopg2://postgres:root@localhost:5432/progem'


@app.route("/project/root", methods=['GET'])
def function():
    print("This point was hit")
    # Get all projects of the user
    resp = f.make_response(jsonify("SOMETHING IS WRONG"), 500)
    return resp


@app.route("/project/root", methods=["POST"])
def createRootProject():
    body = f.request.data
    jsonBody = j.loads(body)
    header = f.request.headers
    userid = header['Authorization']
    project = projectservice.createProjectFromBody(jsonBody, userid)
    addedProjectDict = projectservice.addProjectToDB(project).toDict()

    return jsonify({
        "data": addedProjectDict,
        "message": "Successfully Created Project"
    })


if __name__ == '__main__':
    app.run(debug=True, port=4000)
