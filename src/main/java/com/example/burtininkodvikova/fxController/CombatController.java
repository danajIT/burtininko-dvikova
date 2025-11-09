package com.example.burtininkodvikova.fxController;

import com.example.burtininkodvikova.game.GameManager;
import com.example.burtininkodvikova.models.Element;
import com.example.burtininkodvikova.models.spells.Spell;
import com.example.burtininkodvikova.models.wizards.Wizard;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CombatController {

    @FXML private ProgressBar healthBar;
    @FXML private Label healthValue;
    @FXML private ProgressBar manaBar;
    @FXML private Label manaValue;
    @FXML private Label wizardName;
    @FXML private Label wizardLvl;
    @FXML private ImageView wizardImage;

    @FXML private ProgressBar healthBar2;
    @FXML private Label healthValue2;
    @FXML private ProgressBar manaBar2;
    @FXML private Label manaValue2;
    @FXML private Label wizardName2;
    @FXML private Label wizardLvl2;
    @FXML private ImageView wizardImage2;

    @FXML private TextArea combatLogsField;
    @FXML private ComboBox<Spell> spellsComboBox;
    @FXML private Button castButton;

    private GameManager manager;
    private Wizard p1;
    private Wizard p2;
    private final List<Element> combo = new ArrayList<>();

    public void initializeGame(GameManager manager) {
        this.manager = manager;
        this.p1 = manager.getP1();
        this.p2 = manager.getP2();

        spellsComboBox.getItems().setAll(p1.getSpellBook().getAllSpells());

        castButton.setOnAction(e -> onCast());

        setupStaticInfo();
        updateUI();
        manager.announceTurn();
        setLog(manager.getLog());
    }

    private void setupStaticInfo() {
        wizardName.setText(p1.getName());
        wizardLvl.setText(p1.getLevel() + " Lvl");

        wizardName2.setText(p2.getName());
        wizardLvl2.setText(p2.getLevel() + " Lvl");

        wizardImage.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResource("/assets/blueWizard/Blue_Wizard_Card_GIF.gif")
                ).toExternalForm())
        );

        wizardImage2.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResource("/assets/redWizard/Red_Wizard_Card_GIF.gif")
                ).toExternalForm())
        );
        wizardImage2.setScaleX(-1);

        wizardImage.setPreserveRatio(false);
        wizardImage2.setPreserveRatio(false);
    }

    public void updateUI() {
        if (p1 != null) {
            healthBar.setProgress((double) p1.getHealth() / p1.getMaxHealth());
            healthValue.setText(p1.getHealth() + "/" + p1.getMaxHealth());

            manaBar.setProgress((double) p1.getMana() / p1.getMaxMana());
            manaValue.setText(p1.getMana() + "/" + p1.getMaxMana());
        }

        if (p2 != null) {
            healthBar2.setProgress((double) p2.getHealth() / p2.getMaxHealth());
            healthValue2.setText(p2.getHealth() + "/" + p2.getMaxHealth());

            manaBar2.setProgress((double) p2.getMana() / p2.getMaxMana());
            manaValue2.setText(p2.getMana() + "/" + p2.getMaxMana());
        }
        spellsComboBox.getItems().setAll(manager.getCurrent().getSpellBook().getAllSpells());
    }


    public void setLog(List<String> entries) {
        combatLogsField.clear();
        entries.forEach(s -> combatLogsField.appendText(s + "\n"));
        combatLogsField.setScrollTop(Double.MAX_VALUE);
    }

    public void addLogEntry(String msg) {
        combatLogsField.appendText(msg + "\n");
        combatLogsField.setScrollTop(Double.MAX_VALUE);
    }

    private void restartGame() {
        // For now: reload the whole application window
        javafx.application.Platform.runLater(() -> {
            try {
                // Reopen Main UI
                new com.example.burtininkodvikova.Main().start(
                        (Stage) castButton.getScene().getWindow()
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void showWinner() {

        Wizard winner = null;

        if (p1.isAlive() && !p2.isAlive()) {
            winner = p1;
        } else if (p2.isAlive() && !p1.isAlive()) {
            winner = p2;
        }

        if (winner != null) {
            addLogEntry("\n==== " + winner.getName() + " wins! ====\n");
        } else {
            addLogEntry("\n==== Draw! ====\n");
        }

        spellsComboBox.setDisable(true);

        castButton.setText("Restart");

        castButton.setOnAction(e -> restartGame());
    }


    @FXML
    private void onCast() {

        Spell selected = spellsComboBox.getValue();
        if (selected == null) {
            addLogEntry("No spell selected!");
            return;
        }

        manager.performTurn(selected);
        spellsComboBox.getSelectionModel().clearSelection();
        spellsComboBox.setValue(null);

        updateUI();
        setLog(manager.getLog());

        if (manager.isGameOver()) {
            showWinner();
        }
    }

}
