console.debug("GameBot.ts start");
import Game from "./Game/Game";
import sleep from "./utils/sleep";
import TelegramPolling from "./Bot/Telegram";

export default class GameBot {

	private readonly game = new Game();
	private telegram: Promise<unknown>;
	private console: Promise<unknown>;


	async getInfo(platform: string, platform_id: string): Promise<string> {
		return new Promise((resolve, reject) => {
			resolve("GameBot.getInfo() not implemented");
		});
	}

	async runTelegram() {
		if (!(this.telegram instanceof Promise)) {
			this.telegram = new Promise((resolve, reject) => {
				const telegram = new TelegramPolling("1107986005:AAEejkxU0KofALESwToms-aVckREPWmHpgw");
				while (true) {
					telegram.answerAll(this);
					sleep(10);
				}
			});
		}
		return this.telegram;
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