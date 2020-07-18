import logging
from Telebot import Telebot
from Database import Database
from Game import Game
import sys
print(sys.path)

logging.basicConfig(filename='error.log', filemode='w', format='%(name)s - %(levelname)s - %(message)s')
logging.warning('This will get logged to a file')

try:
  telegram_db = Database("telebot.py")
  telebot = Telebot(telegram_db)
  game_db = Database("game.db")
  game = Game(game_db, telebot)
except Exception as e:
  # Log Error
  logging.error("%s", e)
