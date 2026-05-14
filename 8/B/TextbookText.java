package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TextbookText {
    private List<Paragraph> paragraphs;
    private List<Listing> listings;

    public TextbookText() {
        paragraphs = new ArrayList<>();
        listings = new ArrayList<>();
    }

    public void addParagraph(Paragraph paragraph) {
        paragraphs.add(paragraph);
    }

    public void addListing(Listing listing) {
        listings.add(listing);
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public List<Sentence> getAllSentences() {
        List<Sentence> allSentences = new ArrayList<>();

        for (Paragraph paragraph : paragraphs) {
            allSentences.addAll(paragraph.getSentences());
        }

        return allSentences;
    }

    public Word findWordFromFirstSentenceNotInOthers() {
        List<Sentence> sentences = getAllSentences();

        if (sentences.isEmpty()) {
            return null;
        }

        Sentence firstSentence = sentences.get(0);
        List<Word> wordsFromFirstSentence = firstSentence.getWords();

        Set<String> wordsFromOtherSentences = new HashSet<>();

        for (int i = 1; i < sentences.size(); i++) {
            for (Word word : sentences.get(i).getWords()) {
                wordsFromOtherSentences.add(word.getLowerCaseValue());
            }
        }

        for (Word word : wordsFromFirstSentence) {
            if (!wordsFromOtherSentences.contains(word.getLowerCaseValue())) {
                return word;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Paragraph paragraph : paragraphs) {
            result.append(paragraph).append("\n");
        }

        return result.toString();
    }
}
