package com.isw.app.application.contexts;

import com.isw.app.domain.enums.Balance;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SimulationContext {
  private static SimulationContext instance;

  private SimulationContext() {
  }

  public static SimulationContext getInstance() {
    if (instance == null) {
      instance = new SimulationContext();
    }
    return instance;
  }

  // Propiedades de estado/status
  private final StringProperty message = new SimpleStringProperty();
  private final BooleanProperty success = new SimpleBooleanProperty();
  private final IntegerProperty turns = new SimpleIntegerProperty(1);
  private final IntegerProperty current = new SimpleIntegerProperty(0);

  // Propiedades de configuración
  private final ObjectProperty<Balance> balance = new SimpleObjectProperty<>(Balance.BALANCED);
  private final BooleanProperty flagZombieMutation = new SimpleBooleanProperty(false);
  private final BooleanProperty flagOmnivoreExpansion = new SimpleBooleanProperty(false);

  // Getters y setters para turnos
  public int getTurns() {
    return turns.get();
  }

  public void setTurns(int value) {
    turns.set(value);
  }

  public IntegerProperty turnsProperty() {
    return turns;
  }

  // Getters y setters para turno actual
  public int getCurrent() {
    return current.get();
  }

  public void setCurrent(int value) {
    current.set(value);
  }

  public IntegerProperty currentProperty() {
    return current;
  }

  // Getters y setters para mensaje
  public String getMessage() {
    return message.get();
  }

  public StringProperty messageProperty() {
    return message;
  }

  // Getters y setters para éxito
  public boolean isSuccess() {
    return success.get();
  }

  public void setSuccess(boolean value, String message) {
    this.message.set(message);
    success.set(value);
  }

  public BooleanProperty successProperty() {
    return success;
  }

  // Getters y setters para balance
  public Balance getBalance() {
    return balance.get();
  }

  public void setBalance(Balance value) {
    balance.set(value);
  }

  public ObjectProperty<Balance> balanceProperty() {
    return balance;
  }

  // Getters y setters para mutación zombie
  public boolean getFlagZombieMutation() {
    return flagZombieMutation.get();
  }

  public void setFlagZombieMutation(boolean value) {
    flagZombieMutation.set(value);
  }

  public BooleanProperty flagZombieMutationProperty() {
    return flagZombieMutation;
  }

  // Getters y setters para expansión omnívoro
  public boolean getFlagOmnivoreExpansion() {
    return flagOmnivoreExpansion.get();
  }

  public void setFlagOmnivoreExpansion(boolean value) {
    flagOmnivoreExpansion.set(value);
  }

  public BooleanProperty flagOmnivoreExpansionProperty() {
    return flagOmnivoreExpansion;
  }
}
