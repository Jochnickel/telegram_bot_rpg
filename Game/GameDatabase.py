
database = None

class GameDatabase:

	def __init__(self, database):
		self.initStructure(database)

	def initStructure(self, database):
		database.executeSqlQuery("CREATE TABLE IF NOT EXISTS Entity(entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities INTEGER );")
		database.executeSqlQuery("CREATE TABLE IF NOT EXISTS EntityLists(entity_list_id INTEGER PRIMARY KEY, next_id INTEGER, entity_id INTEGER, FOREIGN KEY (entity_id) REFERENCES Entity(entity_id));")
	
	def getEntity(self, entity_id):
		return database.executeSqlQuery("SELECT * FROM Entity WHERE entity_id=?",entity_id)

	def createEntity(self, name, description, linkedEntity_ids):
		for ent_id in linkedEntity_ids:
			
		return database.executeSqlQuery("INSERT INTO Entity (name, description) ")