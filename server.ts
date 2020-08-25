console.debug("start server.ts");
import GameBot from "./GameBot";

const OFFLINE = process.argv.includes("--offline");

const gamebot = new GameBot();

gamebot.runConsole();
if (!OFFLINE){
    gamebot.runTelegram();
}


console.debug("end server.ts");