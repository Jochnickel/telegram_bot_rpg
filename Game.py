from Database import Database
from Telebot import Telebot
from Gamee.GameDatabase import GameDatabase
import asyncio

entityList = None
database = None

class Game:
	def __init__(self, conn, telebot: Telebot):
		global database
		database = GameDatabase(conn)
		database.createEntity("Training Room", "A roomsssss", [1,2,3])
		print(database.getAllEntityLists())