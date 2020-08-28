import GameDatabase from "./GameDatabase";
import sleep from "../../utils/Sleep";
export default class EntityManager {
	private readonly gdb = new GameDatabase("game.db");

	getEntityByID(entity_id: number) {
		throw new Error("not implemented");
	}

	async getPlayerByID(platform_id: string, platform: string){
		console.debug(await this.gdb.selectPlayer(platform_id, platform));
		sleep(100);
		throw new Error("not implemented");
	}
}