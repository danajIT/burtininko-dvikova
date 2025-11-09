package com.example.burtininkodvikova.models;

import com.example.burtininkodvikova.models.spells.Spell;
import lombok.Getter;
import java.util.*;

@Getter
public class SpellBook {

    private final List<Spell> spells = new ArrayList<>();

    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public List<Spell> getAllSpells() {
        return Collections.unmodifiableList(spells);
    }
}

