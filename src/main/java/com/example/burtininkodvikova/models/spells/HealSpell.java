package com.example.burtininkodvikova.models.spells;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.wizards.Wizard;
import lombok.Getter;

@Getter
public class HealSpell implements Spell {

    private final String name;
    private final int manaCost;
    private final int healAmount;

    public HealSpell(String name, int manaCost, int healAmount) {
        this.name = name;
        this.manaCost = manaCost;
        this.healAmount = healAmount;
    }

    @Override
    public void cast(Wizard caster, Wizard target, CombatLog log) {
        log.add(caster.getName() + " casts " + name + " healing " + healAmount + "!");

        caster.heal(healAmount, log);
    }

    @Override
    public String toString() {
        return name + " (HEAL: " + healAmount + ", MP: " + manaCost + ")";
    }
}
