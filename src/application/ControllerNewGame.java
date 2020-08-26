/**
 * 
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ControllerNewGame implements Initializable{

	public Button buttonValidate;
	
	public RadioButton ia1;
	public RadioButton ia2;
	public RadioButton ia3;
	public RadioButton ia4;
	
	public RadioButton facile;
	public RadioButton moyen;
	public RadioButton difficile;
	
	public RadioButton normal;
	public RadioButton variante;
	public RadioButton varianteMalus;
	
	private int nbIa = 0;
	private int difficulte = 1;
	private int vari = 0;
	private int theme = 0;
	
	private ControllerLab ctl;
	
	/**
	 * Action quand l'on presse sur le radio bouton ia1
	 */
	public void handleIa1(){
		this.nbIa = 3;
	}

	/**
	 * Action quand l'on presse sur le radio bouton ia2
	 */
	public void handleIa2(){
		this.nbIa = 2;
	}
	
	/**
	 * Action quand l'on presse sur le radio bouton ia3
	 */
	public void handleIa3(){
		this.nbIa = 1;
	}
	
	/**
	 * Action quand l'on presse sur le radio bouton ia4
	 */
	public void handleIa4(){
		this.nbIa = 0;
	}
	
	/**
	 * Action quand l'on presse sur le radio bouton facile
	 */
	public void handleFacile() {
		this.difficulte = 1;
	}
	
	/**
	 * Action quand l'on presse sur le radio bouton moyen
	 */
	public void handleMoyen() {
		this.difficulte = 2;
	}
	
	/**
	 * Action quand l'on presse sur le radio bouton variante normal
	 */
	public void handleNormale() {
		this.vari = 0;
	}

	/**
	 * Action quand l'on presse sur le radio bouton variante trésor vivant
	 */
	public void handleVariante() {
		this.vari = 1;
	}

	/**
	 * Action quand l'on presse sur le radio bouton variante trésor vivant bonus/malus
	 */
	public void handleVarianteMalus() {
		this.vari = 2;
	}
	
	/**
	 * Action quand l'on presse sur le radio bouton thème normal
	 */
	public void handleBasique() {
		this.theme = 0;
	}

	/**
	 * Action quand l'on presse sur le radio bouton thème océan
	 */
	public void handleOcean() {
		this.theme = 1;
	}
	
	/**
	 * Action quand l'on presse sur le  bouton valider
	 */
	public void handleButtonValidate() {
		this.ctl.initGame(this.nbIa, this.difficulte, this.vari,this.theme);
		
		Stage stage = (Stage) buttonValidate.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Permet de set le contoller parent
	 * @param ctl controller parent
	 */
	public void setParentController(ControllerLab ctl) {
		this.ctl = ctl;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
