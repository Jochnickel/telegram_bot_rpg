import unittest

from Database import Database


class asd(unittest.TestCase):
    def test_connection(self):
        database = Database("test.db")
        self.assertIsNotNone(database.getConnection())

    def test_createTable(self):
        database = Database("test.db")
        database.deleteFromUnsafe("tab")
        database.createTableIfNotExistsUnsafe(
            "tab", "keyy INTEGER PRIMARY KEY, col1 TEXT")

        tab1 = database.selectUnsafe("*", "tab")
        self.assertEqual(tab1, [])

        ins1 = database.insertUnsafe("tab", "col1", "'somevalue'")
        self.assertIsNotNone(ins1)
        self.assertIsInstance(ins1, int)

        sel = database.selectUnsafe("*", "tab")
        self.assertEqual(sel, [(ins1, 'somevalue')])
        
        sel = database.selectWhereUnsafe("*", "tab","col1='somevalue'")
        self.assertEqual(sel, [(ins1, 'somevalue')])

        sel = database.selectWhereUnsafe("col1", "tab","col1='somevalue'")
        self.assertEqual(sel, [('somevalue',)])

        
        ins2 = database.insertUnsafe("tab", "col1", "'othervalue'")
        self.assertIsNotNone(ins2)
        self.assertIsInstance(ins2, int)

        
        sel = database.selectUnsafe("*", "tab")
        self.assertEqual(sel, [(ins1, 'somevalue'),(ins2, 'othervalue')])
        
        sel = database.selectWhereUnsafe("*", "tab","col1='somevalue'")
        self.assertEqual(sel, [(ins1, 'somevalue')])
        
        sel = database.selectWhereUnsafe("*", "tab","col1='othervalue'")
        self.assertEqual(sel, [(ins2, 'othervalue')])

        
        
        



    def test_createLinkedList(self):
        database = Database("test.db")

        database.deleteFromUnsafe("tab")
        database.createLinkedListIfNotExistsUnsafe("tab","col1")

        tab1 = database.selectUnsafe("*", "tab")
        self.assertEqual(tab1, [])

        ins1 = database.insertLinkedListUnsafe("tab","col1","Joachim")
        self.assertIsNotNone(ins1)
        self.assertIsInstance(ins1, int)

        database.appendLinkedListUnsafe("tab", "col1", "Janke", ins1)

