const sqlite3 = require("sqlite3").verbose();

class Database {
    private db;
    private fileName: string;

    constructor(fileName: string) {
        this.fileName = fileName;
        this.db = new sqlite3.Database(fileName);
    }

    async query(cmd: string) {
        return new Promise((good, bad) => {
            this.db.all(cmd, (err, rows) => {
                good(rows);
            });
        })
    }
}
