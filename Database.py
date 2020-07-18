import sqlite3

connection = None


class Database:
	def __init__(self, filename: str):
		global connection
		connection = sqlite3.connect(filename)

	def getConnection(self):
		global connection
		return connection

	def selectWhereUnsafe(self, cols: str, tableName: str,
						  where_condition: str) -> list:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("SELECT %s FROM %s WHERE %s" %
					   (cols, tableName, where_condition))
		return cursor.fetchall()

	def selectWhereLessUnsafe(self, cols: str, tableName: str, where_col: str,
							  where_val: str) -> list:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute(
			"SELECT %s FROM %s WHERE %s=?" % (cols, tableName, where_col),
			(where_val, ))
		return cursor.fetchall()

	def selectUnsafe(self, cols: str, tableName: str) -> list:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("SELECT %s FROM %s" % (cols, tableName))
		return cursor.fetchall()

	def deleteFromUnsafe(self, tableName: str) -> None:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("DELETE FROM %s" % tableName)
		connection.commit()

	def createTableIfNotExistsUnsafe(self, tableName, columns) -> None:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("CREATE TABLE IF NOT EXISTS %s (%s)" %
					   (tableName, columns))
		connection.commit()

	def createLinkedListIfNotExistsUnsafe(self, tableName, columns):
		return self.createTableIfNotExistsUnsafe(
			tableName,
			"ll_id INTEGER PRIMARY KEY, next_ll_id INTEGER, %s" % (columns))

	def insertUnsafe(self, tableName, colNames, values) -> int:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("INSERT INTO %s (%s) VALUES (%s)" %
					   (tableName, colNames, values))
		connection.commit()
		return cursor.lastrowid

	def insertLinkedListUnsafe(self, tableName, colNames, values) -> int:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("INSERT INTO %s (%s) VALUES (%s)" %
					   (tableName, colNames, values))
		connection.commit()
		return cursor.lastrowid

	def appendLinkedListUnsafe(self, tableName: str, colNames: str,
							   values: str, currentList: int):
		iterator = currentList
		iteratorNext = self.selectNextLinkedListUnsafe(tableName, currentList)
		while(iteratorNext):
			iterator = iteratorNext
			iteratorNext = self.selectNextLinkedListUnsafe(tableName, currentList)

		while (iterator):
			pass

	def selectNextLinkedListUnsafe(self, tableName: str, currentItem: int) -> int | None:
		item = self.selectWhereLessUnsafe("ll_id, next_ll_id", tableName,
											"ll_id", currentItem)
		if (None == item):
			raise ValueError("Item not found")
		return item[1]
