package org.example;

import java.util.ArrayList;
import java.util.List;

class Paragraph {
    private List<Sentence> sentences;

    public Paragraph() {
        sentences = new ArrayList<>();
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Sentence sentence : sentences) {
            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(sentence);
        }

        return result.toString();
    }
}
