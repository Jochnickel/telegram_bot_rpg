
connection = None
cursor = None


class GameDatabase:

    def __init__(self, database):
        global connection
        global cursor
        connection = database.getConnection()
        cursor = connection.cursor()
        self.initStructure()

    def initStructure(self):
        global connection
        global cursor
        cursor.execute(
            "CREATE TABLE IF NOT EXISTS Entity(entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities INTEGER );")
        cursor.execute("CREATE TABLE IF NOT EXISTS EntityLists(entity_list_id INTEGER PRIMARY KEY, next_id INTEGER, entity_id INTEGER, FOREIGN KEY (entity_id) REFERENCES Entity(entity_id));")
        connection.commit()

    def getEntity(self, entity_id):
        global cursor
        cursor.fetchall()
        cursor.execute("SELECT * FROM Entity WHERE entity_id=?", entity_id)
        return cursor.fetchall()

    def getAllEntities(self):
        global cursor
        cursor.fetchall()
        cursor.execute("SELECT * FROM Entity")
        return cursor.fetchall()

    def getAllEntityLists(self):
        global cursor
        cursor.fetchall()
        cursor.execute("SELECT * FROM EntityLists")
        return cursor.fetchall()

    def createEntity(self, name, description, linkedEntity_ids):
        global cursor

        ent_list = self.createEntityList(linkedEntity_ids)

        cursor.execute("INSERT INTO Entity (name, description, linked_entities) VALUES (?,?,?)",
                       (name, description, ent_list))
        connection.commit()
        return cursor.lastrowid

    def createEntityList(self, linkedEntity_ids):
        if (None == linkedEntity_ids):
            return None
        global cursor
        currentID = None
        for ent_id in linkedEntity_ids:
            cursor.execute(
                "INSERT INTO EntityLists (next_id, entity_id) VALUES (?,?)", (currentID, ent_id))
            currentID = cursor.lastrowid
        connection.commit()
        return currentID

    def getEntityList(self, entity_list_id):
        out = []
        global cursor
        cursor.fetchall()
        currentID = entity_list_id
        while(currentID):
            cursor.execute(
                "SELECT * FROM EntityLists WHERE entity_list_id=?", entity_list_id)
            currentID = cursor.fetchall()
            print("currentid", 1)
    
    def deleteAllEntities(self):
        global cursor
        cursor.execute("DELETE FROM Entity")
        connection.commit()
    
    def deleteAllEntityLists(self):
        global cursor
        cursor.execute("DELETE FROM EntityLists")
        connection.commit()
