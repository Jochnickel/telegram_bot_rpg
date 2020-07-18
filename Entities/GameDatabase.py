
database = None
connection = None
cursor = None


class GameDatabase:

	def __init__(self, db):
		global database
		global connection
		global cursor
		database = db
		connection = database.getConnection()
		cursor = connection.cursor()

		self.initStructure()

	def initStructure(self):
		database.createTableIfNotExistsUnsafe("Entity","entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities INTEGER")
		database.createLinkedListIfNotExistsUnsafe("EntityLists","entity_id INTEGER")
		database.createLinkedListIfNotExistsUnsafe("AllEntities","entity_id INTEGER")
		
	
	def getEntity(self, entity_id):
		return database.selectWhereLessUnsafe("*","Entity","entity_id",entity_id)

	def getAllEntities(self):
		return database.selectUnsafe("*","Entity")

	def getAllEntityLists(self):
		return database.selectUnsafe("*","EntityLists")

	def createEntity(self, name, description, linkedEntity_ids):
		global cursor

		ent_list = self.createEntityList(linkedEntity_ids)

		cursor.execute("INSERT INTO Entity (name, description, linked_entities) VALUES (?,?,?)",
					   (name, description, ent_list))
		connection.commit()
		return cursor.lastrowid

	def createEntityList(self, Entity_id_array):
		if (None == Entity_id_array):
			return
		global cursor
		currentID = None
		for ent_id in Entity_id_array:
			cursor.execute(
				"INSERT INTO EntityLists (next_id, entity_id) VALUES (?,?)", (currentID, ent_id))
			currentID = cursor.lastrowid
		connection.commit()
		return currentID

	def getEntityList(self, entity_list_id):
		out = []
		global cursor
		currentID = entity_list_id
		cursor.fetchall()
		while(currentID):

			cursor.execute(
				"SELECT next_id, entity_id FROM EntityLists WHERE entity_list_id=?", (currentID,))
			answer = cursor.fetchall()[0]
			currentID = answer[0]
			out.append(answer[1])
		return out

	def deleteAllEntities(self):
		return database.deleteFromUnsafe("Entity")

	def deleteAllEntityLists(self):
		return database.deleteFromUnsafe("EntityLists")

	def appendToEntityList(self, entity_list_id, Entity_id_array):
		global cursor
		cursor.fetchall()
		cursor.execute(
			"SELECT entity_list_id, next_id FROM EntityLists WHERE entity_list_id=?", (entity_list_id,))
		answer = cursor.fetchall()[0]
		if (None==answer):
			raise ValueError("Coudnt find list")
		while(answer[1]):
			cursor.execute(
				"SELECT entity_list_id, next_id FROM EntityLists WHERE entity_list_id=?", (answer[1],))
			answer = cursor.fetchall()[0]
		listEnd = answer[0]
		newList = self.createEntityList(Entity_id_array)
		cursor.execute("UPDATE EntityLists SET next_id = ? WHERE entity_list_id=?", (newList, listEnd))
		connection.commit()
