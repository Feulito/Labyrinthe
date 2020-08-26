package treasure;

import java.util.ArrayList;

public enum Enum_Treasure {
	GOLDENRING("un anneau d�or"),
	SPIDER("une araignee"),
	ARMOR("une armure"),
	TREASUREMAP("une carte au tresor"),
	CANDLEHOLDER("un chandelier"),
	BAT("une chauve souris"),
	OWL("une chouette"),
	KEYS("des clefs"),
	TREASURECHEST("un coffre au tresor"),
	CROWN("une couronne"),
	SKULL("un crane"),
	DINOSAUR("un dinosaure"),
	EMERALD("une emeraude"),
	SWORD("une epee"),
	GHOST("un fantome"),
	PIXIE("une fee"),
	GENIUS("un genie"),
	GRIMOIRE("un grimoire"),
	RAT("un rat"),
	COINBAG("un sac de pieces"),
	SALAMANDER("une salamandre"),
	BEETLE("un scarabee"),
	DEATHSHEADSPHYNX("un sphynx a tete de mort"),
	TROLL("un troll");
	
	private String name;
	
	/**
	 * cree une énumaration à partir de son nom
	 * @param name : le nom
	 */
	Enum_Treasure(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * renvoie une liste de toutes les énumérations correspondantes à un trésor vivant du jeu
	 * @return ArrayList<Enum_type> la liste des trésors vivants
	 */
	public ArrayList<Enum_Treasure> getLivingTreasuresNames(){
		ArrayList<Enum_Treasure> treasures = new ArrayList<Enum_Treasure>();
		treasures.add(SPIDER);
		treasures.add(BAT);
		treasures.add(OWL);
		treasures.add(DINOSAUR);
		treasures.add(GHOST);
		treasures.add(PIXIE);
		treasures.add(GENIUS);
		treasures.add(RAT);
		treasures.add(SALAMANDER);
		treasures.add(BEETLE);
		treasures.add(DEATHSHEADSPHYNX);
		treasures.add(TROLL);
		return treasures;
	}
}
