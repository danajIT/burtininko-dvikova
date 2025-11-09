package com.example.burtininkodvikova;

import com.example.burtininkodvikova.fxController.CombatController;
import com.example.burtininkodvikova.game.GameManager;
import com.example.burtininkodvikova.models.spells.DamageSpell;
import com.example.burtininkodvikova.models.spells.HealSpell;
import com.example.burtininkodvikova.models.spells.PoisonSpell;
import com.example.burtininkodvikova.models.wizards.PlayerWizard;
import com.example.burtininkodvikova.models.wizards.Wizard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("combat-view.fxml"));
        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(
                Objects.requireNonNull(
                        Main.class.getResource("/styles.css")
                ).toExternalForm()
        );

        Wizard w1 = new PlayerWizard("Arden", 100, 50, 1);
        Wizard w2 = new PlayerWizard("Mira", 100, 50, 1);

        w1.getSpellBook().addSpell(new DamageSpell("Fist Punch", 0, 5));
        w1.getSpellBook().addSpell(new DamageSpell("Firebolt", 10, 20));
        w1.getSpellBook().addSpell(new HealSpell("Healing Light", 8, 20));
        w1.getSpellBook().addSpell(new PoisonSpell("Toxic Spores", 12, 6, 3));

        w2.getSpellBook().addSpell(new DamageSpell("Fist Punch", 0, 5));
        w2.getSpellBook().addSpell(new DamageSpell("Boulder Smash", 8, 18));
        w2.getSpellBook().addSpell(new HealSpell("Earth Mend", 8, 20));
        w2.getSpellBook().addSpell(new PoisonSpell("Venom Seed", 12, 6, 3));

        GameManager manager = new GameManager(w1, w2);

        CombatController controller = loader.getController();
        controller.initializeGame(manager);
        
        stage.setTitle("Burtininko Dvikova");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
