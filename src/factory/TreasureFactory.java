/**
 * 
 */
package factory;

import treasure.Treasure;
import treasure.Enum_Treasure;
import treasure.LivingTreasure;
import player.Player;
import card.Card;

public class TreasureFactory implements AbstractFactory {
	@Override
	public Treasure newTreasure(Enum_Treasure type, int posX, int posY) {
		return new Treasure(type, posX, posY);
	}
	
	@Override
	public LivingTreasure newLivingTreasure(Enum_Treasure type, int posX, int posY) {
		return new LivingTreasure(type, posX, posY);
	}

	@Override
	public Player newPlayer(int x, int y, String color) {
		return null;
	}

	@Override
	public Player newPlayerAI(int x, int y, String color) {
		return null;
	}

	@Override
	public Card newCard(String type, int positionX, int positionY, Treasure tres) {
		return null;
	}
}
