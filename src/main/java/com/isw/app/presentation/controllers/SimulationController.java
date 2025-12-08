package com.isw.app.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import jakarta.mail.MessagingException;
import com.isw.app.domain.enums.Balance;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import com.isw.app.application.mails.WelcomeMail;
import javafx.util.converter.NumberStringConverter;
import com.isw.app.application.services.ReportService;
import com.isw.app.application.services.MailingService;
import com.isw.app.application.contexts.SessionContext;
import com.isw.app.application.contexts.SimulationContext;
import com.isw.app.domain.core.setup.SimulatorInitializer;
import com.isw.app.application.handlers.simulate.SimulateEcosystemSchema;
import com.isw.app.application.handlers.simulate.SimulateEcosystemHandler;
import com.isw.app.infrastructure.repositories.ecosystem.EcosystemRepository;
import com.isw.app.infrastructure.repositories.ecosystem.EcosystemTLQRepository;

public class SimulationController {

  private static final String ERROR_STYLE = "simulation-form__message--error";
  private static final String SUCCESS_STYLE = "simulation-form__message--success";

  private final ReportService reportService = new ReportService();
  private final SessionContext session = SessionContext.getInstance();
  private final MailingService mailing = MailingService.getInstance();
  private final EcosystemRepository repository = new EcosystemTLQRepository();
  private final SimulationContext simulation = SimulationContext.getInstance();
  private final SimulateEcosystemHandler handler = new SimulateEcosystemHandler(repository);

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
  private Label lblMessage, lblTurnInfo;

  @FXML
  private RadioButton rdoBalanced, rdoPreyDominant, rdoPredatorDominant;

  @FXML
  public void initialize() {
    simulation.setPane(gridMatrix);

    grpScenario = new ToggleGroup();
    rdoBalanced.setToggleGroup(grpScenario);
    rdoPreyDominant.setToggleGroup(grpScenario);
    rdoPredatorDominant.setToggleGroup(grpScenario);

    rdoBalanced.setSelected(true);
    rdoBalanced.setUserData(Balance.BALANCED);
    rdoPreyDominant.setUserData(Balance.HERBIVORE_DOMINANT);
    rdoPredatorDominant.setUserData(Balance.CARNIVORE_DOMINANT);

    bindBtnStart(btnStart);
    bindIsSuccess(lblMessage);
    bindFldMaxTurns(fldTurns);
    bindRdoBalance(grpScenario);
    bindLblTurnInfo(lblTurnInfo);
    bindChkZombieMutation(chkZombieMutation);
    bindChkOmnivoreExpansion(chkOmnivoreExpansion);
    bindSimulationComplete();

    SimulatorInitializer.render();
    sendWelcomeEmail();
  }

  @FXML
  private void onStartClick() {
    SimulateEcosystemSchema schema = new SimulateEcosystemSchema(
        simulation.getTurns(),
        simulation.getBalance());

    schema.setFlagZombieMutation(simulation.getFlagZombieMutation());
    schema.setFlagOmnivoreExpansion(simulation.getFlagOmnivoreExpansion());

    handler.handle(schema);
  }

  private void sendWelcomeEmail() {
    new Thread(() -> {
      try {
        WelcomeMail mail = new WelcomeMail(session.getEmail());
        mailing.send(mail);
      } catch (MessagingException e) {
        System.err.println("Error sending welcome email: " + e.getMessage());
        e.printStackTrace();
      }
    }).start();
  }

  private void bindSimulationComplete() {
    simulation.runningProperty().addListener((obs, wasRunning, isRunning) -> {
      if (wasRunning && !isRunning) {
        onSimulationComplete();
      }
    });
  }

  private void onSimulationComplete() {
    reportService.generateAndSendAsync(session.getEmail());
  }

  private void bindBtnStart(Button button) {
    button.disableProperty().bind(simulation.runningProperty());
  }

  private void bindLblTurnInfo(Label label) {
    label.textProperty().bind(simulation.currentProperty().asString("Turno: %d"));
  }

  private void bindFldMaxTurns(TextField turns) {
    NumberStringConverter converter = new NumberStringConverter();
    turns.textProperty().bindBidirectional(simulation.turnsProperty(), converter);
  }

  private void bindChkZombieMutation(CheckBox zombieMutation) {
    zombieMutation.selectedProperty().bindBidirectional(simulation.flagZombieMutationProperty());
  }

  private void bindChkOmnivoreExpansion(CheckBox omnivoreExpansion) {
    omnivoreExpansion.selectedProperty().bindBidirectional(simulation.flagOmnivoreExpansionProperty());
  }

  private void bindIsSuccess(Label label) {
    simulation.successProperty().addListener((obs, prev, next) -> {
      label.getStyleClass().removeAll(ERROR_STYLE, SUCCESS_STYLE);

      if (next) {
        label.setText(simulation.getMessage());
        label.getStyleClass().add(SUCCESS_STYLE);
      } else {
        label.setText(simulation.getMessage());
        label.getStyleClass().add(ERROR_STYLE);
      }
    });
  }

  private void bindRdoBalance(ToggleGroup grpBalance) {
    grpBalance.selectedToggleProperty().addListener((obs, prev, next) -> {
      if (next != null && next.getUserData() instanceof Balance) {
        simulation.setBalance((Balance) next.getUserData());
      }
    });

    simulation.balanceProperty().addListener((obs, prev, next) -> {
      if (next != null) {
        grpBalance.getToggles().stream()
            .filter(toggle -> next.equals(toggle.getUserData()))
            .findFirst()
            .ifPresent(toggle -> grpBalance.selectToggle(toggle));
      }
    });
  }
}
