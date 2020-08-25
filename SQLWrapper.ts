export default class SQLWrapper {
  private readonly db;
  
  constructor(fileName: string){
    this.db = new sqlite3.Database(fileName);
  }
  
  async query(str: string, ...values: any[]){
    return new Promise((resolve, reject)={
      this.db.all(string,)
    });
    const a = this.db.all("CREATE TABLE Test (a INTEGER, b TEXT)");
  }
  
}