import sqlite3

connection = None


class Database:
    def __init__(self, filename: str):
        global connection
        connection = sqlite3.connect(filename)

    def getConnection(self):
        global connection
        return connection
