package com.isw.app.presentation.properties;

import com.isw.app.enums.Balance;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.converter.NumberStringConverter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SimulationProperties {

  private final String ERROR_STYLE = "simulation-form__message--error";
  private final String SUCCESS_STYLE = "simulation-form__message--success";

  private final StringProperty message = new SimpleStringProperty();
  private final BooleanProperty success = new SimpleBooleanProperty();

  private final IntegerProperty turns = new SimpleIntegerProperty(1);
  private final IntegerProperty current = new SimpleIntegerProperty(0);

  private final BooleanProperty flagZombieMutation = new SimpleBooleanProperty(false);
  private final BooleanProperty flagOmnivoreExpansion = new SimpleBooleanProperty(false);

  private final ObjectProperty<Balance> balance = new SimpleObjectProperty<>(Balance.BALANCED);

  public Balance getBalance() {
    return balance.get();
  }

  public void setBalance(Balance value) {
    balance.set(value);
  }

  public Integer getMaxTurns() {
    return turns.get();
  }

  public void setMaxTurns(Integer value) {
    turns.set(value);
  }

  public Integer getCurrentTurn() {
    return current.get();
  }

  public void setCurrentTurn(Integer value) {
    current.set(value);
  }

  public boolean getFlagZombieMutation() {
    return flagZombieMutation.get();
  }

  public void setFlagZombieMutation(boolean value) {
    flagZombieMutation.set(value);
  }

  public boolean getFlagOmnivoreExpansion() {
    return flagOmnivoreExpansion.get();
  }

  public void setFlagOmnivoreExpansion(boolean value) {
    flagOmnivoreExpansion.set(value);
  }

  public boolean isSuccess() {
    return success.get();
  }

  public void bindLblTurnInfo(Label label) {
    label.textProperty().bind(this.current.asString("Turno: %d"));
  }

  public void bindFldMaxTurns(TextField turns) {
    NumberStringConverter converter = new NumberStringConverter();
    turns.textProperty().bindBidirectional(this.turns, converter);
  }

  public void bindChkZombieMutation(CheckBox zombieMutation) {
    zombieMutation.selectedProperty().bindBidirectional(this.flagZombieMutation);
  }

  public void bindChkOmnivoreExpansion(CheckBox omnivoreExpansion) {
    omnivoreExpansion.selectedProperty().bindBidirectional(this.flagOmnivoreExpansion);
  }

  public void setSuccess(boolean value, String message) {
    this.message.set(message);
    success.set(value);
  }

  public void bindIsSuccess(Label label) {
    this.success.addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next) {
        label.setText(this.message.get());
        label.getStyleClass().add(SUCCESS_STYLE);
      } else {
        label.setText(this.message.get());
        label.getStyleClass().add(ERROR_STYLE);
      }
    });
  }

  public void bindRdoBalance(ToggleGroup grpBalance) {
    grpBalance.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (next != null && next.getUserData() instanceof Balance) {
        this.balance.set((Balance) next.getUserData());
      }
    });

    this.balance.addListener((obs, prev, next) -> {
      if (next != null) {
        grpBalance.getToggles().stream()
            .filter(toggle -> next.equals(toggle.getUserData()))
            .findFirst()
            .ifPresent(toggle -> grpBalance.selectToggle(toggle));
      }
    });
  }
}
