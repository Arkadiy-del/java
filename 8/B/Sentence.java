package org.example;

import java.util.ArrayList;
import java.util.List;

class Sentence {
    private List<Lexeme> lexemes;

    public Sentence() {
        lexemes = new ArrayList<>();
    }

    public void addLexeme(Lexeme lexeme) {
        lexemes.add(lexeme);
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();

        for (Lexeme lexeme : lexemes) {
            if (lexeme.isWord()) {
                words.add((Word) lexeme);
            }
        }

        return words;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Lexeme lexeme : lexemes) {
            if (lexeme instanceof PunctuationMark) {
                result.append(lexeme);
            } else {
                if (result.length() > 0) {
                    result.append(" ");
                }

                result.append(lexeme);
            }
        }

        return result.toString();
    }
}
