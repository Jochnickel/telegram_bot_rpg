import unittest

from GameDatabase import GameDatabase


class Test(unittest.TestCase):
	def test(self):
		gdb = GameDatabase("test.db")
		gdb.deleteAllEntities()
		self.assertEqual(gdb.selectAllEntities(), [])
		ent1 = gdb.insertEntity("Table", "An empty table", None)
		list1 = gdb.insertEntityList([ent1, 12])
		ent2 = gdb.insertEntity("Training Room", "A small room with a <table>", list1)
		self.assertEqual(gdb.selectAllEntities(), [
			(ent1, "Table", "An empty table", None),
			(ent2, "Training Room", "A small room with a <table>", list1)
		])
		gdb.updateEntityName(ent2, "Home")
		self.assertEqual(gdb.selectEntity(ent2),[(ent2, "Home", "A small room with a <table>", list1)])
