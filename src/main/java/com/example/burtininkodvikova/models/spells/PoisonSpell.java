package com.example.burtininkodvikova.models.spells;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.effects.PoisonEffect;
import com.example.burtininkodvikova.models.wizards.Wizard;
import lombok.Getter;

@Getter
public class PoisonSpell implements Spell {

    private final String name;
    private final int manaCost;
    private final int turns;
    private final int damagePerTurn;

    public PoisonSpell(String name, int manaCost, int turns, int damagePerTurn) {
        this.name = name;
        this.manaCost = manaCost;
        this.turns = turns;
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    public void cast(Wizard caster, Wizard target, CombatLog log) {
        log.add(caster.getName() + " casts " + name + " poisoning " + target.getName() + "!");

        target.applyStatusEffect(
                new PoisonEffect(turns, damagePerTurn),
                log
        );
    }

    @Override
    public String toString() {
        return name + " (Poison: " + damagePerTurn + "/turn, " + turns + " turns, MP: " + manaCost + ")";
    }
}
