package player;
import boardGame.BoardGame;
import card.Card;
import treasure.Treasure;
import java.util.Random;

import java.util.ArrayList;

public class PlayerAI extends Player{
	private Random rand = new Random();
	
    /**
     * Constructeur d'un PlayerAI avec sa position et sa couleur
     * @param posX : la position x du joueur sur le plateau
     * @param posY : la position y du joueur sur le plateau
     * @param color : la couleur du pion du joueur
     */
	public PlayerAI(int posX, int posY, String color){
        super(posX, posY, color);
    }
    
	/**
	 * Constructeur par défaut d'un joueur en position (0,0)
	 * */
    public PlayerAI() {
    	super();
    }
    
    /**
     * trouve que le joueur peut atteindre depuis sa position (i, j) 
     * @param board : le plateau du jeu
     * @param visited : le tableau indiquant quels positions il peut atteindre
     * @param i : la position x de départ sur le plateau
     * @param j : la position y de départ sur le plateau
     */
	private void getAttainablePointsRec(BoardGame board, boolean[][] visited,  int i, int j){
    	if (visited[i][j]) return; 
    	visited[i][j] = true;
    	
    	if (canGoEast(board, i, j) && (!visited[i][j+1])) {
				getAttainablePointsRec(board, visited, i, j+1);
    	}
    	if (canGoWest(board, i, j) && (!visited[i][j-1])) {
    			getAttainablePointsRec(board, visited, i, j-1);
    	}
    	if (canGoSouth(board, i, j) && (!visited[i+1][j])) {
    			getAttainablePointsRec(board, visited, i+1, j);
    	}
    	if (canGoNorth(board, i, j) && (!visited[i-1][j])) {
    			getAttainablePointsRec(board, visited, i-1, j);
    	}
    }
 
	/**
	 * Récupère la liste de toutes les positions attaignables du joueur depuis sa position actuelle
	 * @param board : le plateau du jeu
	 * @return : la liste de toutes les positions attaignables du joueur sous forme d'une ArrayList de tableaux de 2 entiers
	 */
	public ArrayList<int[]> getAttainablePoints(BoardGame board){
		ArrayList<int[]> points = new ArrayList<int[]>();
		int tab[];
	 	boolean visited[][] = new boolean[board.getBoard().size()][board.getBoard().get(0).size()];
		
		for (int i = 0; i < board.getBoard().size(); i++) {
			for (int j = 0; j < board.getBoard().get(0).size(); j++) {
				visited[i][j] = false;
			}
		}
		
		getAttainablePointsRec(board, visited,this.getPosX(),this.getPosY());
		for (int i=0; i<visited.length; i++) {
			for (int j=0; j<visited[i].length; j++) {
				if (visited[i][j]) {
					tab = new int[2];
					tab[0] = i;
					tab[1] = j;
					points.add(tab);
				}
			}
		}
		return points;
	}
	
