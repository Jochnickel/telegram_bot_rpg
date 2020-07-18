from Entity import Entity
from GameDatabase import GameDatabase


gdb = None


class EntityManager:
	def __init__(self, database):
		global gdb
		gdb = GameDatabase(database)

	def createPlayer(self, room: Entity):
		pass

	def createEntity(self, name, description):
		gdb.insertEntity("Training Room", "An empty room")
