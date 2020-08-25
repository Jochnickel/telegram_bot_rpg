import EntityManager from "./EntityManager";

export default class Game {
  private readonly em = new EntityManager();
  getUserScreen(id: string, platform: string): string{
    throw new Error();
  }
}