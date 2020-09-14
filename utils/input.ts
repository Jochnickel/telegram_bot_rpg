import * as readline from 'readline';

export default async function input(str: string){
	return new Promise(resolve=>{
		console.debug("input() start");
		const rl = readline.createInterface({
			input: process.stdin,
			output: process.stdout
		});

		rl.question(str, answer => {
			resolve(answer);
			console.debug("input() resolved");
			rl.close();
		});
	});
}