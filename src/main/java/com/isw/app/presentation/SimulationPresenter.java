package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class SimulationPresenter {
  private static final String ERROR_STYLE = "simulation-form__message--error";
  private static final String SUCCESS_STYLE = "simulation-form__message--success";
  private static final int MATRIX_SIZE = 10;

  private ToggleGroup grpScenario;

  @FXML
  private GridPane gridMatrix;

  @FXML
  private TextField fldMaxTurns;

  @FXML
  private Button btnStart, btnPause;

  @FXML
  private CheckBox chkThirdSpecies, chkGeneticMutation;

  @FXML
  private Label lblMessage, lblTurnInfo, lblPreyInfo, lblPredatorInfo;

  @FXML
  private RadioButton rdoBalanced, rdoPreyDominant, rdoPredatorDominant;

  @FXML
  public void initialize() {
    grpScenario = new ToggleGroup();
    rdoBalanced.setToggleGroup(grpScenario);
    rdoPreyDominant.setToggleGroup(grpScenario);
    rdoPredatorDominant.setToggleGroup(grpScenario);
    
    initializeMatrix();
    
    rdoBalanced.setSelected(true);
  }

  private void initializeMatrix() {
    gridMatrix.getChildren().clear();
    
    for (int row = 0; row < MATRIX_SIZE; row++) {
      for (int col = 0; col < MATRIX_SIZE; col++) {

        StackPane cell = new StackPane();
        cell.getStyleClass().add("matrix-cell");
        
        Label icon = new Label("🌿");
        icon.getStyleClass().add("matrix-cell__icon");
        cell.getChildren().add(icon);
        
        gridMatrix.add(cell, col, row);
      }
    }
  }

  @FXML
  public void onStartClick() {
    // Este método será implementado después para la lógica de simulación
    showMessage("Simulación iniciada...", false);
  }

  private void showMessage(String message, boolean isError) {
    lblMessage.setText(message);
    lblMessage.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);
    lblMessage.getStyleClass().add(isError ? ERROR_STYLE : SUCCESS_STYLE);
  }
}
