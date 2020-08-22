
  //  "typescript": "^4.0.2",
  //  "nodemon": "^2.0.4",
  //  "ts-node": "^9.0.0",
  //  "@types/node": "^14.0.27"
  //  "express": "^4.17.1",


const sqlite3 = require("sqlite3")

const db = new sqlite3.Database("fileName");

async function query(cmd: string) {
    return new Promise((good, bad) => {
        db.all(cmd, (err, rows) => {
            good(rows);
        });
    })
}

query("CREATE TABLE Entity (id INTEGER, name TEXT, description TEXT); INSERT INTO Entity Values (1,2,3); SELECT * FROM Entity").then(r=>console.log(r),err=>console.error(err))