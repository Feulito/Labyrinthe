package player;

import java.util.ArrayList;

import boardGame.BoardGame;
import card.Card;
import treasure.Treasure;

public class Player implements PlayerInterface {
	private int posX;
	private int posY;
	private int startX;
	private int startY;
	private ArrayList<Treasure> treasure;
	private Treasure holdingTreasure;
	private String color;
	private boolean pick;
	
	/**
	 * Constructeur d'un joueur avec sa position et sa couleur
	 * @param posX : La position X du joueur sur le board
	 * @param posY : La position Y du joueur sur le board
	 * @param color : La couleur du joueur
	 */
	public Player(int posX, int posY, String color) {
		this.posX = posX;
		this.posY = posY;
		this.startX = posX;
		this.startY = posY;
		this.treasure = new ArrayList<Treasure>();
		this.color = color;
		this.pick = false;
	}
	
	
	/**
	 * Constructeur par defaut d'un joueur en position (0,0)
	 */
	public Player() {
		this.posX = 0;
		this.posY = 0;
		this.startX = 0;
		this.startY = 0;
		this.treasure = new ArrayList<Treasure>();
		this.color = "vert";
		this.pick = false;
	}

	/**
	 * Fonction permettant au joueur de recuperer le tresor d'une carte
	 * @param card : La carte sur laquelle se trouve le joueur
	 */
	@Override
	public void pickTreasure(Card card) {
		this.pick = true;
		this.treasure.remove(this.treasure.indexOf(this.holdingTreasure));
		if(!this.treasure.isEmpty()) {
			this.holdingTreasure = this.treasure.get(0);
		}
		else this.holdingTreasure = null;
	}
	
