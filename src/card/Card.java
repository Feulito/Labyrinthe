package card;
import treasure.Treasure;
import treasure.Enum_Treasure;
import java.util.ArrayList;
import player.Player;
import boardGame.BoardGame;

public class Card implements CardInterface{

	private String type;
	private int positionX;
	private int positionY;
	private Direction dir;
	private Treasure tres;
	private ArrayList<Player> players;
	
	private int escapex=-1;
	private int escapey=-1;
	
	/**
	 * Constructeur de Card
	 * @param type Permet de savoir le type de la carte(angle,ligne ou croisement)
	 * @param positionX Position x du joueur
	 * @param positionY Position y du joueur
	 * @param tres Trésor associé à la Card
	 * 
	 * @return Nouvelle Card
	 */
	public Card(String type, int positionX, int positionY, Treasure tres) {
		this.type = type;
		this.positionX = positionX;
		this.positionY = positionY;
		this.dir = new Direction(this.type);
		this.tres = tres;
		this.players = new ArrayList<Player>();
	}
	
	@Override
	public void setPos(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		if(this.tres != null)
		  this.tres.setPos(x, y);
		changeCoordsPlayersOnCase();
	}

	@Override
	public void rotate(int nb) {
		for(int i =0;i < (nb%4);i++)
			this.dir.rotate();
	}

	@Override
	public boolean insert(int x,int y) {
		if( (x == 0 && y%2 == 1) || (x%2 == 1 && y == 6) || (x%2 == 1 && y == 0) || (x == 6 && y%2 == 1)){
			setPos(x, y);
			return true;
		}
		return false;
	}
	
	@Override
	public void goOut() {
		escapex = this.positionX;
		escapey = this.positionY;
		this.positionX = -1;
		this.positionY = -1;
	}
	
	@Override
	public void setPosEscape(int x, int y) {
		escapex = x;
		escapey = y;
	}
	
	@Override
	public void decal(String dir) {
		if(dir.equals("haut")) {
			setPos(this.positionX-1, this.positionY);
		}
		else if(dir.equals("bas")) {
			setPos(this.positionX+1, this.positionY);
		}
		else if(dir.equals("gauche")) {
			setPos(this.positionX, this.positionY-1);
		}
		else if(dir.equals("droite")) {
			setPos(this.positionX, this.positionY+1);
		}
	}

	@Override
	public Treasure getTreasure() {
		return this.tres;

	}

	@Override
	public Direction getDir() {
		return this.dir;
	}

	@Override
	public void setTreasure(Treasure tres) {
		this.tres = tres;
		this.tres.setPos(this.positionX, this.positionY);
	}

	@Override
	public void setTreasure(Enum_Treasure type) {
		this.tres = new Treasure(type,this.positionX,this.positionY);
	}
	
	@Override
	public void unsetTreasure() {
		this.tres = null;
	}

	@Override
	public boolean noTreasure() {
		return this.tres == null;
	}

	@Override
	public String printType() {
		if(this.type.equals("ligne"))
			return "1";
		else if(this.type.equals("angle"))
			return "2";
		else if(this.type.equals("croisement"))
			return "3";
		return "0";
	}

	@Override
	public int getEscapeX() {
		return escapex;
	}

	@Override
	public int getEscapeY() {
		return escapey;
	}
	
	@Override
	public void addPlayerOnCase(Player p) {
		if (this.players.indexOf(p) == -1)
			this.players.add(p);
	}

	@Override
	public void removePlayerOnCase(Player p) {
		this.players.remove(this.players.indexOf(p));
	}

	@Override
	public void receivePlayersOnCase(Card left) {
		this.players.addAll(left.getPlayers());
	}

	@Override
	public void givePlayersOnCase(Card enter) {
		enter.receivePlayersOnCase(this);
		this.players = new ArrayList<Player>();
	}
	
	@Override
	public void changeCoordsPlayersOnCase() {
		for (Player p : this.players) {
			p.setPos(this.positionX, this.positionY);
		}
	}
	
	@Override
	public Player getPlayer(Player p) {
		return this.players.get(this.players.indexOf(p));
	}
	
	@Override
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	@Override
	public Player playerInCase(Player p) {
		for (Player p2 : this.players) {
			if (p2.getStartX() == p.getStartX() && p2.getStartY() == p.getStartY())
				return p2;
		}
		return null;	
	}
	
	@Override
	public boolean hasPlayer() {
		return !this.players.isEmpty();
	}
	
	@Override
	public void checkPlayerOnCorrectCase(BoardGame board) {
		for (Player p : this.players) {
			if (p.getPosX()!=this.positionX || p.getPosY()!=this.positionY) {
				board.getCard(p.getPosX(), p.getPosY()).addPlayerOnCase(p);
				removePlayerOnCase(p);
			}
		}
	}
	
	@Override
	public void resetEscape() {
		this.escapex = -1;
		this.escapey = -1;
	}
	
}
