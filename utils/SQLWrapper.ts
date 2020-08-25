
const sqlite3 = require("sqlite3").verbose();
// import sqlite3 from "sqlite3";

export default class SQL {
	private db;
	private fileName: string;

	constructor(fileName: string) {
		this.fileName = fileName;
		this.db = new sqlite3.Database(fileName);
	}

	async query(cmd: string, ...values : any[]): Promise<any[]> {
		return new Promise((resolve, reject) => {
			try {
				this.db.all(cmd, values, (err, rows) => {
					if (err) {
						reject(err);
					} else {
						resolve(rows);
					}
				});
			} catch (error) {
				reject(error);
			}
		})
	}
}
