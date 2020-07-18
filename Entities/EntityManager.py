from Entity import Entity

db = None

class EntityManager:
	def __init__(self, database):
		global db
		db = database

		db.createLinkedListIfNotExistsUnsafe("AllEntities","entity_id INTEGER")
		db.createTableIfNotExistsUnsafe("Entity","name TEXT, description TEXT")

	def createPlayer(self, room: Entity):
		db.insertUnsafe

	def createEntity(self, name, description):
		db.insertUnsafe("Entity","name, description", "")