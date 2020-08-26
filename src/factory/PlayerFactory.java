package factory;

import card.Card;
import treasure.Treasure;
import treasure.Enum_Treasure;
import treasure.LivingTreasure;
import player.Player;
import player.PlayerAI;

public class PlayerFactory implements AbstractFactory {
	
	@Override
	public Player newPlayer(int x, int y, String color) {
		return new Player(x, y, color);
	}
	
	@Override
	public Player newPlayerAI(int x, int y, String color) {
		return new PlayerAI(x, y, color);
	}

	@Override
	public Card newCard(String type, int positionX, int positionY, Treasure tres) {
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
