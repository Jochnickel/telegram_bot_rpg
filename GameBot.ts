import Input from "./Input";
import Game from "./Game/Game";

export default class GameBot {

	private readonly game = new Game();

	async runTelegram() {
		return new Promise((resolve, reject) => {
			reject("not implemented");
		});
	}

	async runConsole() {
		return new Promise((resolve, reject) => {
			const USER_ID = "1";
			const PLATFORM = "console";
			this.game.getUserScreen(USER_ID, PLATFORM);
		});
	}
}