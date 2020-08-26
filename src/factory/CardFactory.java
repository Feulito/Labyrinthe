/**
 * 
 */
package factory;

import card.Card;
import treasure.Enum_Treasure;
import treasure.LivingTreasure;
import treasure.Treasure;
import player.Player;

public class CardFactory implements AbstractFactory{
	@Override
	public Card newCard(String type, int positionX, int positionY, Treasure tres) {
		return new Card(type, positionX, positionY, tres);
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
	public Treasure newTreasure(Enum_Treasure type, int posX, int posY) {
		return null;
	}

	@Override
	public LivingTreasure newLivingTreasure(Enum_Treasure type, int posX, int posY) {
		return null;
	}

}
