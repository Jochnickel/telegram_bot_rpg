from Telebot import Telebot
from Entities.EntityManager import EntityManager
import asyncio
import time

def switch(keyword):
	return 

class Game:
	entityManager = None
	telebot = None

	def __init__(self, telebot: Telebot):
		self.entityManager = EntityManager()
		self.telebot = telebot
		self.loop()

	def loop(self):
		playerID = 1
		while True:
			inp = input("room / inventory > ")
			if (None==self.entityManager.getPlayer(playerID)):
				self.entityManager.createPlayer(playerID, "Joachim")
			if ("room"==input):
				pass
			if("inventory"==input):
				pass


