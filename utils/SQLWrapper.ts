// const sqlite3 = require("sqlite3").verbose();
import * as sqlite3 from "sqlite3";

// const AsyncLock = require("async-lock");
import * as AsyncLock from "async-lock";

export default class SQL {
	readonly #db: sqlite3.Database;
	readonly #fileName: string;
	readonly #lock = new AsyncLock();

	constructor(fileName: string) {
		this.#fileName = fileName;
		this.#db = new sqlite3.Database(fileName);
	}

	private async synchronized12(foo: () => any) {
		return new Promise((resolve, reject) => {
			this.#lock.acquire('key', (done) => {
				resolve(foo());
				done();
			}, reject);
		})
	}

	private async synchronized(foo: () => any) {
		return this.#lock.acquire('key', foo);
	}

	async query(cmd: string, ...values: any[]): Promise<any[]> {
		return new Promise((resolve, reject) => {
			try {
				const lockableFunction = () => {
					this.#db.all(cmd, values, (err, rows) => {
						if (err) {
							console.error(err);
							reject(err);
						} else {
							resolve(rows);
						}
					});
				};
				this.synchronized(lockableFunction);
			} catch (error) {
				console.error(error);
				reject(error);
			}
		})
	}
}
