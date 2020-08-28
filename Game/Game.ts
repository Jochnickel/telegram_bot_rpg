import EntityManager from "./EntitySystem/EntityManager";

export default class Game {
	private readonly em = new EntityManager();

	async getUserScreen(id: string, platform: string): Promise<string> {
		console.debug("Game.getUserScreen() ", await this.em.getPlayerByID(id, platform));
		return "Game.getUserScreen() not implemented";
	}


}