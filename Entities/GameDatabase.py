import sqlite3

connection = None
cursor = None


class GameDatabase:

	def __init__(self, dbFilename: str):
		global connection
		global cursor
		connection = sqlite3.connect(dbFilename)
		cursor = connection.cursor()

	def initStructure(self):
		cursor.execute(
			"CREATE IF NOT EXIST Entity (entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities INTEGER)")
		cursor.execute(
			"CREATE IF NOT EXIST EntityList (ll_id INTEGER PRIMARY KEY AUTOINCREMENT, value INTEGER, next_ll_id INTEGER)")
		connection.commit()

	def insertEntity(self, name, description):
		cursor.execute(
			"INSERT INTO Entity (name, description) VALUES (?, ?)", (name, description))
		connection.commit()
		return cursor.lastrowid

	def selectAllEntities(self):
		cursor.fetchall()
		cursor.execute("SELECT * FROM Entity")
		return cursor.fetchall()

	def deleteAllEntities(self):
		cursor.execute("DELETE FROM Entity")
		connection.commit()

	def deleteEntity(self, entity_id):
		cursor.execute("DELETE FROM Entity WHERE entity_id=?", entity_id)
		connection.commit()
