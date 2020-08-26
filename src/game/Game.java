package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import factory.FactoryProducer;
import factory.PlayerFactory;
import player.Player;
import player.PlayerAI;

import treasure.Treasure;
import boardGame.BoardGame;

public class Game {
	private static final String[] colors = {"rouge", "vert", "bleu", "jaune"};
	private static final int[][] positions = {{0,0}, {0,6}, {6,0}, {6,6}};
	private String theme;
	private ArrayList<Player> players = new ArrayList<Player>();
	private PlayerFactory playerFactory;

	private BoardGame board;
	private int nbPlayers;
	private int levelAI;
	private int variant;
	private int round;
	private boolean endGame;
	private Random r = new Random();
	
	/**
	 * Construit un jeu de labyrinthe à l'aide de son thème, du niveau des 3 ordinateurs (1 ou 2) et du numéro
	 * de la variante choisie (0 1 ou 2)
	 * @param theme : le thème choisi
	 * @param levelAI : le niveau des ordinateurs
	 * @param variant : le numéro de la variante
	 */
	public Game(String theme, int levelAI, int variant) {
		this(theme, 1, levelAI, variant);
	}
	
	/**
	 * Construit un jeu de labyrinthe à l'aide de son thème, du nombre de joueurs, du niveau des ordinateurs (1 ou 2) et du numéro
	 * de variante choisi (0 1 ou 2)
	 * @param theme :  le thème choisi 
	 * @param nbPlayers : le nombre de joueurs choisi
	 * @param levelAI : le niveau des ordinateurs
	 * @param variant : le numéro de la variante
	 */
	public Game(String theme, int nbPlayers, int levelAI, int variant) {
		this.theme = theme;
		this.playerFactory = (PlayerFactory) FactoryProducer.getFactory("player");
	
		this.nbPlayers = nbPlayers;
		this.levelAI = levelAI;
		this.variant = variant;
		this.round = 0;
		this.endGame = false;
		
		this.board = new BoardGame();
		
		this.createPlayers();
		this.setInitialCard();
		this.board.getEscapeCard().resetEscape();
		this.setListsTreasure();
	}
	
	/**
	 * incrémente l'indice du tour de manière circulaire
	 */
	private void incRound() {
		this.round++;
		this.round %= 4;
	}
	
	/**
	 * récupère le joueur dont c'est le tour
	 * @return Player : le joueur dont c'est le tour
	 */
	public Player getCurrentPlayer() {
		return this.players.get(this.round); 
	}
	
	/**
	 * détermine si c'est au tour d'un ordinateur de jouer
	 * @return True c'est au tour de l'ordinateur de joueur, False sinon
	 */
	public boolean isAITurn() {
		try {
			PlayerAI a = (PlayerAI) this.players.get(this.round); 
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * fais joueur l'ordinateur dont c'est le tour suivant le niveau choisi
	 */
	public void playAI() {
		if (levelAI == 1) {
			((PlayerAI) this.players.get(this.round)).makePartialProgrammedStrategy(this.board);
		}
		else if (levelAI == 2) {
			((PlayerAI) this.players.get(this.round)).makeProgrammedStrategy(this.board);
		}
	}
	
	/**
	 * effectue le premier tour d'une partie
	 */
	public void firstTurn() {
		this.round = r.nextInt(4);
		if (this.isAITurn()) {
			playAI();
			endTurn();
		}
	}
	
	/**
	 * indique si c'est la fin de la partie
	 * @return True si la partie est fini, False sinon
	 */
	public boolean isEndGame() {
		return this.endGame;
	}
	
	/**
	 * effectue la fin du tour et fait joueur les ordinateurs entre le joueur non-ordinateur précédent 
	 * et le prochain joueur non-ordinateur
	 */
	public void endTurn() {
		this.players.get(this.round).unPick();
		if (this.variant!=0) {
			this.board.moveLivingTreasures(this.variant);
		}
		if (isWinner(this.players.get(this.round))){
			endGame = true;
		}else {
			incRound();
			if (isAITurn()){ 
				playAI();
				endTurn();
				}
			}
		}
	
	/**
	 * initialise la liste des trésor de chaque joueur en début de partie
	 */
	private void setListsTreasure() {
		ArrayList<Treasure> treasures = board.getTreasures();
		Collections.shuffle(treasures);
		int ind=0;
		for (int i=0; i<this.players.size(); i++){
			for (int j = 0; j<6; j++) { //6 cartes de trésor par joueur
				this.players.get(i).addTreasure(treasures.get(ind));
				ind++;
			}
			this.players.get(i).initHoldingTreasure();
		}
	}
	
	/**
	 * verifie si le joueur en paramètre est le gagnant de la partie
	 * @param p : le joueur
	 * @return True si il est le gagant de la partie, False sinon
	 */
	public boolean isWinner(Player p) {
		return p.isOnStartPoint() && p.noRemainingTreasure();
	}
	
	/**
	 * renvoie la couleur du joueur qui a gagné la partie
	 * @return String : la couleur du joueur gagnant
	 */
	public String endGame() {
		for (int i=0; i<4; i++) {
			if (isWinner(players.get(i)))
				return players.get(i).getColor();
		}
		return null;
	}

	/**
	 * renvoie le thème utilisé dans la partie
	 * @return String : le thème de la partie
	 */
	public String getTheme() {
		return theme;
	}
	
	/**
	 * change le thème de la partie
	 * @param theme : le nouveau thème de la partie
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	/**
	 * renvoie la liste des joueurs de la partie
	 * @return ArrayList<Player> : la liste des joueurs
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * crée les joueurs de la partie en fonction du nombre de joueurs non-ordinateurs choisi
	 * et du niveau des ordinateurs
	 */
	private void createPlayers() {
		Player p;
		for (int i=0; i<this.nbPlayers; i++) {
			p = this.playerFactory.newPlayer(positions[i][0], positions[i][1], colors[i]);
			this.players.add(p);
		}		
		for (int i = this.nbPlayers; i < 4; i++) {
			p = this.playerFactory.newPlayerAI(positions[i][0], positions[i][1], colors[i]);
			this.players.add(p);
		}
	}
	
	/**
	 * place les joueurs sur leur positions de départ
	 */
	public void setInitialCard() {
		Player player;
		int posX;
		int posY;
		for (int i = 0; i < this.players.size(); i++) {
			player = this.players.get(i);
			posX = player.getPosX();
			posY = player.getPosY();
			this.board.getBoard().get(posX).get(posY).addPlayerOnCase(player);
		}	
	}
	
	/**
	 * permet de récupérer le plateau du jeu
	 * @return BoardGame : le plateau du jeu
	 */
	public BoardGame getBoard() {
		return this.board;
	}
	
	/**
	 * permet de changer de plateau de jeu
	 * @param board : le nouveau plateau de jeu
	 */
	public void setBoard(BoardGame board) {
		this.board = board;
	}
}
