from Database import Database
from Telebot import Telebot
from Gamee.GameDatabase import GameDatabase
import asyncio

entityList = None
database = None


class Game:
	def __init__(self, db, telebot: Telebot):
		global database
		database = GameDatabase(db)
		database.deleteAllEntities()
		database.deleteAllEntityLists()
		listPersons = database.createEntity("Persons in Room", "<>", [])
		inspect = database.createEntity(
			"Inscpect Room", "Persons: <>, Items: <>", [11, 12, 13])
		training_id = database.createEntity(
			"Training Room", "A roomsssss", [11, 12, 13])
		print("tr ", training_id)
		print("all", database.getAllEntityLists())
		print("ale", database.getAllEntities())
		print("EnL", database.getEntityList(3))
