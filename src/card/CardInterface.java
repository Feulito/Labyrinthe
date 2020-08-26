package card;
import treasure.Treasure;

import java.util.ArrayList;

import boardGame.BoardGame;
import player.Player;
import treasure.Enum_Treasure;

public interface CardInterface {
	
	/**
	 * Permet de mettre a jour la position
	 * @param x Position x
	 * @param y Position y
	 */
	public void setPos(int x, int y);
	
	/**
	 * permet de tourner une carte
	 * @param nb nombre de fois a faire tourner
	 */
	public void rotate(int nb);
	
	/**
	 * Permet d'inserer une carte dans une certaine position
	 * @param x Position x
	 * @param y Position y
	 * 
	 * @return Si l insertion se passe bien return true, false sinon
	 */
	public boolean insert(int x,int y);
	
	/**
	 * Permet de sortir une carte du board
	 */
	public void goOut();
	
	/**
	 * Met a jour la position de l escape card
	 * @param x Position x
	 * @param y Position y
	 */
	public void setPosEscape(int x, int y);
	
	/**
	 * decal la carte d une case dans une direction
	 * @param dir direction dans laquelle il faut décal
	 */
	public void decal(String dir);
	
	/**
	 * Permet de récuperer le trésor posé sur la carte
	 * @return Trésor
	 */
	public Treasure getTreasure();
	
	/**
	 * Permet de récuperer la direction de la carte
	 * @return Direction
	 */
	public Direction getDir();
	
	/**
	 * Met a jour le trésor
	 * @param tres Trésor a mettre sur la carte
	 */
	public void setTreasure(Treasure tres);
	
	/**
	 * Met a jour le trésor
	 * @param type Trésor a mettre sur la carte
	 */
	public void setTreasure(Enum_Treasure name);
	
	/**
	 * Enleve le trésor de la carte
	 */
	public void unsetTreasure();
	
	/**
	 * Verifie qu'il n'y a pas de trésor sur la carte
	 * @return Vrai si il n'y a pas de trésor sur la carte
	 */
	public boolean noTreasure();
	
	/**
	 * Type de la carte
	 * @return un string entre 1 et 3 pour le type de la carte / 0 si erreur
	 */
	public String printType();
	
	/**
	 * retourne l ancien x de l'escape card
	 * @return position x
	 */
	public int getEscapeX();
	
	/**
	 * retourne l ancien y de l'escape card
	 * @return position y
	 */
	public int getEscapeY();
	
	/**
	 * Ajoute un joueur sur la case
	 * @param p Joueur a mettre sur la case
	 */
	public void addPlayerOnCase(Player p);
	
	/**
	 * Retire un joueur sur la case
	 * @param p Joueur a enlever sur la case
	 */
	public void removePlayerOnCase(Player p);
	
	/**
	 * Permet de recevoir un joueur a partir d'une autre Card
	 * @param left carte qui donne son joueur
	 */
	public void receivePlayersOnCase(Card left);
	
	/**
	 * Permet de donner un joueur vers une autre Card
	 * @param enter carte qui reçoit son joueur
	 */
	public void givePlayersOnCase(Card enter);
	
	/**
	 * Change la position du joueur sur la case avec la position actuelle de la carte
	 */
	public void changeCoordsPlayersOnCase();
	
	/**
	 * Permet de recup le joueur passé en paramètre
	 * @return Joueur a recup
	 */
	public Player getPlayer(Player p);
	
	/**
	 * Recup tous les joueurs d'une Card
	 * @return liste des joueurs sur la Card
	 */
	public ArrayList<Player> getPlayers();
	
	/**
	 * Verifie qu'un joueur précis est sur une case
	 * @return null si pas trouver / le joueur lui meme sinon
	 */
	public Player playerInCase(Player p);
	
	/**
	 * Test si il y a des joueurs sur la case
	 * @return True si il y a au moins un joueur / false sinon
	 */
	public boolean hasPlayer();
	
	/**
	 * Verifie qu'un player est sur la bonne card
	 * @param board Le board a verif
	 */
	public void checkPlayerOnCorrectCase(BoardGame board);
	
	/**
	 * Permet de reset les anciennes positions de l escape Card pour pouvoir recommencer une partie par exemple
	 */
	public void resetEscape();
	
}
