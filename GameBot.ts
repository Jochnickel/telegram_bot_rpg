console.debug("GameBot.ts start");
import Input from "./utils/Input";
import Game from "./Game/Game";
import sleep from "./utils/Sleep";

const https = require('https');

export default class GameBot {

	private readonly game = new Game();

	async runTelegram() {
		return new Promise(async (resolve, reject) => {
			while(true){
				await sleep(10);
				const r = https.get("https://api.telegram.org/bot1107986005:AAEejkxU0KofALESwToms-aVckREPWmHpgw/sendMessage?text=typescript22&chat_id=452549370");
				console.debug(r);
			}
		});
	}

	async runConsole() {
		console.debug("GameBot.runConsole() start");
		return new Promise(async (resolve, reject) => {
			const USER_ID = "1";
			const PLATFORM = "console";
			while(true){
				console.log(this.game.getUserScreen(USER_ID, PLATFORM));
				await sleep(1);
			}
		});
	}
	
}

console.debug("GameBot.ts end");