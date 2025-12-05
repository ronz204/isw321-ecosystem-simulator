package com.isw.app.properties;

import com.isw.app.enums.Balance;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.converter.NumberStringConverter;

public class SimulationProperties {

  private final String ERROR_STYLE = "simulation-form__message--error";
  private final String SUCCESS_STYLE = "simulation-form__message--success";

  private final IntegerProperty maxTurns = new SimpleIntegerProperty();
  private final StringProperty message = new SimpleStringProperty("");

  private final ObjectProperty<Balance> balance = new SimpleObjectProperty<>(Balance.BALANCED);

  private final BooleanProperty thirdSpecies = new SimpleBooleanProperty();
  private final BooleanProperty geneticMutation = new SimpleBooleanProperty();

  public Integer getMaxTurns() {
    return maxTurns.get();
  }

  public void setMaxTurns(Integer value) {
    maxTurns.set(value);
  }

  public Balance getBalance() {
    return balance.get();
  }

  public void setBalance(Balance value) {
    balance.set(value);
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
    NumberStringConverter converter = new NumberStringConverter();
    maxTurns.textProperty().bindBidirectional(this.maxTurns, converter);
  }

  public void bindChkThirdSpecies(CheckBox thirdSpecies) {
    thirdSpecies.selectedProperty().bindBidirectional(this.thirdSpecies);
  }

  public void bindChkGeneticMutation(CheckBox geneticMutation) {
    geneticMutation.selectedProperty().bindBidirectional(this.geneticMutation);
  }

  public void bindRdoScenario(ToggleGroup grpScenario) {
    grpScenario.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (next != null && next.getUserData() instanceof Balance) {
        this.balance.set((Balance) next.getUserData());
      }
    });

    this.balance.addListener((obs, prev, next) -> {
      if (next != null) {
        grpScenario.getToggles().stream()
            .filter(toggle -> next.equals(toggle.getUserData()))
            .findFirst()
            .ifPresent(toggle -> grpScenario.selectToggle(toggle));
      }
    });
  }

  public void listenLblMessage(Label label) {
    this.message.addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next != null && !next.isEmpty()) {
        label.getStyleClass().add(next.contains("exitosamente") ? SUCCESS_STYLE : ERROR_STYLE);
      }
    });
  }
}
