/*
 * @author Duterte Sebastien
 */
package player;

import card.Card;
import treasure.Treasure;

import java.util.ArrayList;

import boardGame.BoardGame;

public interface PlayerInterface {
	
	/**
	 * Fonction permettant au joueur de recuperer le tresor d'une carte
	 * @param card : La carte sur laquelle se trouve le joueur
	 */
	public void pickTreasure(Card card);

	/**
	 * Fonction verifiant si un joueur peut aller au nord ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	public boolean canGoNorth(BoardGame board, int xStart, int yStart);
	
	/**
	 * Fonction verifiant si un joueur peut aller au Sud ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	public boolean canGoSouth(BoardGame board, int xStart, int yStart);
	
	/**
	 * Fonction verifiant si un joueur peut aller e l'Ouest ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	public boolean canGoWest(BoardGame board, int xStart, int yStart);
	
	/**
	 * Fonction verifiant si un joueur peut aller a l'est ou non
	 * @param board : le board du jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	public boolean canGoEast(BoardGame board, int xStart, int yStart);
	
	/**
	 * Fonction bougeant le player vers l'ouest
	 * @param board : Le board du jeu
	 */
	public void moveWest(BoardGame board);
	
	/**
	 * Fonction bougeant le player vers l'est
	 * @param board : Le board du jeu
	 */
	public void moveEast(BoardGame board);
	
	/**
	 * Fonction bougeant le player vers le sud
	 * @param board : Le board du jeu
	 */
	public void moveSouth(BoardGame board);
	
	/**
	 * Fonction bougeant le player vers le nord
	 * @param board : Le board du jeu
	 */
	public void moveNorth(BoardGame board);
	
	/**
	 * Fonction bougeant le joueur sans verification
	 * @param board : Le board du jeu
	 * @param x : La coordonnee x d'arrivee
	 * @param y : La coordonnee y d'arrivee
	 */
	public void moveWithoutCheck(BoardGame board, int x, int y);
	
	/**
	 * Fonction bougeant le joueur sans attraper de tresor
	 * @param board : Le board du jeu
	 * @param x : La coordonnee x d'arrivee
	 * @param y : La coordonnee y d'arrivee
	 */
	public void moveWithoutPickTreasure(BoardGame board, int x, int y);
	
	/**
	 * Fonction pour set la position du joueur en (x,y)
	 * @param x : La coordonnee x du joueur dans le plateau
	 * @param y : La coordonnee y du joueur dans le plateau
	 */
	public void setPos(int x, int y);
	
	/**
	 * Fonction retournant la position X du joueur
	 * @return int : Position X du joueur
	 */
	public int getPosX();
	/**
	 * Fonction changeant la position X du joueur
	 * @param posX : La nouvelle position X du joueur
	 */
	public void setPosX(int posX);

	/**
	 * Fonction retournant la position Y du joueur
	 * @return int : La position Y du joueur
	 */
	public int getPosY();
	/**
	 * Fonction changeant la position Y du joueur
	 * @param posY : La nouvelle position Y du joueur
	 */
	public void setPosY(int posY);
	
	/**
	 * Fonction retournant la liste des tresors du joueur
	 * @return ArrayList<Treasure> : La liste des tresors du joueur
	 */
	public ArrayList<Treasure> getTreasure();
	
	/**
	 * Fonction changeant la liste des tresors du joueur
	 * @param treasure : La nouvelle liste des tresors du joueur
	 */
	public void setTreasure(ArrayList<Treasure> treasure);
	
	/**
	 * Fonction ajoutant un tresor au joueur
	 * @param t : Le tresor a ajouter
	 */
	public void addTreasure(Treasure t);

	/**
	 * Fonction retournant le tresor que le joueur a en main
	 * @return Treasure : Le tresor que le joueur tient en main
	 */
	public Treasure getHoldingTreasure();

	/**
	 * Fonction changeant le tresor que tient le joueur
	 * @param holdingTreasure : Un tresor
	 */
	public void setHoldingTreasure(Treasure holdingTreasure);

	/**
	 * Fonction initialisant le tresor que tient le joueur
	 */
	public void initHoldingTreasure();
	
	/**
	 * Fonction retournant la couleur du joueur
	 * @return String : La couleur du joueur
	 */
	public String getColor();

	/**
	 * Fonction changeant la couleur du joueur
	 * @param color : La nouvelle couleur du joueur
	 */
	public void setColor(String color);
	
	/**
	 * Fonction retournant la coordonnee x de depart du joueur
	 * @return int : La coordonnee x de depart du joueur
	 */
	public int getStartX();
	
	/**
	 * Fonction retournant la coordonnee y de depart du joueur
	 * @return int : La coordonnee y de depart du joueur
	 */
	public int getStartY();
	
	/**
	 * Fonction verifiant si le joueur est a son point de depart ou non
	 * @return True si le joueur est a sa case de depart, false sinon
	 */
	public boolean isOnStartPoint();
	
	/**
	 * Fonction permettant au joueur de retourner a son point de depart
	 * Cette fonction est appelee lors d'un Bonus d'un tresor vivant
	 * @param board : Le board du jeu
	 */
	public void goOnStartPoint(BoardGame board);
	
	/**
	 * Fonction permettant au joueur ordinateur d'aller vers le tresor recherche
	 * Cette fonction est appelee lors d'un Bonus d'un tresor vivant
	 * @param board
	 */
	public void goOnHoldingTreasure(BoardGame board);
	
	/**
	 * Fonction permettant au joueur d'aller a l'oppose de son point actuel.
	 * @param board
	 */
	public void goOnDiagonallyOppositePoint(BoardGame board);
	
	/**
	 * Fonction verifiant si le joueur est positionne sur le tresor qu'il recherche
	 * @return true s'il est sur le tresor recherche, false sinon
	 */
	public boolean isOnHoldingTreasure();
	
	/**
	 * Fonction verifiant si le joueur n'a plus de tresors a chercher
	 * @return true si le joueur n'a plus de tresors a chercher, false sinon
	 */
	public boolean noRemainingTreasure();
	
	/**
	 * Fonction indiquant que le joueur n'a pas pioche
	 */
	public void unPick();
	
	/**
	 * Fonction retournant si le joueur a pioche ou non
	 * @return true si le joueur a pioche, false sinon
	 */
	public boolean getPick();
	
}
