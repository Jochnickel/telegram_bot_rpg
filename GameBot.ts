const readline = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
});

readline.question('Who are you?', name => {
  console.log(`Hey there ${name}!`);
  readline.close();
});
export default class GameBot {
  async runConsole(){
    const name = Prompt("What is your name?\n");
    console.log("your name is ", name);
  }
}