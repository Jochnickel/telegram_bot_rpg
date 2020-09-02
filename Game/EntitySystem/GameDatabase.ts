import SQLWrapper from "../../utils/SQLWrapper";

export default class GameDatabase {
	readonly #sql: SQLWrapper;

	constructor(fileName: string) {
		this.#sql = new SQLWrapper(fileName);
		this.prepareDB();
	}

	private async prepareDB() {
		await this.#sql.query("CREATE TABLE IF NOT EXISTS Entity (entity_id INT PRIMARY KEY, name TEXT, description TEXT);");
		await this.#sql.query("CREATE TABLE IF NOT EXISTS LinkedEntities (entity_id INT, i INT, linked_entity_id INT);");

		await this.#sql.query("CREATE TABLE IF NOT EXISTS Player (player_id INT PRIMARY KEY, platform_id TEXT, platform TEXT, entity_id INT);");
		await this.#sql.query("CREATE TABLE IF NOT EXISTS Role (role_name TEXT PRIMARY KEY);");
		// await this.sql.query("INSERT INTO Role VALUES ('admin');");
		// await this.sql.query("INSERT INTO Role VALUES ('player');");
		await this.#sql.query("CREATE TABLE IF NOT EXISTS PlayerRoles (id INT, role_name TEXT);");
		// await this.sql.query("INSERT INTO Player VALUES (1,'1','console',NULL);");
	}


	async selectEntity(entity_id: number) {
		return this.#sql.query("SELECT * FROM Entity WHERE entity_id=?", entity_id);
	}

	async selectEntityIdWherePlayer(platform_id: string, platform: string) {
		return this.#sql.query("SELECT entity_id FROM Player WHERE platform_id=? AND platform=?", platform_id, platform);
	}

	async selectPlayerRoles(platform_id: string, platform: string) {
		const res = await this.#sql.query("SELECT role_name FROM PlayerRoles NATURAL JOIN Player WHERE platform_id=?, platform=?", platform_id, platform);
		return res[0];
	}

	async insertPlayer(platform_id: string, platform: string, entity_id: number) {
		const res = await this.#sql.query("INSERT INTO Player (platform_id, platform, entity_id) VALUES (?,?,?)", platform_id, platform, entity_id);
		console.debug("GameDatabase.insertPlayer() ", res);
		return res;

	}

	async updatePlayerEntityID(entity_id: number) {
		const res = await this.#sql.query("UPDATE Player (entity_id) VALUES (?)", entity_id);
		console.debug("GameDatabase.updatePlayerEntityID() ", res);
		return res;
	}

	async insertNewEntity(name: string, description: string) {
		const res = await this.#sql.query("INSERT INTO Entity (name, description) VALUES (?,?)", name, description);
		console.debug("GameDatabase.insertNewEntity() ", res);
		return res;
	}
	async selectPlayer(platform_id: string, platform: string) {
		return this.#sql.query("SELECT * FROM Player WHERE platform_id=? AND platform=?", platform_id, platform);
	}
}