export default class SQLWrapper {
  private readonly db;
  
  constructor(fileName: string){
    this.db = new sqlite3.Database(fileName);
  }
  
  query(str: string, ...values: any[]){
    const a = this.db.run("CREATE TABLE Test (a INTEGER, b TEXT)");
  }
  
}