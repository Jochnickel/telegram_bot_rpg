import Input from "./Input";

export default class GameBot {
  async runConsole(){
    
    let name = await Input("What is your name?\n");
    console.log("your name is ", name);
    
    name = await Input("What is your name again?\n");
    console.log("your name is ", name, "again");
  }
}