	/**
	 * effectue la stratégie complexe de l'ordinateur, l'ordinateur se déplace au point
	 *  le plus proche du trésor ou du point de départ s'il a trouvé tous les trésors
	 * (il prend donc en compte la meilleure orientation possible de la carte,
	 *  la meilleure insertion possible dans le plateau et le meilleur déplacement possible)
	 * @param board : le plateau du jeu
	 */
    public void makeProgrammedStrategy(BoardGame board){
    	double[][] points = new double[48][3];
    	double[] min;
    	int indexMin;
		int[] tab = {1,3,5};
		int orient = 0; 
		int r;
		int x; 
		int y;
		Card escape;
		Treasure t;
	
		for (int i=0; i<48; i+=16) { //insert de toutes les façons possibles
			for (r=0; r<4; r++) {
				escape = board.getEscapeCard();
				escape.rotate(orient);
				x = escape.getEscapeX();
				y = escape.getEscapeY();
				orient++;
				
				if (board.insertCardDown(tab[(i+r)%3])){
					t = super.getHoldingTreasure();
					if (t!=null)
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), t.getPosX(), t.getPosY());
					else
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), super.getStartX(), super.getStartY());
					board.insertCardUpWithoutCheck(tab[(i+r)%3]);
					board.getEscapeCard().setPosEscape(x, y);
				}else {
					points[i+r] = null;
				}
			}
			for (r=4; r<8; r++) {
				escape = board.getEscapeCard();
				escape.rotate(orient);
				x = escape.getEscapeX();
				y = escape.getEscapeY();
				orient++;
				if (board.insertCardLeft(tab[(i+r)%3])){
					t = super.getHoldingTreasure();
					if (t!=null)
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), t.getPosX(), t.getPosY());
					else
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), super.getStartX(), super.getStartY());
					board.insertCardRightWithoutCheck(tab[(i+r)%3]);
					board.getEscapeCard().setPosEscape(x, y);
				}else {
					points[i+r] = null;
				}
			}
			for (r=8; r<12; r++) {
				escape = board.getEscapeCard();
				escape.rotate(orient);
				x = escape.getEscapeX();
				y = escape.getEscapeY();
				orient++;
				if (board.insertCardRight(tab[(i+r)%3])){
					t = super.getHoldingTreasure();
					if (t!=null)
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), t.getPosX(), t.getPosY());
					else
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), super.getStartX(), super.getStartY());
					board.insertCardLeftWithoutCheck(tab[(i+r)%3]);
					board.getEscapeCard().setPosEscape(x, y);
				}else {
					points[i+r] = null;
				}
			}
			for (r=12; r<16; r++) {
				escape = board.getEscapeCard();
				escape.rotate(orient);
				x = escape.getEscapeX();
				y = escape.getEscapeY();
				orient++;
				if (board.insertCardUp(tab[(i+r)%3])){
					t = super.getHoldingTreasure();
					if (t!=null)
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), t.getPosX(), t.getPosY());
					else
						points[i+r] = getClosestPointToGoalWithDistance(getAttainablePoints(board), super.getStartX(), super.getStartY());
					board.insertCardDownWithoutCheck(tab[(i+r)%3]);
					board.getEscapeCard().setPosEscape(x, y);
				}else {
					points[i+r] = null;
				}
			}
		}
		
		if (points[0] != null) {
			min = points[0];
			indexMin = 0;
		}
		else {
			min = points[4];
			indexMin = 4;
		}
		for (int i=0; i<48; i++) {
			if ((points[i] != null) && (points[i][2] < min[2])) {
				min = points[i];
				indexMin = i;
			}
		}
		
		board.getEscapeCard().rotate(indexMin);
		if ((indexMin%16) < 4) {
			board.insertCardDown(tab[indexMin%3]);
		}else if ((indexMin%16) < 8) {
			board.insertCardLeft(tab[indexMin%3]);
		}else if ((indexMin%16) < 12) {
			board.insertCardRight(tab[indexMin%3]);
		}else {
			board.insertCardUp(tab[indexMin%3]);
		}
		super.moveWithoutCheck(board, (int) min[0], (int) min[1]);		
    }  
    
    /**
     * calcule la distance géométrique entre le point de coordonnées (xEnd, yEnd)
     *  et le point de coordonnées (point[0], point[1]) 
     * @param xEnd : la position x dans le plateau du point visé
     * @param yEnd : la position y dans le plateau du point visé
     * @param point : le point de coordonnées (point[0], point[1])
     * @return la distance géométrique en Double
     */
    private Double getDistanceBetweenPointAndGoal(int xEnd, int yEnd, int[] point) {
    	return Math.sqrt(Math.pow(point[0] - xEnd, 2) + Math.pow(point[1]-yEnd, 2));
    }
  
    /**
     * récupère le point de la liste de points ayant la plus faible distance géométrique avec le 
     * point visé (xEnd, yEnd)
     * @param points : la liste de points
     * @param xEnd : la position x dans le plateau du point visé
     * @param yEnd : la position y dans le plateau du point visé
     * @return un tableau de 2 entiers représentant les coordonnées du point, null si les positions
     * du but sont à -1
     */
    private int[] getClosestPointToGoal(ArrayList<int[]> points, int xEnd, int yEnd) {
    	if (xEnd==-1 && yEnd==-1) return null;
    	Double min = getDistanceBetweenPointAndGoal(xEnd, yEnd, points.get(0));
    	Double current;
    	int indexMin = 0;
    	for (int i=1; i<points.size(); i++) {
    		current = getDistanceBetweenPointAndGoal(xEnd,yEnd, points.get(i));
    		if (current < min) {
    			min = current;
    			indexMin = i;
    		}
    	}
    	return points.get(indexMin);
    }
    
    /** 
     * récupère les coordonnées point de la liste le plus proche du point visé (xEnd, yEnd)
     * ainsi que la distance géométrique de ce point avec le point visé  
     * @param points : la liste de points
     * @param xEnd : la position x dans le plateau du point visé
     * @param yEnd : la position y dans le plateau du point visé
     * @return un tableau de 3 double représentant les 2 coordonnées du point converties en 
     * double et la distance géométrique
     */
    private double[] getClosestPointToGoalWithDistance(ArrayList<int[]> points, int xEnd, int yEnd) {
    	if (xEnd == -1 && yEnd == -1)
    		return null;
    	double[] tab = new double[3];
    	double min = getDistanceBetweenPointAndGoal(xEnd, yEnd, points.get(0));
    	double current;
    	int indexMin = 0;
    	for (int i=1; i<points.size(); i++) {
    		current = getDistanceBetweenPointAndGoal(xEnd, yEnd, points.get(i));
    		if (current < min) {
    			min = current;
    			indexMin = i;
    		}
    	}
    	tab[0] = (double)points.get(indexMin)[0];
    	tab[1] = (double)points.get(indexMin)[1];
    	tab[2] = min;
    	return tab;
    }
    
    /**
     * effectue la stratégie de l'ordinateur facile, en insérant de manière aléatoire la
     * carte avec une orientation aléatoire et en se rapprochant le plus proche possible du
     * trésor ou du point de départ s'il a trouvé tous les trésors
     * @param board : le plateau du jeu
     */
    public void makePartialProgrammedStrategy(BoardGame board) {
    	ArrayList<int[]> points; 
    	int[] min;
    	Treasure t;
    	int i = rand.nextInt(12);
    	board.getEscapeCard().rotate(rand.nextInt(4));
    	
    	if (!board.makeInsertion(i)) {
    		i++; i%=12; board.makeInsertion(i);
    	}
    	t = this.getHoldingTreasure();
    	points = getAttainablePoints(board);
    	
    	if (t!=null) {
    		min = getClosestPointToGoal(points, t.getPosX(), t.getPosY());
    	}
    	else
    		min = getClosestPointToGoal(points, super.getStartX(), super.getStartY());
    	if (min!=null)
    		super.moveWithoutCheck(board, min[0], min[1]);
    }    
}
