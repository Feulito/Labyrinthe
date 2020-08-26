package boardGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import card.Card;
import factory.FactoryProducer;
import factory.TreasureFactory;
import factory.CardFactory;
import treasure.Enum_Treasure;
import treasure.LivingTreasure;
import treasure.Treasure;
import player.Player;

public class BoardGame {
	public static final int NUMBER_ROWS_COLS = 7;
	
	private Card escapeCard;
	private ArrayList<ArrayList<Card>> board;
	private ArrayList<Treasure> treasures;
	private static final TreasureFactory treasureFactory = (TreasureFactory) FactoryProducer.getFactory("treasure"); 
	private static final CardFactory cardFactory = (CardFactory) FactoryProducer.getFactory("card");
	private static final String LINE = "ligne";
	private static final String ANGLE = "angle";
	private static final String CROSSOVER = "croisement";
	private Random r = new Random();
	
	/**
	 * Constructeur d'un plateau de jeu
	 */
	public BoardGame() {
		createBoard();
	}
	
	/**
	 * cree la matrice de cartes du plateau de jeu et y insere les cartes immobiles
	 */
	private void createBoard() {
		board = new ArrayList<ArrayList<Card>>();
		for (int i=0; i<NUMBER_ROWS_COLS; i++) {
			board.add(new ArrayList<Card>());
			if (i % 2 != 0) {
				for (int j = 0; j < NUMBER_ROWS_COLS; j++) {
					board.get(i).add(null);
				}
			}
		}
		
		this.treasures = new ArrayList<Treasure>();
		insertFixCards();
	}
	
	/** 
	 * insere les cartes immobiles dans la matrice de cartes et prepare les cartes mobiles
	 */
	private void insertFixCards() {
		Card c;
		Treasure t;

		board.get(0).add(new Card(ANGLE, 0, 0, null));
		board.get(0).add(null);
		
		t = treasureFactory.newTreasure(Enum_Treasure.SKULL, 0,2);
		treasures.add(t);
		board.get(0).add(new Card(CROSSOVER, 0, 2, t));
		board.get(0).add(null);
		
		t = treasureFactory.newTreasure(Enum_Treasure.SWORD, 0,4);
		treasures.add(t);
		board.get(0).add(new Card(CROSSOVER, 0, 4, t));
		board.get(0).add(null);
		
		c = cardFactory.newCard(ANGLE, 0, 6, null);
		c.rotate(1);
		
		board.get(0).add(c);
		
		t = treasureFactory.newTreasure(Enum_Treasure.COINBAG,2,0);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 2, 0, t);
		c.rotate(3);
		
