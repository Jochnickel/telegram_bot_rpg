export default async function sleep(s: number){
    return new Promise((resolve, reject)=>{
        setTimeout(() => {
            resolve();
        }, s*1000);
    });
}