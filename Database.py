import sqlite3

connection = None

class Database:
	def __init__(self, filename: str):
		global connection
		connection = sqlite3.connect(filename)
	def executeSqlQuery(self, queryString: str):
		cursor = connection.cursor()
		cursor.execute(queryString)
		cursor.commit()
		return cursor.fetchall()
		