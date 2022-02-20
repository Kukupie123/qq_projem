from datetime import datetime

from sqlalchemy import Column, Integer, String, Date, Boolean
from sqlalchemy.orm import declarative_base

Base = declarative_base()


class Project(Base):
    __tablename__ = 'projects'
    id = Column(Integer, primary_key=True,)
    title = Column(String)
    description = Column(String)
    ancestry = Column(String)
    timestamp = Column(Date, default=datetime.utcnow())
    iscomplete = Column(Boolean)
    rulesid = Column(Integer)
    userid = Column(String)

    def toDict(self):
        return dict(
            title=self.title,
            description=self.description,
            ancestry=self.ancestry,
            timestamp=self.timestamp,
            iscomplete=self.iscomplete,
            rulesid=self.rulesid,
            userid=self.userid,
            id=self.id
        )