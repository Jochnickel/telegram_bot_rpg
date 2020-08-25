import * as readline from 'readline';

export default async function Input(str: string){
  return new Promise(resolve=>{
    const rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout
    });

    rl.question(str, answer => {
      resolve(answer);
      rl.close();
    });
  });
}