console.debug("server.ts start");
import GameBot from "./GameBot";

const OFFLINE = true;// process.argv.includes("--offline");

const gamebot = new GameBot();
console.debug("server.ts middle")
gamebot.runConsole();
if (!OFFLINE){
    gamebot.runTelegram();
}


console.debug("server.ts end");