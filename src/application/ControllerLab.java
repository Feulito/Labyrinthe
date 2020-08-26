package application;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import boardGame.BoardGame;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import javafx.stage.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;
import game.*;
import card.*;
import treasure.*;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class ControllerLab implements Initializable {
	
	private Game game;
	
	public GridPane gridCards;
	public Button button10;
	public Button button30;
	public Button button50;
	public Button button16;
	public Button button36;
	public Button button56;
	public Button button61;
	public Button button63;
	public Button button65;
	public Button button01;
	public Button button03;
	public Button button05;
	public Button buttonNewGame;
	public Button buttonRotateLeft;
	public Button buttonRotateRight;
	public Button buttonQuit;
	public Button buttonFinDeTour;
	public Button haut;
	public Button bas;
	public Button gauche;
	public Button droite;
	public ImageView imgReserve;
	public ImageView imgTresor;
	public ImageView imgJoueur;
	public Text labJoueur;
	public Text labNbTresor;
	public Text gagnant;
	
	private boolean insertion;
	private Button desactiver;
	private String prefixImg;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		button10.setDisable(true);
		button30.setDisable(true);
		button50.setDisable(true);
		button16.setDisable(true);
		button36.setDisable(true);
		button56.setDisable(true);
		button61.setDisable(true);
		button63.setDisable(true);
		button65.setDisable(true);
		button01.setDisable(true);
		button03.setDisable(true);
		button05.setDisable(true);
		buttonRotateLeft.setDisable(true);
		buttonRotateRight.setDisable(true);
		buttonFinDeTour.setDisable(true);
		haut.setDisable(true);
		bas.setDisable(true);
		gauche.setDisable(true);
		droite.setDisable(true);
		insertion = false;
	}
	
	/**
	 * Permet de bloquer l'insertion
	 */
	public void bloquerInsertion() {
		button10.setDisable(true);
		button30.setDisable(true);
		button50.setDisable(true);
		button16.setDisable(true);
		button36.setDisable(true);
		button56.setDisable(true);
		button61.setDisable(true);
		button63.setDisable(true);
		button65.setDisable(true);
		button01.setDisable(true);
		button03.setDisable(true);
		button05.setDisable(true);
		buttonRotateLeft.setDisable(true);
		buttonRotateRight.setDisable(true);
		buttonFinDeTour.setDisable(false);
		insertion = true;
	}
	
	/**
	 * Gere les boutons pour un nouveau tour de jeu
	 */
	public void buttonNewTurn() {
		button10.setDisable(false);
		button30.setDisable(false);
		button50.setDisable(false);
		button16.setDisable(false);
		button36.setDisable(false);
		button56.setDisable(false);
		button61.setDisable(false);
		button63.setDisable(false);
		button65.setDisable(false);
		button01.setDisable(false);
		button03.setDisable(false);
		button05.setDisable(false);
		buttonRotateLeft.setDisable(false);
		buttonRotateRight.setDisable(false);
		haut.setDisable(true);
		bas.setDisable(true);
		gauche.setDisable(true);
		droite.setDisable(true);
		buttonFinDeTour.setDisable(true);
		insertion = false;
		updateCross();
		updateCurrPlayer();
		iabutton();
		if(desactiver != null) {
			desactiver.setDisable(true);		
		}

	}
	
	/**
	 * Permet de desactiver le bouton contraire a la où l ia a joué
	 */
	public void iabutton() {
		int tmp = this.game.getBoard().getInsertImpossible();
		switch (tmp) {
		case 1:
			this.desactiver = button10;
			break;
		case 2:
			this.desactiver = button30;
			break;
		case 3:
			this.desactiver = button50;
			break;
		case 4:
			this.desactiver = button16;
			break;
		case 5:
			this.desactiver = button36;
			break;
		case 6:
			this.desactiver = button56;
			break;
		case 7:
			this.desactiver = button65;
			break;
		case 8:
			this.desactiver = button63;
			break;
		case 9:
			this.desactiver = button61;
			break;
		case 10:
			this.desactiver = button05;
			break;
		case 11:
			this.desactiver = button03;
			break;
		case 12:
			this.desactiver = button01;
			break;
		default :
			this.desactiver = null;
			break;
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 10
	 */
	public void handleButton10() {
		if(!insertion && this.game.getBoard().insertCardUp(1)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
		
	}

	/**
	 * Action quand l'on presse sur le bouton 30
	 */
	public void handleButton30() {
		if(!insertion && this.game.getBoard().insertCardUp(3)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 50
	 */
	public void handleButton50() {
		if(!insertion && this.game.getBoard().insertCardUp(5)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 16
	 */
	public void handleButton16() {
		if(!insertion && this.game.getBoard().insertCardRight(1)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 36
	 */
	public void handleButton36() {
		if(!insertion && this.game.getBoard().insertCardRight(3)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 56
	 */
	public void handleButton56() {
		if(!insertion && this.game.getBoard().insertCardRight(5)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 61
	 */
	public void handleButton61() {
		if(!insertion && this.game.getBoard().insertCardDown(1)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 63
	 */
	public void handleButton63() {
		if(!insertion && this.game.getBoard().insertCardDown(3)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 65
	 */
	public void handleButton65() {
		if(!insertion && this.game.getBoard().insertCardDown(5)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 01
	 */
	public void handleButton01() {
		if(!insertion && this.game.getBoard().insertCardLeft(1)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 03
	 */
	public void handleButton03() {
		if(!insertion && this.game.getBoard().insertCardLeft(3)) {
			insertion = true;
			updateView();
			bloquerInsertion();
		}
	}
	
	/**
	 * Action quand l'on presse sur le bouton 05
	 */
	public void handleButton05() {
		if(!insertion && this.game.getBoard().insertCardLeft(5)) {
			insertion = true;
			updateView();
			bloquerInsertion();	
		}
	}
	
	/**
	 * Permet de créer une nouvelle partie (creer la nouvelle fenetre d option
	 */
	public void handleNewGame() {

        try {
        	
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/fxml/nouveaujeu.fxml"));
    		Parent root = loader.load();	
    		ControllerNewGame ctl = loader.getController();
    		ctl.setParentController(this);
    		
    		
            Scene mascene = new Scene(root, 800, 300);

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Option du jeu");
            popup.setScene(mascene);

            popup.show();        	
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
        
        
	}
	
	/**
	 * Permet de creer le jeu avec les options voulu
	 * @param nbia Nombre d ia dans jeu
	 * @param difficulte difficulte du jeu
	 * @param variante variante du jeu
	 * @param theme theme du jeu
	 */
	public void initGame(int nbia,int difficulte,int variante, int theme) {
		if(theme == 0) {
			this.prefixImg = "/images/";
		}
		else {
			this.prefixImg = "/images/Ocean";
		}

		desactiver = null;
		this.game = new GameBuilder().preparePlateauMulti(4-nbia, difficulte, variante);
		this.game.firstTurn();
		buttonNewTurn();
		gagnant.setText("");
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton de rotation gauche
	 */
	public void handleButtonRotateLeft() {
		this.game.getBoard().getEscapeCard().rotate(3);
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton de rotation droite
	 */
	public void handleButtonRotateRight() {
		this.game.getBoard().getEscapeCard().rotate(1);
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton quitter
	 */
	public void handleButtonQuit() {
	    Stage stage = (Stage) buttonQuit.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Action quand l'on presse sur le bouton haut
	 */
	public void handleHaut() {
		game.getCurrentPlayer().moveNorth(game.getBoard());
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton bas
	 */
	public void handleBas() {
		game.getCurrentPlayer().moveSouth(game.getBoard());
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton gauche
	 */
	public void handleGauche() {
		game.getCurrentPlayer().moveWest(game.getBoard());
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton droite
	 */
	public void handleDroite() {
		game.getCurrentPlayer().moveEast(game.getBoard());
		updateView();
	}
	
	/**
	 * Action quand l'on presse sur le bouton fin de tour
	 */
	public void handleFinDeTour() {
		this.game.endTurn();
		buttonNewTurn();
		updateView();
	}
	
	/**
	 * met a jour la vue en fonction du modèle
	 */
	private void updateView() {
		if(insertion && this.game.getCurrentPlayer().isOnHoldingTreasure()) {
			this.game.getCurrentPlayer().pickTreasure(this.game.getBoard().getCard(this.game.getCurrentPlayer().getPosX(), this.game.getCurrentPlayer().getPosY()));
		}
		if(this.game.getCurrentPlayer().getPick()) {
			this.game.endTurn();
			buttonNewTurn();
		}
		int column = gridCards.getColumnCount();
		int row = gridCards.getRowCount();
		String url;
		ImageView currView;
		Card currCard;
		Treasure currTreasure;
		ArrayList<ArrayList<Card>> cards = game.getBoard().getBoard();
		for(int i = 0; i < column; i++) {
			for(int j = 0; j < row;j++) {
				currView = (ImageView) gridCards.getChildren().get(j * row + i);
				currCard = cards.get(i).get(j);
				currTreasure = currCard.getTreasure();
				url = generateImgURL(currCard,currTreasure);
				currView.setImage(new Image(getClass().getResourceAsStream(url)));
				currView.setRotate(currCard.getDir().getDegre());
			}
		}

		// img reserve
		currView = this.imgReserve;
		currCard = game.getBoard().getEscapeCard();
		currTreasure = currCard.getTreasure();
		url = generateImgURL(currCard,currTreasure);
		currView.setImage(new Image(getClass().getResourceAsStream(url)));
		currView.setRotate(currCard.getDir().getDegre());
		// img tresor
		currView = this.imgTresor;
		currTreasure = game.getCurrentPlayer().getHoldingTreasure();

		url = "/images/Carte"+treasureToString(currTreasure)+".png";


		currView.setImage(new Image(getClass().getResourceAsStream(url)));

		// image joueur
		for(Player p : game.getPlayers()) {
			currView = (ImageView) gridCards.getChildren().get(p.getPosY() * row + p.getPosX());
			currCard = game.getBoard().getCard(p.getPosX(), p.getPosY());
			url = this.prefixImg + playerToString(p) + cardToString(currCard) + ".png";
			currView.setImage(new Image(getClass().getResourceAsStream(url)));
			currView.setRotate(currCard.getDir().getDegre());
		}
		updateCross();
		
		this.iabutton();
		if(desactiver != null)
			desactiver.setDisable(true);


		
		labNbTresor.setText(""+game.getCurrentPlayer().getTreasure().size());
		
		if(game.isWinner(game.getCurrentPlayer())) {
			this.gagnant.setText("Le joueur "+this.game.getCurrentPlayer().getColor()+" a gagné !!!");
			this.initialize(null, null);
		}
	}
	
	/**
	 * génère l'URL de l'image de la carte du plateau
	 * @param c carte a convertir
	 * @param t trésor sur la carte (posiblement inexsitant)
	 * @return URL de l'image
	 */
	private String generateImgURL(Card c, Treasure t) {
		// nom Tresor + I , T, L ou CheminAngle ou CheminDroit ou CheminEnT
		if(t != null)
			return this.prefixImg+treasureToString(t,c)+".png";
		else
			return this.prefixImg+cardToLongString(c);
	}
	
	/**
	 * génère un nom de trésor pour les carte avec trésor
	 * @param c carte du modèle
	 * @param t trésor sur la carte (posiblement inexsitant)
	 * @return nom du trésor conforme au nom donné pour les images de carte
	 */
	private String treasureToString(Treasure t, Card c) {
		switch(t.getType()) {
		case GOLDENRING: return "Anneau"; //
		case SPIDER: return "Araignee" + cardToString(c);
		case ARMOR: return "Armure"; //
		case TREASUREMAP: return "Carte"; //
		case CANDLEHOLDER: return "Chandelier"; //
		case BAT: return "ChauveSourie" + cardToString(c);
		case OWL: return "Chouette" + cardToString(c);
		case KEYS: return "Clef"; //
		case TREASURECHEST: return "Tresor"; //
		case CROWN: return "Couronne"; //
		case SKULL: return "Crane"; //
		case DINOSAUR: return "Dino" + cardToString(c);
		case EMERALD: return "Emeraude"; //
		case SWORD: return "Epee"; //
		case GHOST: return "Fantome" + cardToString(c);
		case PIXIE: return "Fee" + cardToString(c);
		case GENIUS: return "Genie" + cardToString(c);
		case GRIMOIRE: return "Grimoire"; //
		case RAT: return "Rat" + cardToString(c);
		case COINBAG: return "SacOr"; //
		case SALAMANDER: return "Salamandre" + cardToString(c);
		case BEETLE: return "Scarabee" + cardToString(c);
		case DEATHSHEADSPHYNX: return "Sphinx" + cardToString(c);
		case TROLL: return "Troll" + cardToString(c);
		}
		return null;
	}
	/**
	 * génère un nom de trésor conforme au nom des images de trésor
	 * @param t trésor à convertir
	 * @return nom converti
	 */
	private String treasureToString(Treasure t) {
		if(t == null)
			return "Vierge";
		switch(t.getType()) {
		case GOLDENRING: return "Anneau"; //
		case SPIDER: return "Araignee";
		case ARMOR: return "Armure"; //
		case TREASUREMAP: return "Carte"; //
		case CANDLEHOLDER: return "Chandelier"; //
		case BAT: return "ChauveSourie";
		case OWL: return "Chouette";
		case KEYS: return "Clef"; //
		case TREASURECHEST: return "Tresor"; //
		case CROWN: return "Couronne"; //
		case SKULL: return "Crane"; //
		case DINOSAUR: return "Dino";
		case EMERALD: return "Emeraude"; //
		case SWORD: return "Epee"; //
		case GHOST: return "Fantome";
		case PIXIE: return "Fee";
		case GENIUS: return "Genie";
		case GRIMOIRE: return "Grimoire"; //
		case RAT: return "Rat";
		case COINBAG: return "SacOr"; //
		case SALAMANDER: return "Salamandre";
		case BEETLE: return "Scarabee";
		case DEATHSHEADSPHYNX: return "Sphinx";
		case TROLL: return "Troll";
		}
		return null;
	}
	/**
	 * génère un nom pour les carte sans trésor
	 * @param c carte aconvertir
	 * @return nom converti
	 */
	private String cardToLongString(Card c) {
		if(c.printType().equals("1")) {
			return "CheminDroit.png";
		}
		else if(c.printType().equals("2")) {
			return "CheminAngle.png";
		}
		else if(c.printType().equals("3")) {
			return "CheminEnT.png";
		}
		return null;
	}
	/**
	 * génère une chaine en fonction de l'orentation de la carte
	 * @param c carte a convertir
	 * @return nom converti
	 */
	private String cardToString(Card c) {
		if(c.printType().equals("1")) {
			return "I";
		}
		else if(c.printType().equals("2")) {
			return "L";
		}
		else if(c.printType().equals("3")) {
			return "T";
		}
		return null;
	}
	/**
	 * génère le nom du joueur en fonction de la convention de nomage des images de carte
	 * @param p joueur a convertir
	 * @return nom converti
	 */
	private String playerToString(Player p) {
		if(p.getColor().equals("rouge")) {
			return "Rouge";
		}
		if(p.getColor().equals("vert")) {
			return "Vert";
		}
		if(p.getColor().equals("bleu")) {
			return "Bleu";
		}
		if(p.getColor().equals("jaune")) {
			return "Jaune";
		}
		return null;
	}
	/**
	 * met ajour la croix de déplacement en fonction des déplacement disponible a joueur
	 */
	private void updateCross() {
		if(insertion) {
			Player p = game.getCurrentPlayer();
			int playerPosX = p.getPosX();
			int playerPosY = p.getPosY();
			BoardGame b = game.getBoard();

			boolean up = !p.canGoNorth(b,playerPosX, playerPosY);
			boolean down = !p.canGoSouth(b,playerPosX, playerPosY);
			boolean left = !p.canGoWest(b,playerPosX, playerPosY);
			boolean right = !p.canGoEast(b,playerPosX, playerPosY);
			
			gauche.setDisable(left);
			droite.setDisable(right);
			haut.setDisable(up);
			bas.setDisable(down);			
		}
	}
	/**
	 * met a jour l'image et le nom du joueur courant en fonction du modèle
	 */
	private void updateCurrPlayer() {
		ImageView currView;
		String url;
		labJoueur.setText(playerToString(game.getCurrentPlayer()));
		currView = this.imgJoueur;
		url = "/images/"+playerToImg(game.getCurrentPlayer())+".png";
		System.out.println(url);
		currView.setImage(new Image(getClass().getResourceAsStream(url)));
	}
	/**
	 * Génère le nom de la pastille de couleur selon le joueur
	 * @param p joueur a convertir
	 * @return nom de l'image converti
	 */
	private String playerToImg(Player p) {
		if(p.getColor().equals("rouge")) {
			return "jrouge";
		}
		if(p.getColor().equals("vert")) {
			return "jvert";
		}
		if(p.getColor().equals("bleu")) {
			return "jbleu";
		}
		if(p.getColor().equals("jaune")) {
			return "jjaune";
		}
		return null;
	}

}
