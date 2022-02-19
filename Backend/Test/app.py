import flask
from flask import Flask, request
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql+psycopg2://postgres:root@localhost:5432/progem'


@app.route("/project/root", methods=['GET'])
def function():
    # Get all projects of the user
    resp = flask.make_response(jsonify("SOMETHING IS WRONG"), 500)
    return resp
    user = User
    user.id = "kuku@gmail.com"
    users = User.query.all()
    for u in users:
        return jsonify(u.password)


@app.route("/project/root", methods=["POST"])
def createRootProject():
    # Expecting a userID on authHeader.
    body = request.data
    header = request.headers
    #Create Root rule and check if such rules exist by se, if it exists, get the ID,
    return jsonify(str(header))


if __name__ == '__main__':
    app.run(debug=True)
