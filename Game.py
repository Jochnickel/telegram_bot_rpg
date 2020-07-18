from Database import Database
from Telebot import Telebot
from Gamee.EntityManager import EntityManager
import asyncio



class Game:
	def __init__(self, db, telebot: Telebot):
		entityManager = EntityManager(db)
		
