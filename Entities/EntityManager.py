from .Entity import Entity
from .GameDatabase import GameDatabase
from .Player import Player

class EntityManager:
	gdb = None
	def __init__(self):
		self.gdb = GameDatabase("game.db")
		self.gdb.setEntity1("Training Room", "A room")

	def createPlayer(self, player_id, name):
		ent = self.createEntity(name, "A new player. You are in the <training room>", [1])
		self.gdb.insertPlayer(player_id, ent.getEntityID())
	
	def getPlayer(self, player_id):
		dbplayer = self.gdb.selectPlayer(player_id)[0]
		if (None==dbplayer):
			return None
		player = Player(dbplayer[0])
		def getEntity():
			return self.getEntity(dbplayer[1])
		player.getEntity = lambda: self.getEntity(dbplayer[1])


	def getEntity(self, entity_id):
		pass

	def createEntity(self, name: str, description: str, LinkedEntites: list):
		link_id = self.createEntityList(LinkedEntites)
		e_id = self.gdb.insertEntity(name, description, link_id)
		ent = Entity(e_id, name, description, link_list)
		return ent

	def createEntityList(self, LinkedEntites: list):
		link_list = map(lambda e: e.getEntityID(), LinkedEntites)
		link_id = self.gdb.insertEntityList(link_list)
		return link_id


	def changeEntityName(self, entity_id, name):
		self.gdb.updateEntityName(entity_id, name)
	
	def inspectRoom(self, player_id):
		pass
