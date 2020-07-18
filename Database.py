import sqlite3

connection = None


class Database:
	def __init__(self, filename: str):
		global connection
		connection = sqlite3.connect(filename)

	def getConnection(self):
		global connection
		return connection

	def selectWhereUnsafe(self, cols, tableName, where_condition):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("SELECT %s FROM %s WHERE %s" %
					   (cols, tableName, where_condition))
		return cursor.fetchall()

	def selectWhereLessUnsafe(self, cols, tableName, where_col, where_val):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("SELECT %s FROM %s WHERE %s=?" %
					   (cols, tableName, where_col), (where_val,))
		return cursor.fetchall()

	def selectUnsafe(self, cols, tableName):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("SELECT %s FROM %s" % (cols, tableName))
		return cursor.fetchall()

	def deleteFromUnsafe(self, tableName):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("DELETE FROM %s" % tableName)
		connection.commit()

	def createTableIfNotExistsUnsafe(self, tableName, colsWOBrackets):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("CREATE TABLE IF NOT EXISTS %s (%s)" %
					   (tableName, colsWOBrackets))
		connection.commit()

	def createLinkedListIfNotExistsUnsafe(self, tableName, colNames):
		return self.createTableIfNotExistsUnsafe(tableName, "ll_id INTEGER PRIMARY KEY, next_ll_id INTEGER, %s" % (colNames))

	def insertLinkedListUnsafe(self, tableName, colNames, values):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("INSERT INTO %s (%s) VALUES (%s)" %
					   (tableName, colNames, values))
		connection.commit()
		return cursor.lastrowid

	def appendLinkedListUnsafe(self, tableName, currentList, values):
		cursor = connection.cursor()
		cursor.fetchall()
		iterator = self.selectWhereUnsafe(
			"ll_id, next_ll_id", tableName, "ll_id=%s" % (currentList))
		if (None == iterator or None == iterator[0]):
			raise ValueError("currentList not found")
		while(iterator):
			pass