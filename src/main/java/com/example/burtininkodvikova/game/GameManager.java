package com.example.burtininkodvikova.game;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.Element;
import com.example.burtininkodvikova.models.wizards.Wizard;
import com.example.burtininkodvikova.models.spells.Spell;
import lombok.Getter;

import java.util.List;

@Getter
public class GameManager {

    private final Wizard p1;
    private final Wizard p2;
    private Wizard current;
    private Wizard opponent;
    private final CombatLog log = new CombatLog();

    public GameManager(Wizard p1, Wizard p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.current = p1;
        this.opponent = p2;
    }

    public void announceTurn() {
        log.add("--- " + current.getName() + "'s turn ---");
    }

    public void performTurn(Spell spell) {
        current.startTurn(log);

        if (spell == null) {
            log.add(current.getName() + " failed to cast...");
            current.endTurn(log);
            return;
        }

        if (!current.trySpendMana(spell.getManaCost(), log)) {
            log.add(current.getName() + " cannot cast " + spell.getName() + "! There is not enough mana to spend!");
            current.endTurn(log);
            return;
        }

        spell.cast(current, opponent, log);

        current.endTurn(log);

        swapTurn();
        announceTurn();
    }


    private void swapTurn() {
        Wizard tmp = current;
        current = opponent;
        opponent = tmp;
    }

    public boolean isGameOver() {
        return !p1.isAlive() || !p2.isAlive();
    }

    public List<String> getLog() {
        return log.getEntries();
    }

}