	/**
	 * Fonction verifiant si un joueur peut aller au nord ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	@Override
	public boolean canGoNorth(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart-1, yStart) && board.getBoard().get(xStart).get(yStart).getDir().getNorth() && board.getBoard().get(xStart-1).get(yStart).getDir().getSouth();
	}
	
	/**
	 * Fonction verifiant si un joueur peut aller au Sud ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	@Override
	public boolean canGoSouth(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart+1, yStart) && board.getBoard().get(xStart).get(yStart).getDir().getSouth() && board.getBoard().get(xStart+1).get(yStart).getDir().getNorth();
	}
	
	/**
	 * Fonction verifiant si un joueur peut aller e l'Ouest ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	@Override
	public boolean canGoWest(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart, yStart-1) && board.getBoard().get(xStart).get(yStart).getDir().getWest() && board.getBoard().get(xStart).get(yStart-1).getDir().getEast();
	}
	
	/**
	 * Fonction verifiant si un joueur peut aller e l'est ou non
	 * @param board : le board de jeu
	 * @return True s'il peut se deplacer, false sinon
	 */
	@Override
	public boolean canGoEast(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart, yStart+1) && board.getBoard().get(xStart).get(yStart).getDir().getEast() && board.getBoard().get(xStart).get(yStart+1).getDir().getWest();
	}
	
	/**
	 * Fonction bougeant le player vers l'ouest
	 * @param board : Le board du jeu
	 */
	@Override
	public void moveWest(BoardGame board) {
		moveWithoutCheck(board, this.posX, this.posY-1);
	}

	/**
	 * Fonction bougeant le player vers l'est
	 * @param board : Le board du jeu
	 */
	@Override
	public void moveEast(BoardGame board) {
		moveWithoutCheck(board, this.posX, this.posY+1);
	}

	/**
	 * Fonction bougeant le player vers le sud
	 * @param board : Le board du jeu
	 */
	@Override
	public void moveSouth(BoardGame board) {
		moveWithoutCheck(board, this.posX+1, this.posY);
	}

	/**
	 * Fonction bougeant le player vers le nord
	 * @param board : Le board du jeu
	 */
	@Override
	public void moveNorth(BoardGame board) {
		moveWithoutCheck(board, this.posX-1, this.posY);
	}
	
	/**
	 * Fonction bougeant le joueur sans verification
	 * @param board : Le board du jeu
	 * @param x : La coordonnee x d'arrivee
	 * @param y : La coordonnee y d'arrivee
	 */
	@Override
	public void moveWithoutCheck(BoardGame board, int x, int y) {
		board.getCard(this.posX, this.posY).removePlayerOnCase(this);
		board.getCard(x, y).addPlayerOnCase(this);
		setPos(x, y);
		
		
		if (board.checkPlayerOnTreasure(this))
			pickTreasure(board.getCard(posX, posY));
	}
	
	/**
	 * Fonction bougeant le joueur sans attraper de tresor
	 * @param board : Le board du jeu
	 * @param x : La coordonnee x d'arrivee
	 * @param y : La coordonnee y d'arrivee
	 */
	@Override
	public void moveWithoutPickTreasure(BoardGame board, int x, int y) {
		board.getCard(this.posX, this.posY).removePlayerOnCase(this);
		board.getCard(x, y).addPlayerOnCase(this);
		setPos(x, y);
	}
	
	/**
	 * Fonction pour set la position du joueur en (x,y)
	 * @param x : La coordonnee x du joueur dans le plateau
	 * @param y : La coordonnee y du joueur dans le plateau
	 */
	@Override
	public void setPos(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	/**
	 * Fonction retournant la position X du joueur
	 * @return int : Position X du joueur
	 */
	@Override
	public int getPosX() {
		return posX;
	}

	/**
	 * Fonction changeant la position X du joueur
	 * @param posX : La nouvelle position X du joueur
	 */
	@Override
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Fonction retournant la position Y du joueur
	 * @return int : La position Y du joueur
	 */
	@Override
	public int getPosY() {
		return posY;
	}

	/**
	 * Fonction changeant la position Y du joueur
	 * @param posY : La nouvelle position Y du joueur
	 */
	@Override
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	/**
	 * Fonction retournant la liste des tresors du joueur
	 * @return ArrayList<Treasure> : La liste des tresors du joueur
	 */
	@Override
	public ArrayList<Treasure> getTreasure() {
		return this.treasure;
	}

	/**
	 * Fonction changeant la liste des tresors du joueur
	 * @param treasure : La nouvelle liste des tresors du joueur
	 */
	@Override
	public void setTreasure(ArrayList<Treasure> treasure) {
		this.treasure = treasure;
	}
	
	/**
	 * Fonction ajoutant un tresor au joueur
	 * @param t : Le tresor a ajouter
	 */
	@Override
	public void addTreasure(Treasure t) {
		this.treasure.add(t);
	}

	/**
	 * Fonction retournant le tresor que le joueur a en main
	 * @return Treasure : Le tresor que le joueur tient en main
	 */	
	@Override
	public Treasure getHoldingTreasure() {
		return holdingTreasure;
	}

	/**
	 * Fonction changeant le tresor que tient le joueur
	 * @param holdingTreasure : Un tresor
	 */	
	@Override
	public void setHoldingTreasure(Treasure holdingTreasure) {
		this.holdingTreasure = holdingTreasure;
	}
	
	/**
	 * Fonction initialisant le tresor que tient le joueur
	 */
	@Override
	public void initHoldingTreasure() {
		this.holdingTreasure = this.treasure.get(0);
	}
	
	/**
	 * Fonction retournant la couleur du joueur
	 * @return String : La couleur du joueur
	 */	
	@Override
	public String getColor() {
		return color;
	}

	/**
	 * Fonction changeant la couleur du joueur
	 * @param color : La nouvelle couleur du joueur
	 */	
	@Override
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Fonction retournant la coordonnee x de depart du joueur
	 * @return int : La coordonnee x de depart du joueur
	 */
	@Override
	public int getStartX() {
		return this.startX;
	}

	/**
	 * Fonction retournant la coordonnee y de depart du joueur
	 * @return int : La coordonnee y de depart du joueur
	 */
	@Override
	public int getStartY() {
		return this.startY;
	}

	/**
	 * Fonction verifiant si le joueur est a son point de depart ou non
	 * @return True si le joueur est a sa case de depart, false sinon
	 */
	@Override
	public boolean isOnStartPoint() {
		return this.posX == this.startX && this.posY == this.startY;
	}

	/**
	 * Fonction permettant au joueur de retourner a son point de depart
	 * Cette fonction est appelee lors d'un Bonus d'un tresor vivant
	 * @param board : Le board du jeu
	 */
	@Override
	public void goOnStartPoint(BoardGame board) {
		moveWithoutPickTreasure(board, this.startX, this.startY);
	}

	/**
	 * Fonction permettant au joueur ordinateur d'aller vers le tresor recherche
	 * Cette fonction est appelee lors d'un Bonus d'un tresor vivant
	 * @param board
	 */
	@Override
	public void goOnHoldingTreasure(BoardGame board) {
		moveWithoutPickTreasure(board, this.holdingTreasure.getPosX(), this.holdingTreasure.getPosY());
	}
	
	/**
	 * Fonction permettant au joueur d'aller a l'oppose de son point actuel.
	 * @param board
	 */
	@Override
	public void goOnDiagonallyOppositePoint(BoardGame board) {
		int nbRowsCols = BoardGame.NUMBER_ROWS_COLS;
		
		if (this.startX == 0 && this.startY == 0)
			moveWithoutPickTreasure(board, nbRowsCols-1, nbRowsCols-1);
		else if (this.startX == nbRowsCols-1 && this.startY == nbRowsCols-1)
			moveWithoutPickTreasure(board, 0,0);
		else if (this.startX == 0 && this.startY == nbRowsCols-1)
			moveWithoutPickTreasure(board, nbRowsCols-1, 0);
		else if (this.startX == nbRowsCols-1 && this.startY == 0)
			moveWithoutPickTreasure(board, 0, nbRowsCols-1);
	}

	/**
	 * Fonction verifiant si le joueur est positionne sur le tresor qu'il recherche
	 * @return true s'il est sur le tresor recherche, false sinon
	 */
	@Override
	public boolean isOnHoldingTreasure() {
		return this.posX == this.holdingTreasure.getPosX() && this.posY == this.holdingTreasure.getPosY();
	}

	/**
	 * Fonction verifiant si le joueur n'a plus de tresors a chercher
	 * @return true si le joueur n'a plus de tresors a chercher, false sinon
	 */
	@Override
	public boolean noRemainingTreasure() {
		return this.treasure.isEmpty();
	}

	/**
	 * Fonction indiquant que le joueur n'a pas pioche
	 */
	@Override
	public void unPick() {
		this.pick = false;
	}

	/**
	 * Fonction retournant si le joueur a pioche ou non
	 * @return true si le joueur a pioche, false sinon
	 */
	@Override
	public boolean getPick() {
		return this.pick;
	}
}
