import PlayerNotFoundException from "./PlayerNotFoundException";
import GameDatabase from "./GameDatabase";
import sleep from "../../utils/sleep";

export default class EntityManager {
	readonly #gdb = new GameDatabase("game.db");

	getEntityByID(entity_id: number) {
		throw new Error("not implemented");
	}

	async getPlayerByID(platform_id: string, platform: string) {
		console.debug("EntityManager.getPlayerByID() start");
		const player = await this.#gdb.selectPlayer(platform_id, platform);
		if (player === []){
			throw new PlayerNotFoundException();
		}
		console.debug("EntityManager.getPlayerByID()", player);
		//TODO
		await sleep(10);
		throw new Error("not implemented");
	}

	async createPlayer(platform_id: string, platform: string){
		this.#gdb.insertPlayer(platform_id, platform, null);
	}
}