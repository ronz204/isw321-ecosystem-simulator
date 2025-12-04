package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class SimulationPresenter {
  private static final String ERROR_STYLE = "simulation-form__message--error";
  private static final String SUCCESS_STYLE = "simulation-form__message--success";

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
  }

  @FXML
  public void onStartClick() {
    System.out.println("Starting simulation...");
  }
}
