package org.example;

abstract class Lexeme {
    private String value;

    public Lexeme(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getLowerCaseValue() {
        return value.toLowerCase();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public abstract boolean isWord();

    @Override
    public String toString() {
        return value;
    }
}
