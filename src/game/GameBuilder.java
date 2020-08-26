package game;


public class GameBuilder {
	/**
	 * renvoie un modèle de jeu du labyrinthe avec un seul joueur réel
	 * @param levelAI : le niveau des ordinateurs
	 * @param variant : la variante choisie
	 * @return Game : le modèle de jeu du labyinthe
	 */
	public Game preparePlateauSolo(int levelAI, int variant) {
		return new Game("solo", levelAI, variant);
	}
	
	/**
	 * renvoie un modèle de jeu du labyrinthe avec plusieurs joueurs réels
	 * @param nbPlayers : le nombre de joueurs réels
	 * @param levelAI :  le niveau des ordinateurs
	 * @param variant : la variante choisie
	 * @return Game : le modèle de jeu du labyrinthe
	 */
	public Game preparePlateauMulti(int nbPlayers, int levelAI, int variant) {
		return new Game("Multi", nbPlayers, levelAI, variant);
	}
}
