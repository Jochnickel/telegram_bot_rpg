from Database import Database
from Telebot import Telebot
from Entities.EntityManager import EntityManager
import asyncio

 

class Game:
	def __init__(self, telebot: Telebot):
		entityManager = EntityManager

 