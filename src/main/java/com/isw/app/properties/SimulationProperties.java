package com.isw.app.properties;

import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.converter.NumberStringConverter;

public class SimulationProperties {

  private final String ERROR_STYLE = "simulation-form__message--error";
  private final String SUCCESS_STYLE = "simulation-form__message--success";

  private final IntegerProperty maxTurns = new SimpleIntegerProperty();
  private final StringProperty scenario = new SimpleStringProperty();
  private final StringProperty message = new SimpleStringProperty("");

  private final BooleanProperty thirdSpecies = new SimpleBooleanProperty();
  private final BooleanProperty geneticMutation = new SimpleBooleanProperty();

  public Integer getMaxTurns() {
    return maxTurns.get();
  }

  public void setMaxTurns(Integer value) {
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
    maxTurns.textProperty().bindBidirectional(this.maxTurns, new NumberStringConverter());
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
      } else if ("Presas Dominantes".equals(next)) {
        rdoPreyDominant.setSelected(true);
      } else if ("Depredadores Dominantes".equals(next)) {
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
        this.scenario.set("Presas Dominantes");
      } else if (rdoPredatorDominant.isSelected()) {
        this.scenario.set("Depredadores Dominantes");
      } else {
        this.scenario.set(null);
      }
    });
  }
}
