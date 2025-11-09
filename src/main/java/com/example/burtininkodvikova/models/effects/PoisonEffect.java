package com.example.burtininkodvikova.models.effects;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.wizards.Wizard;

public class PoisonEffect extends BaseStatusEffect {

    private final int dmg;

    public PoisonEffect(int turns, int dmg) {
        super(turns);
        this.dmg = dmg;
    }

    @Override
    public String getName() {
        return "Poison";
    }

    @Override
    public void onApply(Wizard target, CombatLog log) {
        log.add(target.getName() + " is poisoned for " + remainingTurns + " turns!");
    }

    @Override
    public void onTurnStart(Wizard target, CombatLog log) {
        target.takeDamage(dmg, log);
        log.add(target.getName() + " suffers " + dmg + " poison damage!");
    }
}
