import json

import flask
from flask import Flask
from flask import jsonify

from service import projectservice

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql+psycopg2://postgres:root@localhost:5432/progem'


@app.route("/project/root", methods=['GET'])
def function():
    print("This point was hit")
    # Get all projects of the user
    resp = flask.make_response(jsonify("SOMETHING IS WRONG"), 500)
    return resp


@app.route("/project/root", methods=["POST"])
def createRootProject():
    body = flask.request.data
    jsonBody = json.loads(body)
    header = flask.request.headers
    userid = header['Authorization']
    project = projectservice.createProjectFromBody(jsonBody, userid)
    addedProjectDict = projectservice.addProjectToDB(project).toDict()
    return jsonify(addedProjectDict)


if __name__ == '__main__':
    app.run(debug=True, port=4000)
