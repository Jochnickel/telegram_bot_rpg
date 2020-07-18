import sqlite3

connection = None


class Database:
	def __init__(self, filename: str):
		global connection
		connection = sqlite3.connect(filename)

	def getConnection(self):
		global connection
		return connection

	# create

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

	# delete

	def deleteFromUnsafe(self, tableName: str) -> None:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("DELETE FROM %s" % tableName)
		connection.commit()

	# insert

	# throws ValueError if currentList not found
	def appendLinkedListUnsafe(self, tableName: str, colNames: str, values: str, currentList: int):
		lastItem = self.getLastLinkedListItemUnsafe(tableName, currentList)
		newList = self.insertLinkedListUnsafe(tableName, colNames, values)
		self.updateWhereUnsafe(tableName, "next_ll_id=%s" %
							   newList, "ll_id=%s" % lastItem)

	def insertLinkedListUnsafe(self, tableName, colNames, values) -> int:
		cursor = connection.cursor()
		cursor.fetchall()
		print(colNames)
		cursor.execute("INSERT INTO %s (%s) VALUES (%s)" %
					   (tableName, colNames, values))
		connection.commit()
		return cursor.lastrowid

	def insertUnsafe(self, tableName, colNames, values) -> int:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("INSERT INTO %s (%s) VALUES (%s)" %
					   (tableName, colNames, values))
		connection.commit()
		return cursor.lastrowid

	def insertLessUnsafe(self, tableName, colNames, values) -> int:
		cursor = connection.cursor()
		cursor.fetchall()
		vls = []
		for a in values:
			vls.append('?')
		valuString = ("%s"%tuple(vls))
		cursor.execute("INSERT INTO %s (%s) VALUES %s"%(tableName, colNames, valuString), values)
		connection.commit()
		return cursor.lastrowid

	# selects

	# throws ValueError if currentitem not found

	def getLastLinkedListItemUnsafe(self, tableName: str, currentItem: int):
		iteratorID = currentItem
		iteratorNextID = self.selectNextLinkedListUnsafe(
			tableName, currentItem)

		while(iteratorNextID):
			iteratorID = iteratorNextID
			iteratorNextID = self.selectNextLinkedListUnsafe(
				tableName, iteratorID)

		return iteratorID

	def hasNextLinkedListUnsafe(self, tableName: str, currentItem: int) -> bool:
		return bool(self.selectWhereLessUnsafe("next_ll_id", tableName, "ll_id", currentItem)[0])

	# throws ValueError if currentItem not found
	def selectNextLinkedListUnsafe(self, tableName: str, currentItem: int) -> int:
		item = self.selectWhereLessUnsafe(
			"ll_id, next_ll_id", tableName, "ll_id", currentItem)
		if (None == item):
			raise ValueError("Initial item not found")
		return item[1]

	def selectUnsafe(self, cols: str, tableName: str) -> list:
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("SELECT %s FROM %s" % (cols, tableName))
		return cursor.fetchall()

	def selectWhereUnsafe(self, cols: str, tableName: str, where_condition: str) -> list:
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

	# update

	def updateUnsafe(self, tableName: str, colsVals: str):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("UPDATE %s SET %s" % (tableName, colsVals))
		return cursor.fetchall()

	def updateWhereUnsafe(self, tableName: str, colsVals: str, whereCondition: str):
		cursor = connection.cursor()
		cursor.fetchall()
		cursor.execute("UPDATE %s SET %s WHERE %s" %
					   (tableName, colsVals, whereCondition))
		return cursor.fetchall()
