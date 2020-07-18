import logging
import Database
import Telebot
import Game

logging.basicConfig(filename='error.log', filemode='w', format='%(name)s - %(levelname)s - %(message)s')
logging.warning('This will get logged to a file')

try:
  telegram_db = new Database("telebot.db")
  telebot = new Telebot(telegram_db)
  game_db = new Database("game.db")
  game = new Game(game_db, telebot)
except Error as e:
  # Log Error
  logging.error("%s", e)
