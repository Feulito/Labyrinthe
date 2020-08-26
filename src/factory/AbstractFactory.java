/*
 * @author Duterte S�bastien
 * @version 1.0
 */

package factory;

import player.*;
import treasure.*;
import card.*;

public interface AbstractFactory {
	
	/**
	 * Fonction creant un nouveau joueur
	 * @param x : Coordoonee x dans le plateau representant sa position de depart
	 * @param y : Coordonnee y dans le plateau representant sa porisition de depart
	 * @param color : La couleur du joueur
	 * @return Player : Un nouveau joueur
	 */
	public Player newPlayer(int x, int y, String color);
	
	/**
	 * Fonction creant un nouveau joueur controle par l'ordinateur
	 * @param x : Coordoonee x dans le plateau representant sa position de depart
	 * @param y : Coordonnee y dans le plateau representant sa porisition de depart
	 * @param color : La couleur du joueur
	 * @return Player : Un nouveau joueur controle par l'ordinateur
	 */
	public Player newPlayerAI(int x, int y, String color);
	
	/**
	 * Fonction creant une nouvelle carte dans le plateau
	 * @param mobile : True si la carte est mobile, false si elle est fixe
	 * @param type : Type de carte (Ligne, angle, croisement)
	 * @param positionX : Position x dans le tableau de jeu
	 * @param positionY : Position y dans le tableau de jeu
	 * @param tres : Le tresor qu'elle contient
	 * @return Card : La nouvelle carte
	 */
	public Card newCard(String type, int positionX, int positionY, Treasure tres);
	
	/**
	 * Fonction creant un nouveau tresor
	 * @param type : Le type du tresor, correspondant a son nom
	 * @param posX : La position X du tresor sur le plateau de jeu
	 * @param posY : la position Y du tresor sur le plateau de jeu
	 * @return Treasure : Un nouveau tresor
	 */
	public Treasure newTreasure(Enum_Treasure type, int posX, int posY);
	
	/**
	 * Fonction creant un nouveau tresor vivant
	 * @param type : Le type du tresor, correspondant a son nom
	 * @param posX : La position X du tresor sur le plateau de jeu
	 * @param posY : la position Y du tresor sur le plateau de jeu
	 * @return Treasure : Un nouveau tresor vivant
	 */
	public LivingTreasure newLivingTreasure(Enum_Treasure type, int posX, int posY);
}
