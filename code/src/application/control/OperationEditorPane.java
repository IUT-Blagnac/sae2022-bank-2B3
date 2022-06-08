package application.control;

import application.DailyBankApp;
import application.DailyBankState;
import application.tools.CategorieOperation;
import application.tools.StageManagement;
import application.view.OperationEditorPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.CompteCourant;
import model.data.Operation;

/**
 * Classe qui gère le contrôleur de la page de gestion des opérations (débit ou crédit) 
 */

public class OperationEditorPane {

	private Stage primaryStage;
	private OperationEditorPaneController oepc;
	
	 /**
	 * Constructeur de la classe
	 * @param _parentStage : la scene qui appelle celle-ci
	 * @param _dbstate : la session de l'utilisateur connecté
	 */

	public OperationEditorPane(Stage _parentStage, DailyBankState _dbstate) {

		try {
			FXMLLoader loader = new FXMLLoader(
					OperationEditorPaneController.class.getResource("operationeditorpane.fxml"));
			BorderPane root = loader.load();

			Scene scene = new Scene(root, 500 + 20, 250 + 10);
			scene.getStylesheets().add(DailyBankApp.class.getResource("application.css").toExternalForm());

			this.primaryStage = new Stage();
			this.primaryStage.initModality(Modality.WINDOW_MODAL);
			this.primaryStage.initOwner(_parentStage);
			StageManagement.manageCenteringStage(_parentStage, this.primaryStage);
			this.primaryStage.setScene(scene);
			this.primaryStage.setTitle("Enregistrement d'une opération");
			this.primaryStage.setResizable(false);

			this.oepc = loader.getController();
			this.oepc.initContext(this.primaryStage, _dbstate);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /**
	 * Permet de lancer la fonction du controleur de la page d'enregistrement d'un débit ou crédit 
	 * @param cpte : le compte sur lequel l'opération sera effectuer
	 * @param cm : le type d'operation (débit ou crédit)
	 */

	public Operation doOperationEditorDialog(CompteCourant cpte, CategorieOperation cm) {
		return this.oepc.displayDialog(cpte, cm);
	}
}
