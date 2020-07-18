import logging
from Telebot import Telebot
from Game import Game


logging.basicConfig(level=logging.DEBUG, filename='error.log',
					format='%(asctime)s: %(levelname)s: %(name)s: %(message)s')
logging.info('Starting main.py')

try:
	telebot = Telebot()
	game = Game(telebot)
	logging.info("Done main.py")
except Exception as e:
	logging.exception("")
