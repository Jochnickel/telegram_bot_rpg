import Input from "./Input";
import Game from "./Game";

export default class GameBot {
  
  private readonly game = new Game();

  async runTelegram(){
    
  }
  async runConsole(){
    const USER_ID = "1";
    const PLATFORM = "console";
    game.getUserScreen(USER_ID,PLATFORM);
  }
}