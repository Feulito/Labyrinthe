/**
 * 
 */
package treasure;

public class Treasure implements TreasureInterface {

	private int posX;
	private int posY;
	private String name;
	private Enum_Treasure type;
	
	/**
	 * crée un nouveau trésor en position (0,0) avec un nom par défaut
	 */
	public Treasure() {
		this.posX = 0;
		this.posY = 0;
		this.name = "Default";
	}
	
	/**
	 * crée un nouveau trésor à partir de son type et de sa position (posX, posY)
	 * @param type : le type de trésor
	 * @param posX : la position x du trésor sur le plateau
	 * @param posY : la position y du trésor sur le plateau
	 */
	public Treasure(Enum_Treasure type, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		this.name = this.type.toString();
	}
	
	@Override
	public int getPosX() {
		return posX;
	}
	
	@Override
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	@Override
	public int getPosY() {
		return posY;
	}
	
	@Override
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	@Override
	public void setPos(int x, int y) {
		setPosX(x);
		setPosY(y);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Enum_Treasure getType() {
		return type;
	}

}
