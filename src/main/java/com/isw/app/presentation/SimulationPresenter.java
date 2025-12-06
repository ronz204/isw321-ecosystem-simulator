package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import com.isw.app.enums.Balance;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import com.isw.app.core.objects.Matrix;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.properties.SimulationProperties;
import com.isw.app.handlers.simulate.SimulateEcosystemSchema;
import com.isw.app.handlers.simulate.SimulateEcosystemHandler;

public class SimulationPresenter {

  private final SimulationProperties properties = new SimulationProperties();
  private final SimulateEcosystemHandler handler = new SimulateEcosystemHandler(properties);

  private ToggleGroup grpScenario;
  
  @FXML
  private Button btnStart;

  @FXML
  private GridPane gridMatrix;

  @FXML
  private TextField fldMaxTurns;

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

    rdoBalanced.setUserData(Balance.BALANCED);
    rdoPreyDominant.setUserData(Balance.PREY_DOMINANT);
    rdoPredatorDominant.setUserData(Balance.PREDATOR_DOMINANT);

    properties.bindLblMessage(lblMessage);
    properties.bindFldMaxTurns(fldMaxTurns);
    properties.bindRdoScenario(grpScenario);
    properties.bindChkThirdSpecies(chkThirdSpecies);
    properties.bindChkGeneticMutation(chkGeneticMutation);
    
    properties.listenLblMessage(lblMessage);
    
    drawMatrix();
  }
  
  private void drawMatrix() {
    gridMatrix.getChildren().clear();
    
    for (int row = 0; row < Matrix.ROWS; row++) {
      for (int col = 0; col < Matrix.COLS; col++) {
        Label cellLabel = new Label("🌿");

        cellLabel.getStyleClass().add("matrix-cell__icon");
        cellLabel.setAlignment(Pos.CENTER);
        cellLabel.setMaxWidth(Double.MAX_VALUE);
        cellLabel.setMaxHeight(Double.MAX_VALUE);
        
        StackPane cell = new StackPane(cellLabel);
        cell.getStyleClass().add("matrix-cell");
        gridMatrix.add(cell, col, row);
      }
    }
  }

  @FXML
  public void onStartClick() {
    SimulateEcosystemSchema schema = new SimulateEcosystemSchema(
      properties.getMaxTurns(),
      properties.getBalance().getName()
    );

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      properties.setMessage(ValidatorHelper.getMessages(violations));
      return;
    }

    handler.handle(schema);
  }
}
