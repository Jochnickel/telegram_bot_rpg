console.debug("GameBot.ts start");
import Game from "./Game/Game";
import sleep from "./utils/Sleep";
import * as https from 'https';

export default class GameBot {

	private readonly game = new Game();

	async runTelegram() {
		return new Promise(async (resolve, reject) => {
			while (true) {
				await sleep(10);
				const r = https.get("https://api.telegram.org/bot1107986005:AAEejkxU0KofALESwToms-aVckREPWmHpgw/sendMessage?text=typescript22&chat_id=452549370");
				console.debug("GameBot.runTelegram()", r);
			}
		});
	}

	async runConsole() {
		console.debug("GameBot.runConsole() start");
		return new Promise(async (resolve, reject) => {
			const USER_ID = "1";
			const PLATFORM = "console";
			while (true) {
				const screen = await this.game.getUserScreen(USER_ID, PLATFORM);
				console.log(screen);
				await sleep(1);
			}
		});
	}

}

console.debug("GameBot.ts end");