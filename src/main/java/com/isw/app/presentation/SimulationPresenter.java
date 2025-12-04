package com.isw.app.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import com.isw.app.enums.Balance;
import com.isw.app.handlers.simulate.SimulateEcosystemHandler;
import com.isw.app.handlers.simulate.SimulateEcosystemResponse;
import com.isw.app.handlers.simulate.SimulateEcosystemSchema;
import com.isw.app.core.objects.Detail;
import com.isw.app.helpers.ValidatorHelper;
import com.isw.app.properties.SimulationProperties;

public class SimulationPresenter {
  private static final String ERROR_STYLE = "simulation-form__message--error";
  private static final String SUCCESS_STYLE = "simulation-form__message--success";
  private static final int MATRIX_SIZE = 10;

  private final SimulateEcosystemHandler handler = new SimulateEcosystemHandler();
  private final SimulationProperties properties = handler.getProperties();

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

    initializeMatrix();
    setupBindings();

    rdoBalanced.setSelected(true);
  }

  private void setupBindings() {
    lblTurnInfo.textProperty().bind(
        properties.currentTurn.asString()
            .concat(" / ")
            .concat(properties.maxTurns));

    lblPreyInfo.textProperty().bind(
        properties.preyCount.asString("🐰 Presas: %d"));

    lblPredatorInfo.textProperty().bind(
        properties.predatorCount.asString("🦊 Depredadores: %d"));

    properties.message.addListener((obs, oldVal, newVal) -> {
      lblMessage.setText(newVal);
    });

    btnStart.disableProperty().bind(properties.isRunning);

    properties.ecosystem.addListener((obs, oldVal, newVal) -> {
      if (newVal != null) {
        updateMatrixUI();
      }
    });

    properties.currentTurn.addListener((obs, oldVal, newVal) -> {
      updateMatrixUI();
    });
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
    SimulateEcosystemSchema schema = getFormFields();

    var violations = ValidatorHelper.validate(schema);
    if (!violations.isEmpty()) {
      showMessage(ValidatorHelper.getMessages(violations), true);
      return;
    }

    try {
      SimulateEcosystemResponse response = handler.handle(schema.toCommand());

      if (response.isSuccess()) {
        showMessage(response.message(), false);
      } else {
        showMessage(response.message(), true);
      }
    } catch (Exception e) {
      showMessage("Error: " + e.getMessage(), true);
    }
  }

  private SimulateEcosystemSchema getFormFields() {
    Integer maxTurns = parseMaxTurns();
    String balanceType = getSelectedBalance();
    boolean thirdSpecies = chkThirdSpecies.isSelected();
    boolean geneticMutation = chkGeneticMutation.isSelected();

    return new SimulateEcosystemSchema(maxTurns, balanceType, thirdSpecies, geneticMutation);
  }

  private Integer parseMaxTurns() {
    try {
      String text = fldMaxTurns.getText();
      if (text == null || text.trim().isEmpty()) {
        return null;
      }
      return Integer.parseInt(text.trim());
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private String getSelectedBalance() {
    if (rdoBalanced.isSelected())
      return Balance.BALANCED.getValue();
    if (rdoPredatorDominant.isSelected())
      return Balance.PREDATOR_DOMINANT.getValue();
    if (rdoPreyDominant.isSelected())
      return Balance.PREY_DOMINANT.getValue();
    return null;
  }

  private void updateMatrixUI() {
    var ecosystem = properties.ecosystem.get();
    if (ecosystem == null)
      return;

    gridMatrix.getChildren().clear();
    var matrix = ecosystem.getMatrix();

    for (int row = 0; row < MATRIX_SIZE; row++) {
      for (int col = 0; col < MATRIX_SIZE; col++) {
        StackPane cell = new StackPane();
        cell.getStyleClass().add("matrix-cell");

        var sector = matrix.getSectorAt(row, col);
        String icon = "🌿";

        if (!sector.isEmpty()) {
          var animal = sector.getAnimal();
          if (animal.getDetail() == Detail.HERBIVORE) {
            icon = "🐰";
          } else if (animal.getDetail() == Detail.CARNIVORE) {
            icon = "🦊";
          }
        }

        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("matrix-cell__icon");
        cell.getChildren().add(iconLabel);

        gridMatrix.add(cell, col, row);
      }
    }
  }

  private void showMessage(String message, boolean isError) {
    lblMessage.setText(message);
    lblMessage.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);
    lblMessage.getStyleClass().add(isError ? ERROR_STYLE : SUCCESS_STYLE);
  }
}
