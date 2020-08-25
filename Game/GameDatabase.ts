import SQLWrapper from "../utils/SQLWrapper";

export default class GameDatabase {
    private readonly sql = new SQLWrapper("game.db");
    constructor(fileName: string) {
        
    }

    selectEntity(entity_id: number){
        const res = this.sql.query("SELECT * FROM Entity WHERE entity_id = ?", entity_id);
    }
}