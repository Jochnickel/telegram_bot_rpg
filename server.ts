//require('https').get("https://api.telegram.org/bot1107986005:AAEejkxU0KofALESwToms-aVckREPWmHpgw/sendMessage?text=typescript22&chat_id=452549370");
import GameBot from "./GameBot";
console.debug("start server.ts");

const gamebot = new GameBot();

gamebot.runConsole();

console.debug("end server.ts")