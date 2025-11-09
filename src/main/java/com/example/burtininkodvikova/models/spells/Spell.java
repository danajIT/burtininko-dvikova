package com.example.burtininkodvikova.models.spells;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.wizards.Wizard;

public interface Spell {
    String getName();
    int getManaCost();
    void cast(Wizard caster, Wizard target, CombatLog log);
}


