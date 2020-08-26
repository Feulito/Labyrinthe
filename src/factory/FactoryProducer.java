package factory;

public class FactoryProducer {
	/**
	 * crée un nouveau FactoryProducer
	 */
	private FactoryProducer() {}
	
	/**
	 * renvoie un factory en fonction de son nom
	 * @param factory :  le nom du factory
	 * @return AbstractFactory : le factory
	 */
	public static AbstractFactory getFactory(String factory) {
		switch (factory) {
		case "player" :
			return new PlayerFactory();
		case "card" :
			return new CardFactory();
		default :
			return new TreasureFactory();
		}
	}

}
