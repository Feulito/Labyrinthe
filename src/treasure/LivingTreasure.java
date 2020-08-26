/**
 * 
 */
package treasure;
import java.util.ArrayList;
import java.util.Random;
import boardGame.BoardGame;
import card.Card;
import player.Player;

public class LivingTreasure extends Treasure  {
	private Random random;
	
	/**
	 * Crée un nouveau trésor en position 0 0
	 */
	public LivingTreasure() {
		super(null, 0,0);
		random = new Random();
	}
	
	/**
	 * Crée un nouveau trésor vivant à partir de son type, sa position (posX, posY) sur le plateau
	 * @param type : le type de trésor
	 * @param posX : la position x du trésor sur le plateau
	 * @param posY : la position y du trésor sur le plateau
	 */
	public LivingTreasure(Enum_Treasure type, int posX, int posY) {
		super(type,posX, posY);
		random = new Random();
	}
	
	/**
	 * indique si le trésor peut se déplacer vers le haut sur le plateau
	 * @param board : le plateau de cartes
	 * @param xStart : la position x du trésor
	 * @param yStart : la position y du trésor
	 * @return True si il peut se déplacer, False sinon
	 */
	private boolean canGoNorth(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart-1, yStart) && board.getBoard().get(xStart).get(yStart).getDir().getNorth() && board.getBoard().get(xStart-1).get(yStart).getDir().getSouth();
	}
	
	/**
	 * indique si le trésor peut se déplacer vers le bas sur le plateau
	 * @param board : le plateau de cartes
	 * @param xStart : la position x du trésor
	 * @param yStart : la position y du trésor
	 * @return True si il peut se déplacer, False sinon
	 */
	private boolean canGoSouth(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart+1, yStart) && board.getBoard().get(xStart).get(yStart).getDir().getSouth() && board.getBoard().get(xStart+1).get(yStart).getDir().getNorth();
	}
	
	/**
	 * indique si le trésor peut se déplacer vers la gauche sur le plateau
	 * @param board : le plateau de cartes
	 * @param xStart : la position x du trésor
	 * @param yStart : la position y du trésor
	 * @return True si il peut se déplacer, False sinon
	 */
	private boolean canGoWest(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart, yStart-1) && board.getBoard().get(xStart).get(yStart).getDir().getWest() && board.getBoard().get(xStart).get(yStart-1).getDir().getEast();
	}
	
	/**
	 * indique si le trésor peut se déplacer vers la droite sur le plateau
	 * @param board : le plateau de cartes
	 * @param xStart : la position x du trésor
	 * @param yStart : la position y du trésor
	 * @return True si il peut se déplacer, False sinon
	 */
	private boolean canGoEast(BoardGame board, int xStart, int yStart) {
		return board.isCoordValid(xStart, yStart+1) && board.getBoard().get(xStart).get(yStart).getDir().getEast() && board.getBoard().get(xStart).get(yStart+1).getDir().getWest();
	}

	/**
	 * déplace le trésor d'une carte dans une direction aléatoire
	 * @param board : le plateau du jeu
	 */
	public void move(BoardGame board) {
		int dir = this.random.nextInt(4);
		int i=getPosX();
		int j=getPosY();
		
		if (dir == 0 && board.isCoordValid(i-1, j) && canGoNorth(board, i, j) && board.getCard(i-1, j).noTreasure()){
			board.getCard(i, j).unsetTreasure();
			super.setPos(i-1, j);
			board.getCard(i-1, j).setTreasure(this);	
		}
		else if (dir == 1 && board.isCoordValid(i+1, j) && canGoSouth(board, i, j) && board.getCard(i+1, j).noTreasure()) {
			board.getCard(i, j).unsetTreasure();
			super.setPos(i+1, j);
			board.getCard(i+1, j).setTreasure(this);
		} 
		else if(dir == 2 && board.isCoordValid(i, j-1) && canGoEast(board, i, j) && board.getCard(i, j-1).noTreasure()) {
			board.getCard(i, j).unsetTreasure();
			super.setPos(i, j-1);
			board.getCard(i, j-1).setTreasure(this);
		} 
		else if(dir == 3 && board.isCoordValid(i, j+1) && canGoWest(board, i, j) && board.getCard(i, j+1).noTreasure()) {
			board.getCard(i, j).unsetTreasure();
			super.setPos(i, j+1);
			board.getCard(i, j+1).setTreasure(this);
		}
	}
	
	/**
	 * indique si le trésor va se déplacer (70% de chances)
	 * @return True si il va se déplacer, False sinon
	 */
	public boolean willMove() {
		return this.random.nextInt(100) < 70;
	}
	
	/**
	 * effectue l'action du trésor vivant sans la variante avec les pénalités
	 * @param board : le plateau du jeu
	 */
	public void makeActionWithoutVariant(BoardGame board) {
		if (willMove())
			move(board);
	}
	
	/**
	 * effectue l'action du trésor vivant avec la variante avec les pénalités
	 * (inflige un bonus ou un malus à tous les joueurs sur la même carte que le trésor)
	 * @param board : le plateau du jeu
	 */
	public void makeActionWithVariant(BoardGame board) {
		int action = this.random.nextInt(3);
		Card c = board.getCard(getPosX(), getPosY());
		
		makeActionWithoutVariant(board);
		
		ArrayList<Player> players = c.getPlayers();
		
		if (action != 0 && (!players.isEmpty())) {
			for (int i=0; i<players.size();i++) {
				if (action == 1)
					bonus(players.get(i), board);
				else
					malus(players.get(i), board);
			}
			c.checkPlayerOnCorrectCase(board);
		}
	}
	
	/**
	 * inflige un bonus au joueur passé en paramètres
	 * @param p : le joueur
	 * @param board : le plateau du jeu
	 */
	public void bonus(Player p, BoardGame board) {
		if (p.noRemainingTreasure() && !p.isOnStartPoint()) {
			p.goOnStartPoint(board);
		}
		else if (!p.noRemainingTreasure()) {
			p.goOnHoldingTreasure(board);
		}
	}
	
	/**
	 * inflige un malus au joueur passé en paramètres
	 * @param p : le joueur
	 * @param board : le plateau du jeu
	 */
	public void malus(Player p, BoardGame board) {
		if (!p.noRemainingTreasure()) {
			p.goOnStartPoint(board);
		}
		else {
			p.goOnDiagonallyOppositePoint(board);
		}
	}
}
