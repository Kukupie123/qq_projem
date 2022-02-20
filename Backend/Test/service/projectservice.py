from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from models import models

postgres_url = 'postgresql+psycopg2://postgres:root@localhost:5432/progem'

engine = create_engine(postgres_url)

Session = sessionmaker()
Session.configure(bind=engine,expire_on_commit=False)


def createProjectFromBody(body, userid):
    pro = models.Project()
    pro.title = body['title']
    pro.description = body['description']
    pro.ancestry = body['ancestry']
    pro.iscomplete = body['iscomplete']
    pro.rulesid = body['rulesid']
    pro.userid = userid
    return pro


def addProjectToDB(project):
    localSession = Session()
    localSession.add(project)
    localSession.commit()
    localSession.flush()
    localSession.close()
    return project
