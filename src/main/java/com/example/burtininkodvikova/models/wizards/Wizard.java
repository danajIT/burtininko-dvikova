package com.example.burtininkodvikova.models.wizards;

import com.example.burtininkodvikova.models.CombatLog;
import com.example.burtininkodvikova.models.SpellBook;
import com.example.burtininkodvikova.models.effects.StatusEffect;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Wizard {

    protected final String name;
    protected final int maxHealth;
    protected final int maxMana;

    @Setter protected int health;
    @Setter protected int mana;
    @Setter protected int shield;
    @Setter protected int level;

    protected final SpellBook spellBook = new SpellBook();
    protected final List<StatusEffect> statuses = new ArrayList<>();

    protected Wizard(String name, int maxHealth, int maxMana, int level) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.level = level;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean hasStatus(String statusName) {
        return statuses.stream()
                .anyMatch(s -> s.getName().equalsIgnoreCase(statusName));
    }

    public void applyStatusEffect(StatusEffect effect, CombatLog log) {
        statuses.add(effect);
        effect.onApply(this, log);
    }

    public void startTurn(CombatLog log) {
        for (StatusEffect s : List.copyOf(statuses)) {
            s.onTurnStart(this, log);
        }
        cleanupExpired(log);
    }

    public void endTurn(CombatLog log) {
        for (StatusEffect s : List.copyOf(statuses)) {
            s.onTurnEnd(this, log);
            s.tick();
        }
        cleanupExpired(log);
    }

    private void cleanupExpired(CombatLog log) {
        statuses.removeIf(s -> {
            if (s.isExpired()) {
                log.add(name + "'s " + s.getName() + " has worn off.");
                return true;
            }
            return false;
        });
    }


    public void heal(int amount, CombatLog log) {
        int before = health;
        health = Math.min(maxHealth, health + amount);
        log.add(name + " heals " + (health - before) + " HP.");
    }

    public boolean trySpendMana(int cost, CombatLog log) {
        if (mana < cost) {
            log.add(name + " does not have enough mana! (" + mana + "/" + maxMana + ")");
            return false;
        }
        mana -= cost;
        log.add(name + " spends " + cost + " mana (" + mana + "/" + maxMana + ")");
        return true;
    }

    public void gainMana(int amount, CombatLog log) {
        mana = Math.min(maxMana, mana + amount);
        log.add(name + " restores " + amount + " mana (" + mana + "/" + maxMana + ").");
    }

    public void takeDamage(int amount, CombatLog log) {
        if (amount > 0) {
            health = Math.max(0, health - amount);
            log.add(name + " takes " + amount + " damage (" + health + "/" + maxHealth + " HP).");
        }
    }
}
