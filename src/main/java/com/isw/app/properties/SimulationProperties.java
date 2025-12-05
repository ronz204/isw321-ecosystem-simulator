package com.isw.app.properties;

import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SimulationProperties {

  private final String ERROR_STYLE = "simulation-form__message--error";
  private final String SUCCESS_STYLE = "simulation-form__message--success";

  private final StringProperty maxTurns = new SimpleStringProperty();
  private final StringProperty scenario = new SimpleStringProperty();
  private final StringProperty message = new SimpleStringProperty("");

  private final BooleanProperty thirdSpecies = new SimpleBooleanProperty();
  private final BooleanProperty geneticMutation = new SimpleBooleanProperty();

  public String getMaxTurns() {
    return maxTurns.get();
  }

  public void setMaxTurns(String value) {
    maxTurns.set(value);
  }

  public String getScenario() {
    return scenario.get();
  }

  public void setScenario(String value) {
    scenario.set(value);
  }

  public boolean getThirdSpecies() {
    return thirdSpecies.get();
  }

  public void setThirdSpecies(boolean value) {
    thirdSpecies.set(value);
  }

  public boolean getGeneticMutation() {
    return geneticMutation.get();
  }

  public void setGeneticMutation(boolean value) {
    geneticMutation.set(value);
  }

  public String getMessage() {
    return message.get();
  }

  public void setMessage(String value) {
    message.set(value);
  }

  public void bindLblMessage(Label label) {
    label.textProperty().bind(this.message);
  }

  public void bindFldMaxTurns(TextField maxTurns) {
    maxTurns.textProperty().bindBidirectional(this.maxTurns);
  }

  public void bindChkThirdSpecies(CheckBox thirdSpecies) {
    thirdSpecies.selectedProperty().bindBidirectional(this.thirdSpecies);
  }

  public void bindChkGeneticMutation(CheckBox geneticMutation) {
    geneticMutation.selectedProperty().bindBidirectional(this.geneticMutation);
  }

  public void listenLblMessage(Label label) {
    this.message.addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next != null && !next.isEmpty()) {
        label.getStyleClass().add(next.contains("exitosamente") ? SUCCESS_STYLE : ERROR_STYLE);
      }
    });
  }

  public void listenRdoScenario(RadioButton rdoBalanced, RadioButton rdoPreyDominant, RadioButton rdoPredatorDominant,
      ToggleGroup grpScenario) {
    this.scenario.addListener((obs, prev, next) -> {
      if ("Balanceado".equals(next)) {
        rdoBalanced.setSelected(true);
      } else if ("Presa Dominante".equals(next)) {
        rdoPreyDominant.setSelected(true);
      } else if ("Depredador Dominante".equals(next)) {
        rdoPredatorDominant.setSelected(true);
      } else {
        rdoBalanced.setSelected(false);
        rdoPreyDominant.setSelected(false);
        rdoPredatorDominant.setSelected(false);
      }
    });

    grpScenario.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (rdoBalanced.isSelected()) {
        this.scenario.set("Balanceado");
      } else if (rdoPreyDominant.isSelected()) {
        this.scenario.set("Presa Dominante");
      } else if (rdoPredatorDominant.isSelected()) {
        this.scenario.set("Depredador Dominante");
      } else {
        this.scenario.set(null);
      }
    });
  }
}
