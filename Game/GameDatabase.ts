import SQLWrapper from "../utils/SQLWrapper";

export default class GameDatabase {
    private readonly sql = new SQLWrapper("game.db");
    constructor(fileName: string) {
        
    }
}