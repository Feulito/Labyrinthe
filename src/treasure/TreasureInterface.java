/**
 * 
 */
package treasure;

public interface TreasureInterface {
	
	/**
	 * renvoie le nom du trésor
	 * @return String : le nom du trésor
	 */
	String getName();
	
	/**
	 * change les positions x et y du trésor
	 * @param x : la nouvelle position x
	 * @param y : la nouvelle position y
	 */
	void setPos(int x, int y);
	
	/**
	 * renvoie la position x du trésor
	 * @return int : la position x
	 */
	public int getPosX();

	/**
	 * change la position x du trésor
	 * @param posX : la nouvelle position x
	 */
	public void setPosX(int posX);
	
	/**
	 * renvoie la position y du trésor
	 * @return int : la position y
	 */
	public int getPosY();
	
	/**
	 * change la position y du trésor
	 * @param posY : la nouvelle position y
	 */
	public void setPosY(int posY);
	
	/**
	 * récupère le type du trésor
	 * @return Enum_Treasure : le type
	 */
	public Enum_Treasure getType();
}