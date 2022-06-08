package application.control;

import application.DailyBankApp;
import application.DailyBankState;
import application.tools.EditionMode;
import application.tools.StageManagement;
import application.view.CompteEditorPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Client;
import model.data.CompteCourant;

/**
* Classe qui gère le contrôleur de la page de gestion des comptes (ajout et modification d'un compte)
*/

public class CompteEditorPane {

	private Stage primaryStage;
	private CompteEditorPaneController cepc;
	
	/**
	 * Constructeur de la classe 
	 * @param _parentStage : la scene qui appelle celle-ci
	 * @param _dbstate : la session en cours de l'utilisateur connecté
	 */

	public CompteEditorPane(Stage _parentStage, DailyBankState _dbstate) {

		try {
			FXMLLoader loader = new FXMLLoader(CompteEditorPaneController.class.getResource("compteeditorpane.fxml"));
			BorderPane root = loader.load();

			Scene scene = new Scene(root, root.getPrefWidth()+20, root.getPrefHeight()+10);
			scene.getStylesheets().add(DailyBankApp.class.getResource("application.css").toExternalForm());

			this.primaryStage = new Stage();
			this.primaryStage.initModality(Modality.WINDOW_MODAL);
			this.primaryStage.initOwner(_parentStage);
			StageManagement.manageCenteringStage(_parentStage, this.primaryStage);
			this.primaryStage.setScene(scene);
			this.primaryStage.setTitle("Gestion d'un compte");
			this.primaryStage.setResizable(false);

			this.cepc = loader.getController();
			this.cepc.initContext(this.primaryStage, _dbstate);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		/**
	 * Lance la fonction du contrôleur de la page d'ajout ou de modification des comptes pour afficher la scene
	 * @param client : le client à qui appartient le compte
	 * @param cpte : le compte à modifier
	 * @param em : indique le mode d'édition (ajout, modification ou suppression)
	 * @return le client modifié
	 */

	public CompteCourant doCompteEditorDialog(Client client, CompteCourant cpte, EditionMode em) {
		return this.cepc.displayDialog(client, cpte, em);
	}
}
