package com.example.burtininkodvikova.models.effects;
import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.wizards.Wizard;

public interface StatusEffect {
    String getName();
    int getRemainingTurns();
    void onApply(Wizard target, CombatLog log);
    void onTurnStart(Wizard target, CombatLog log);
    void onTurnEnd(Wizard target, CombatLog log);
    void tick();
    boolean isExpired();
}

