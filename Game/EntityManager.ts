import GameDatabase from "./GameDatabase";
export default class EntityManager {
	private readonly gdb = new GameDatabase("game.db");

	getEntityByID(entity_id: number) {
		throw new Error("not implemented");
	}

}