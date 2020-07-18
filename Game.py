from Database import Database
from Telebot import Telebot
from Entities.EntityManager import EntityManager
import asyncio

 

class Game:
	def __init__(self, db, telebot: Telebot):
		entityManager = EntityManager(db)

 