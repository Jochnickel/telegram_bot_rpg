import EntityManager from "./EntitySystem/EntityManager";
import PlayerNotFoundException from "./EntitySystem/PlayerNotFoundException";

export default class Game {
	readonly #em = new EntityManager();

	async getUserScreen(id: string, platform: string): Promise<string> {
		console.debug("Game.getUserScreen() start");
		try{
			console.debug("Game.getUserScreen() ", await this.#em.getPlayerByID(id, platform));
		} catch(err){
			if (err instanceof PlayerNotFoundException){
				return "Player not Found. Create with /create_player <name>!"
			}
		}
		console.debug("Game.getUserScreen() end");
		return "Game.getUserScreen() not implemented";
	}


}