		board.get(2).add(c);
		board.get(2).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.KEYS,2,2);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 2, 2, t);
		c.rotate(3);
		
		board.get(2).add(c);
		board.get(2).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.EMERALD,2,4);
		treasures.add(t);
		board.get(2).add(new Card(CROSSOVER, 2, 4, t));
		board.get(2).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.ARMOR,2,6);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 2, 6, t);
		c.rotate(1);
		
		board.get(2).add(c);

		t = treasureFactory.newTreasure(Enum_Treasure.GRIMOIRE,4,0);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 4, 0, t);
		c.rotate(3);
		
		board.get(4).add(c);
		board.get(4).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.CROWN,4,2);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 4, 2, t);
		c.rotate(2);
		
		board.get(4).add(c);
		board.get(4).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.TREASURECHEST,4,4);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 4, 4, t);
		c.rotate(1);
		
		board.get(4).add(c);
		board.get(4).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.CANDLEHOLDER,4,6);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 4, 6, t);
		c.rotate(1);
		board.get(4).add(c);

		c = cardFactory.newCard(ANGLE, 6, 0, null);
		c.rotate(3);
		board.get(6).add(c);
		board.get(6).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.TREASUREMAP,6,2);		
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 6, 2, t);
		c.rotate(2);
		
		board.get(6).add(c);
		board.get(6).add(null);

		t = treasureFactory.newTreasure(Enum_Treasure.GOLDENRING,6,4);
		treasures.add(t);
		c = cardFactory.newCard(CROSSOVER, 6, 4, t);
		c.rotate(2);
		
		board.get(6).add(c);
		board.get(6).add(null);
		
		c = cardFactory.newCard(ANGLE, 6, 6, null);
		c.rotate(2);
		
		board.get(6).add(c);
		
		prepareCards();
	}
	
	/**
	 * crée et fixes les trésors vivants sur les cartes mobiles préparées de manière aléatoire
	 * @param prepare : la liste de toutes les cartes du jeu
	 */
	private void prepareLivingTreasures(ArrayList<Card> prepare) {
		ArrayList<Enum_Treasure> livingTreasures = Enum_Treasure.ARMOR.getLivingTreasuresNames();
		ArrayList<Treasure> lTreasures = new ArrayList<Treasure>();
		
		
		for (int i=0; i<livingTreasures.size(); i++) {
			lTreasures.add(treasureFactory.newLivingTreasure(livingTreasures.get(i), 0, 0));
		}
		Collections.shuffle(livingTreasures);
		
		for (int i=0, j=22; i<lTreasures.size(); i++, j++) {
			prepare.get(j).setTreasure(lTreasures.get(i)); 
			//22 est l'indice de la 7 e carte d'angle comme ça 6 cartes d'angles et les 6 croisements auront un trésor
		}
		Collections.shuffle(prepare);
	}
	
	/**
	 * prepare les cartes mobiles du jeu et les trésors vivants
	 */
	private void prepareCards() {
		int nbLignes = 0;
		int nbAngles = 0;
		
		ArrayList<Card> prepare = new ArrayList<Card>();
		
		
		for (int i = 0; i < 34; i++) {
			if (nbLignes < 12) {
				nbLignes++;
				prepare.add(cardFactory.newCard(LINE, 0, 0, null));
			} else if (nbAngles < 16) {
				nbAngles++;
				prepare.add(cardFactory.newCard(ANGLE, 0, 0, null));
			} else {
				prepare.add(cardFactory.newCard(CROSSOVER, 0, 0, null));
			}
		}
		
		
		prepareLivingTreasures(prepare);
		
		
		int ind = 0;
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(0).size(); j++) {
				if (board.get(i).get(j) == null) {
					prepare.get(ind).setPos(i,j);
					prepare.get(ind).rotate(r.nextInt(4));
					board.get(i).set(j, prepare.get(ind));
					if (prepare.get(ind).getTreasure() != null)
						treasures.add(prepare.get(ind).getTreasure());
					ind++;
				}
			}
		}
		prepare.get(ind).setPos(-1, -1);
		if (prepare.get(ind).getTreasure()!=null) {
			treasures.add(prepare.get(ind).getTreasure());
		}
		setEscapeCard(prepare.get(ind));
	}
	
	/**
	 * Fonction retournant le plateau du jeu
	 * @return ArrayList<ArrayList<Card>> : Liste de liste de cartes
	 */
	public ArrayList<ArrayList<Card>> getBoard() {
		return this.board;
	}
	
	/**
	 * permet de changer de plateau
	 * @param board : le nouveau plateau
	 */
	public void setBoard(ArrayList<ArrayList<Card>> board) {
		this.board = board;
	}
	
	/**
	 * permet de changer la carte qui est hors du plateau
	 * @param c : la nouvelle carte sortie du plateau
	 */
	public void setEscapeCard(Card c) {
		this.escapeCard = c;
	}
	
	/**
	 *  permet de récupérer la carte qui est sortie du plateau
	 * @return Card : la carte sortie du plateau
	 */
	public Card getEscapeCard() {
		return this.escapeCard;
	}
	
	/**
	 * permet de faire bouger les trésors vivants suivant la variante en paramètre
	 * @param variant : la variante choisie (avec ou sans pénalités)
	 */
	public void moveLivingTreasures(int variant) {
		Treasure t;
		for (int i=0; i<NUMBER_ROWS_COLS; i++) {
			for (int j=0; j<NUMBER_ROWS_COLS; j++) {
				t = board.get(i).get(j).getTreasure();
				
				if (t!=null && Enum_Treasure.ARMOR.getLivingTreasuresNames().indexOf(t.getType()) != -1) {
					if (variant==1)
						((LivingTreasure) t).makeActionWithoutVariant(this);
					else if (variant==2)
						((LivingTreasure) t).makeActionWithVariant(this);
				}
			}
		}
	}
	
	/**
	 * récupère l'indice de l'insertion qui annule le coup du joueur précédent
	 * @return int : l'indice de 1 à 12 de l'insertion impossible dans le sens horaire
	 */
	public int getInsertImpossible() {
		if (escapeCard.getEscapeX() == 0 && escapeCard.getEscapeY() == 1)
			return 1;
		else if (escapeCard.getEscapeX() == 0 && escapeCard.getEscapeY() == 3)
			return 2;
		else if (escapeCard.getEscapeX() == 0 && escapeCard.getEscapeY() == 5)
			return 3;
		
		else if (escapeCard.getEscapeX() == 1 && escapeCard.getEscapeY() == 6)
			return 4;
		
		else if (escapeCard.getEscapeX() == 3 && escapeCard.getEscapeY() == 6)
			return 5;
		
		else if (escapeCard.getEscapeX() == 5 && escapeCard.getEscapeY() == 6)
			return 6;
		
		else if (escapeCard.getEscapeX() == 6 && escapeCard.getEscapeY() == 5)
			return 7;
		
		else if (escapeCard.getEscapeX() == 6 && escapeCard.getEscapeY() == 3)
			return 8;
		
		else if (escapeCard.getEscapeX() == 6 && escapeCard.getEscapeY() == 1)
			return 9;
		
		else if (escapeCard.getEscapeX() == 5 && escapeCard.getEscapeY() == 0)
			return 10;
		
		else if (escapeCard.getEscapeX() == 3 && escapeCard.getEscapeY() == 0)
			return 11;
		
		else if (escapeCard.getEscapeX() == 1 && escapeCard.getEscapeY() == 0)
			return 12;
		else
			return -1;
	}
	
	/**
	 * ajoute la carte à gauche en poussant la ligne d'indice row vers la droite
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int :  le numéro de la ligne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public boolean insertCardLeft(int row) {
		if(escapeCard.getEscapeX() == row && escapeCard.getEscapeY() == 0)
			return false;
		
		Card out = board.get(row).remove(board.get(row).size()-1);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		board.get(row).add(0, escapeCard);
		
		escapeCard.insert(row, 0);
		for(int i=1;i < 7;i++) {
			board.get(row).get(i).decal("droite");
		}
		
		this.escapeCard = out;
		this.escapeCard.goOut();
		return true;
	}
	
	/**
	 * ajoute la carte à droite en poussant la ligne d'indice row vers la gauche
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int : le numéro de la ligne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public boolean insertCardRight(int row) {
		if(escapeCard.getEscapeX() == row && escapeCard.getEscapeY() == board.get(row).size()-1)
			return false;
		
		Card out = board.get(row).remove(0);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		board.get(row).add(escapeCard);
		
		escapeCard.insert(row, 6);
		for(int i=0;i < 6;i++) {
			board.get(row).get(i).decal("gauche");
		}
		
		this.escapeCard = out;
		this.escapeCard.goOut();
		return true;
	}
	
	
	/**
	 * ajoute la carte en haut en poussant la colonne d'indice column vers le bas
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int : le numéro de la colonne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public boolean insertCardUp(int column) {
		if(escapeCard.getEscapeX() == 0 && escapeCard.getEscapeY() == column)
			return false;
		
		Card out = board.get(6).get(column);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		for(int i=5;i >= 0;i--) {
			board.get(i+1).remove(column);
			board.get(i+1).add(column,board.get(i).get(column));
			board.get(i+1).get(column).decal("bas");
		}
		board.get(0).remove(column);
		board.get(0).add(column,escapeCard);
		this.escapeCard.insert(0, column);
		
		this.escapeCard = out;
		this.escapeCard.goOut();
		return true;
	}
	
	/**
	 * ajoute la carte en bas en poussant la colonne d'indice column vers le haut
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int : le numéro de la colonne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public boolean insertCardDown(int column) {
		if(escapeCard.getEscapeX() == board.size()-1 && escapeCard.getEscapeY() == column)
			return false;
		
		Card out = board.get(0).get(column);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		for(int i=0;i <= 5;i++) {
			board.get(i).remove(column);
			board.get(i).add(column,board.get(i+1).get(column));
			board.get(i).get(column).decal("haut");
		}
		board.get(6).remove(column);
		board.get(6).add(column,escapeCard);
		this.escapeCard.insert(6, column);
		
		this.escapeCard = out;
		this.escapeCard.goOut();
		return true;
	}
	
	/**
	 * ajoute la carte à gauche en poussant la ligne d'indice row vers la droite sans
	 * vérifier que le coup n'annule pas le coup du dernier joueur
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int :  le numéro de la ligne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public void insertCardLeftWithoutCheck(int row) {
		Card out = board.get(row).remove(board.get(row).size()-1);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		board.get(row).add(0, escapeCard);
		
		escapeCard.insert(row, 0);
		for(int i=1;i < 7;i++) {
			board.get(row).get(i).decal("droite");
		}
		
		this.escapeCard = out;
		this.escapeCard.goOut();
	}
	
	/**
	 * ajoute la carte à droite en poussant la ligne d'indice row vers la gauche sans
	 * vérifier que le coup n'annule pas le coup du dernier joueur
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int :  le numéro de la ligne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public void insertCardRightWithoutCheck(int row) {
		Card out = board.get(row).remove(0);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		board.get(row).add(escapeCard);
		
		escapeCard.insert(row, 6);
		for(int i=0;i < 6;i++) {
			board.get(row).get(i).decal("gauche");
		}
		
		this.escapeCard = out;
		this.escapeCard.goOut();
	}
	
	
	/**
	 * ajoute la carte en haut en poussant la colonne d'indice row vers le bas sans
	 * vérifier que le coup n'annule pas le coup du dernier joueur
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int :  le numéro de la colonne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public void insertCardUpWithoutCheck(int column) {
		Card out = board.get(6).get(column);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		for(int i=5;i >= 0;i--) {
			board.get(i+1).remove(column);
			board.get(i+1).add(column,board.get(i).get(column));
			board.get(i+1).get(column).decal("bas");
		}
		board.get(0).remove(column);
		board.get(0).add(column,escapeCard);
		this.escapeCard.insert(0, column);
		
		this.escapeCard = out;
		this.escapeCard.goOut();
	}
	
	/**
	 * ajoute la carte en bas en poussant la colonne d'indice row vers le haut sans
	 * vérifier que le coup n'annule pas le coup du dernier joueur
	 * retiens la carte sortie dans l'attribut escapeCard
	 * @param int :  le numéro de la colonne d'insertion (1 3 ou 5)
	 * @return True si l'insertion a réussie, False sinon
	 */
	public void insertCardDownWithoutCheck(int column) {
		Card out = board.get(0).get(column);
		
		if (out.hasPlayer())
			out.givePlayersOnCase(escapeCard);
		
		for(int i=0;i <= 5;i++) {
			board.get(i).remove(column);
			board.get(i).add(column,board.get(i+1).get(column));
			board.get(i).get(column).decal("haut");
		}
		board.get(6).remove(column);
		board.get(6).add(column,escapeCard);
		this.escapeCard.insert(6, column);
		
		this.escapeCard = out;
		this.escapeCard.goOut();
	}
	
	/**
	 * imprime le plateau
	 */
	public void printBoard() {
		StringBuilder buffer = new StringBuilder();
		
		for (int i=0; i<this.board.size(); i++) {
			buffer.append("[");
			if (board.get(i).isEmpty()) buffer.append("                               ");
			
			for (int j=0; j<this.board.get(i).size(); j++) {
				if (board.get(i).get(j) == null) buffer.append(" ");
				else buffer.append(this.board.get(i).get(j).printType());
				if (j!=NUMBER_ROWS_COLS-1) buffer.append(" || ");		
			}
			buffer.append("]\n");	
		}
		
		buffer.append("\n");
		System.out.println(buffer.toString());
	}
	
	/**
	 * Récupère le trésor à partir de son nom
	 * @param treasureName : le nom du trésor recherché
	 * @return Treasure si trouvé, null sinon
	 */
	public Treasure getTreasure(String treasureName) {
		Treasure tres;
		for (int i=0; i<NUMBER_ROWS_COLS; i++) {
			for (int j=0; j<NUMBER_ROWS_COLS; j++) {
				tres = board.get(i).get(j).getTreasure();
				if (tres != null && tres.getName().equals(treasureName)) {
					return tres;
				}
			}
		}
		return null;
	}
	
	/**
	 * récupère la liste des trésors du jeu
	 * @return ArrayList<Treasure> la liste des trésors du jeu
	 */
	public ArrayList<Treasure> getTreasures(){
		return treasures;
	}
	
	/**
	 * indique si les coordonnées en paramètres correspondent à une case du plateau
	 * @param x : la position x testée
	 * @param y : la position y testée
	 * @return True si les coordonnées sont valide, False sinon
	 */
	public boolean isCoordValid(int x, int y) {
		return x >= 0 && x < this.board.size() && y >= 0 && y < this.board.get(0).size();
	}
	
	/**
	 * verifie si le trésor courant du joueur est le bon
	 * @param joueur : le joueur
	 * @return True si le trésor correspond, False sinon
	 */
	public boolean checkPlayerOnTreasure(Player joueur) {
		if(board.get(joueur.getPosX()).get(joueur.getPosY()).getTreasure() == null)
			return false;
		return board.get(joueur.getPosX()).get(joueur.getPosY()).getTreasure().equals(joueur.getHoldingTreasure());
	}
	
	/**
	 * récupère en x, y sans verifier que les coordonnées soit valides
	 * @param x : position x de la carte recherchée
	 * @param y : position y de la carte recherchée
	 * @return Card : la carte recherchée
	 */
	public Card getCard(int x,int y) {
		return board.get(x).get(y);
	}
	
	/**
	 * recherche les nouvelles positions du joueur après une insertion
	 * @param p : le joueur recherché
	 * @return Player : le joueur avec ses nouvelles positions
	 */
	public Player searchNewPlayerPosition(Player p) {
		Player p2;
		
		for (int i=0; i<NUMBER_ROWS_COLS; i++) {
			for (int j=0; j<NUMBER_ROWS_COLS; j++) {
				if ((p2 = board.get(i).get(j).playerInCase(p))!=null) {
					return p2;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * fait la bonne insertion de carte en fonction de l'indice entré en paramètre
	 * @param board : le plateau du jeu
	 * @param i : le numéro de la colonne/ligne de l'insertion
	 * @return : True si l'insertion a réussi, False sinon
	 */
    public boolean makeInsertion(int i){
    	switch (i) {
    	case 0:
    		return this.insertCardDown(1);
    	case 1:
    		return this.insertCardLeft(1);
    	case 2:
    		return this.insertCardRight(1); 		
    	case 3:
    		return this.insertCardUp(1);
    	case 4:
    		return this.insertCardDown(3);
    	case 5:
    		return this.insertCardLeft(3);
    	case 6:
    		return this.insertCardRight(3);
    	case 7:
    		return this.insertCardUp(3);
    	case 8:
    		return this.insertCardDown(5);
    	case 9:
    		return this.insertCardLeft(5);
    	case 10:
    		return this.insertCardRight(5);
    	case 11:
    		return this.insertCardUp(5);
    	default:
    		return false;
    	}
    	
    }
}
