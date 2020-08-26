package card;

public interface DirectionInterface {
	
	/**
	 * Permet de tourner la carte
	 */
	public void rotate();
	
	/**
	 * Permet de recuperer la direction nord de la direction
	 * @return boolean de la direction demandé
	 */
	public boolean getNorth();
	
	/**
	 * Permet de recuperer la direction sud de la direction
	 * @return boolean de la direction demandé
	 */
	public boolean getSouth();
	
	/**
	 * Permet de recuperer la direction ouest de la direction
	 * @return boolean de la direction demandé
	 */
	public boolean getWest();
	
	/**
	 * Permet de recuperer la direction est de la direction
	 * @return boolean de la direction demandé
	 */
	public boolean getEast();
	
	/**
	 * Permet de recuperer le degré de rotation de la direction
	 * @return degré de rotation
	 */
	public int getDegre();
	

}
