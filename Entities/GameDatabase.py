database = None


class GameDatabase:

	def __init__(self, db):
		global database
		database = db
		self.initStructure()

	def initStructure(self):
		database.createTableIfNotExistsUnsafe(
			"Entity", "entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities INTEGER")
		database.createLinkedListIfNotExistsUnsafe(
			"EntityLists", "entity_id INTEGER")
		database.createLinkedListIfNotExistsUnsafe(
			"AllEntities", "entity_id INTEGER")

	
	def insertEntity(self, name, description):
		database.insertLessUnsafe("Entity","name, description",name, description)

	def insertEntity(self):
		pass

	def insertEntity(self):
		pass

	def insertEntity(self):
		pass

	def insertEntity(self):
		pass

	def insertEntity(self):
		pass
