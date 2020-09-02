import Game from "../Game/Game";
import GameBot from "../GameBot";
import * as https from 'https';

export default class TelegramPolling {
	private readonly TOKEN: string;

	constructor(token: string) {
		this.TOKEN = token;
	}

	async answerAll(gameBot: GameBot) {
		for (let index = 0; index < 0; index++) {
			//TODO
			if ("".includes("/info")) {
				const info = gameBot.getInfo("telegram", "452549370");
				const r = https.get("https://api.telegram.org/bot1107986005:AAEejkxU0KofALESwToms-aVckREPWmHpgw/sendMessage?text=typescript22&chat_id=452549370");
			}
		}
	}

}