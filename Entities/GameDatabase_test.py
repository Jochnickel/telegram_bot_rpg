import unittest

from GameDatabase import GameDatabase

class Test(unittest.TestCase):
    def test(self):
        gdb = GameDatabase("test.db")
        gdb.deleteAllEntities()
        gdb.insertEntity("Training Room","An empty room")
        print(gdb.selectAllEntities())