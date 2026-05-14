package org.example;

import java.util.ArrayList;
import java.util.List;

class Word extends Lexeme {
    private List<Symbol> symbols;

    public Word(String value) {
        super(value);
        symbols = new ArrayList<>();

        for (int i = 0; i < value.length(); i++) {
            symbols.add(new Symbol(value.charAt(i)));
        }
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    @Override
    public boolean isWord() {
        return true;
    }
}
