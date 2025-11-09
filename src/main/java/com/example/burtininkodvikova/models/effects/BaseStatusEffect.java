package com.example.burtininkodvikova.models.effects;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.wizards.Wizard;
import lombok.Getter;

@Getter
public abstract class BaseStatusEffect implements StatusEffect {

    protected int remainingTurns;

    protected BaseStatusEffect(int turns) {
        this.remainingTurns = turns;
    }

    @Override
    public void tick() {
        remainingTurns--;
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    // Default empty implementation
    @Override
    public void onApply(Wizard target, CombatLog log) {}

    @Override
    public void onTurnStart(Wizard target, CombatLog log) {}

    @Override
    public void onTurnEnd(Wizard target, CombatLog log) {}
}
