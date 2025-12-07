package com.isw.app.presentation.controllers;

import javafx.fxml.FXML;
import com.isw.app.enums.Balance;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import com.isw.app.handlers.simulate.SimulateEcosystemSchema;
import com.isw.app.handlers.simulate.SimulateEcosystemHandler;
import com.isw.app.presentation.properties.SimulationProperties;

public class SimulationController {

  private final SimulationProperties properties = new SimulationProperties();
  private final SimulateEcosystemHandler handler = new SimulateEcosystemHandler(properties);

  private ToggleGroup grpScenario;

  @FXML
  private Button btnStart;

  @FXML
  private TextField fldTurns;

  @FXML
  private GridPane gridMatrix;

  @FXML
  private CheckBox chkOmnivoreExpansion, chkZombieMutation;

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
    rdoPreyDominant.setUserData(Balance.HERBIVORE_DOMINANT);
    rdoPredatorDominant.setUserData(Balance.CARNIVORE_DOMINANT);

    properties.bindIsSuccess(lblMessage);
    properties.bindFldMaxTurns(fldTurns);
    properties.bindRdoBalance(grpScenario);
    properties.bindLblTurnInfo(lblTurnInfo);
    properties.bindChkZombieMutation(chkZombieMutation);
    properties.bindChkOmnivoreExpansion(chkOmnivoreExpansion);
  }

  @FXML
  private void onStartClick() {
    SimulateEcosystemSchema schema = new SimulateEcosystemSchema(
        properties.getMaxTurns(),
        properties.getBalance());
    
    schema.setFlagZombieMutation(properties.getFlagZombieMutation());
    schema.setFlagOmnivoreExpansion(properties.getFlagOmnivoreExpansion());

    handler.handle(schema);
  }
}
