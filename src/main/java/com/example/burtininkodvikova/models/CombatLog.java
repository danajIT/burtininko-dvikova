package com.example.burtininkodvikova.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class CombatLog {

    private final List<String> entries = new ArrayList<>();

    public void add(String message) {
        entries.add(message);
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public void clear() {
        entries.clear();
    }

    @Override
    public String toString() {
        return String.join("\n", entries);
    }
}
