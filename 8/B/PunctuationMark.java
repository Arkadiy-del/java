package org.example;

class PunctuationMark extends Lexeme {
    public PunctuationMark(String value) {
        super(value);
    }

    @Override
    public boolean isWord() {
        return false;
    }
}
