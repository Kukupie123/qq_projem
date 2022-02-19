from flask_sqlalchemy import SQLAlchemy

from app import app

db = SQLAlchemy(app)


class Project(db.Model):
    __tablename__ = 'projects'
    id = db.Column(
        db.Integer, primary_key=True, name="id", nullable=False
    )
    title = db.Column(db.String)
    description = db.Column(db.String)
    ancestry = db.Column(db.String)
    timestamp = db.Column(db.Date)
    iscomplete = db.Column(db.Boolean)
    rulesid = db.Column(db.Integer)
    userid = db.Column(db.String)


class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.String, primary_key=True, name="email", nullable=False)
    password = db.Column(db.String, primary_key=False, name="cred", nullable=False)
