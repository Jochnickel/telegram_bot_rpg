
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
            
            console.log(err, rows);
            
            good(rows);
        });
    })
}

query("SELECT * FROM Entity WH;").then(r=>console.log(r),err=>console.error(err))
