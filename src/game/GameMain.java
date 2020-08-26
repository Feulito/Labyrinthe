package game;

import card.Card;
import player.Player;

public class GameMain{
	
	/**
	 * permet de tester le modèle du jeu du labyrinthe
	 * @param args : les arguments
	 */
	public static void main(String[] args) {
		System.out.println("Lancement du test :");
		System.out.println("Pr�paration du plateau solo...");
		
		GameBuilder gameBuilder = new GameBuilder();
		
		Game game = gameBuilder.preparePlateauSolo(0,0);
		
		for (int i = 0; i < game.getPlayers().size(); i++) {
			String couleur = game.getPlayers().get(i).getColor();
			String position = "(" + game.getPlayers().get(i).getPosX() + "," + game.getPlayers().get(i).getPosY() + ")";
			System.out.println("Joueur " + i + " couleur : " + couleur + " position : " + position);
		}
		
		System.out.println("Le plateau :");
		
		Player player = game.getPlayers().get(0);
		
		game.getBoard().getBoard().get(0).set(1, new Card("ligne", 0, 1, null));
		game.getBoard().printBoard();

		System.out.println("Position : (" + player.getPosX() + "," + player.getPosY() + ")");
		
		System.out.println("type = " + game.getBoard().getEscapeCard().printType());
		
		game.getBoard().insertCardUp(1);
		System.out.println("type = " + game.getBoard().getEscapeCard().printType());
		game.getBoard().insertCardDown(3);
		game.getBoard().printBoard();
	}

}
