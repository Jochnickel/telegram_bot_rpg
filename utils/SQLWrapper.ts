
export default class SQL {
	private db;
	private fileName: string;

	constructor(fileName: string) {
		this.fileName = fileName;
		this.db = require("sqlite3").verbose().Database(fileName);
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
