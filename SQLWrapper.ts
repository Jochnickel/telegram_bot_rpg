const sqlite3 = require("sqlite3").verbose();

class SQLWrapper {
	private db;
	private fileName: string;

	constructor(fileName: string) {
		this.fileName = fileName;
		this.db = new sqlite3.Database(fileName);
	}

	async query(cmd: string) {
		return new Promise((resolve, reject) => {
			this.db.all(cmd, (err, rows) => {
				if (err) {
					reject(err);
				} else {
					resolve(rows);
				}
			});
		})
	}
}
