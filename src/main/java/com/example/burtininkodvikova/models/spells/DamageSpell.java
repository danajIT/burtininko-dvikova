package com.example.burtininkodvikova.models.spells;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.wizards.Wizard;
import lombok.Getter;

@Getter
public class DamageSpell implements Spell {

    private final String name;
    private final int manaCost;
    private final int damage;

    public DamageSpell(String name, int manaCost, int damage) {
        this.name = name;
        this.manaCost = manaCost;
        this.damage = damage;
    }

    @Override
    public void cast(Wizard caster, Wizard target, CombatLog log) {
        log.add(caster.getName() + " casts " + name + " dealing "
                + damage + " damage!");

        target.takeDamage(damage, log);
    }

    @Override
    public String toString() {
        return getName() + " (DMG: " + damage + ", MP: " + manaCost + ")";
    }
}
