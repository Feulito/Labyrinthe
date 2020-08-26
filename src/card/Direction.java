package card;

public class Direction implements DirectionInterface {
	
	private boolean nord;
	private boolean est;
	private boolean sud;
	private boolean ouest;
	private int angle;

	/**
	 * Constructeur de direction
	 * @param type Type de la carte pour adpater la direction
	 * @return Direction créée
	 */
	public Direction(String type) {
		this.angle = 90;
		if(type.equals("ligne")) {
			this.ouest = true;
			this.est = true;
		}
		else if(type.equals("angle")) {
			this.est = true;
			this.sud = true;
		}
		else if(type.equals("croisement")) {
			this.ouest = true;
			this.est = true;
			this.sud = true;
		}
	}

	@Override
	public void rotate() {
		boolean tmp = this.nord;
		this.nord = this.ouest;
		this.ouest = this.sud;
		this.sud = this.est;
		this.est = tmp;
		this.angle = (this.angle + 90)% 360;

	}

	@Override
	public boolean getNorth() {
		return this.nord;
	}

	@Override
	public boolean getSouth() {
		return this.sud;
	}

	@Override
	public boolean getWest() {
		return this.ouest;
	}

	@Override
	public boolean getEast() {
		return this.est;
	}

	@Override
	public int getDegre() {
		return this.angle;
	}

}
