package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import com.isw.app.properties.SimulationProperties;

public class SimulationPresenter {

  private final SimulationProperties properties = new SimulationProperties();

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
    rdoBalanced.setSelected(true);
    rdoBalanced.setToggleGroup(grpScenario);
    rdoPreyDominant.setToggleGroup(grpScenario);
    rdoPredatorDominant.setToggleGroup(grpScenario);

    properties.bindLblMessage(lblMessage);
    properties.bindFldMaxTurns(fldMaxTurns);
    properties.bindChkThirdSpecies(chkThirdSpecies);
    properties.bindChkGeneticMutation(chkGeneticMutation);
    
    properties.listenLblMessage(lblMessage);
    properties.listenRdoScenario(rdoBalanced, rdoPreyDominant, rdoPredatorDominant, grpScenario);
  }

  @FXML
  public void onStartClick() {
    System.out.println("Starting simulation...");
  }
}
