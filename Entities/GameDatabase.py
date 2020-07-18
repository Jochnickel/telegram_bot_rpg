import sqlite3

connection = None
cursor = None


class GameDatabase:

	def __init__(self, dbFilename: str):
		global connection
		global cursor
		connection = sqlite3.connect(dbFilename)
		cursor = connection.cursor()
		self.initStructure()

	def initStructure(self):
		cursor.execute(
			"CREATE TABLE IF NOT EXISTS Entity (entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities INTEGER)")
		cursor.execute(
			"CREATE TABLE IF NOT EXISTS EntityList (ll_id INTEGER PRIMARY KEY AUTOINCREMENT, value INTEGER, next_ll_id INTEGER)")
		cursor.execute(
			"CREATE TABLE IF NOT EXISTS PlayerList (player_id INTEGER PRIMARY KEY, entity_id INTEGER)")
		connection.commit()

	# Entity

	def setEntity1(self, name, description, linkedEntityListID):
		cursor.execute("DELETE FROM Entity WHERE entity_id=1")
		cursor.execute("INSERT INTO Entity (entity_id, name, description, linked_entities) VALUES (1, ?, ?, ?)",
					   (name, description, linkedEntityListID))
		connection.commit()
		return cursor.lastrowid

	def selectAllEntities(self):
		cursor.fetchall()
		cursor.execute("SELECT * FROM Entity")
		return cursor.fetchall()

	def deleteAllEntities(self):
		cursor.execute("DELETE FROM Entity")
		connection.commit()

	def insertEntity(self, name, description, linkedEntityListID):
		cursor.execute(
			"INSERT INTO Entity (name, description, linked_entities) VALUES (?, ?, ?)", (name, description, linkedEntityListID))
		connection.commit()
		return cursor.lastrowid

	def selectEntity(self, entity_id):
		cursor.fetchall()
		cursor.execute("SELECT * FROM Entity WHERE entity_id=?", (entity_id,))
		return cursor.fetchall()

	def deleteEntity(self, entity_id):
		cursor.execute("DELETE FROM Entity WHERE entity_id=?", (entity_id,))
		connection.commit()

	def updateEntityName(self, entity_id, name):
		cursor.execute(
			"UPDATE Entity SET name=? WHERE entity_id=?", (name, entity_id))
		connection.commit()

	def updateEntityDescription(self, entity_id, description):
		cursor.execute(
			"UPDATE Entity SET description=? WHERE entity_id=?", (description, entity_id))
		connection.commit()

	def updateEntityLinkedEntites(self, entity_id, linked_entity_list_id):
		cursor.execute(
			"UPDATE Entity SET linked_entities=? WHERE entity_id=?", (linked_entity_list_id, entity_id))
		connection.commit()

	# EntityLists

	def insertEntityList(self, entityList):
		ll_id = None
		for e_id in reversed(entityList):
			cursor.execute(
				"INSERT INTO EntityList (value, next_ll_id) VALUES (?, ?)", (e_id, ll_id))
			ll_id = cursor.lastrowid
		connection.commit()
		return ll_id

	# Player

	def selectPlayer(self, player_id):
		cursor.fetchall()
		cursor.execute("SELECT * FROM Player WHERE player_id=?", (player_id,))
		return cursor.fetchall()

	def insertPlayer(self, player_id, entity_id):
		cursor.fetchall()
		cursor.execute(
			"INSERT INTO Player (player_id, entity_id) VALUES (?, ?)", (player_id, entity_id))
		connection.commit()
		return cursor.lastrowid
