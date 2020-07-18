import logging
from Telebot import Telebot
from Database import Database
from Game import Game


logging.basicConfig(level=logging.DEBUG, filename='error.log',
					format='%(asctime)s: %(levelname)s: %(name)s: %(message)s')
logging.info('Starting main.py')

try:
	telegram_db = Database("telebot.py")
	telebot = Telebot(telegram_db)
	game_db = Database("game.db")
	game = Game(game_db, telebot)
	logging.info("Done main.py")
except Exception as e:
	logging.exception("")
