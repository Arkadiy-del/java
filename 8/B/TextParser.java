package org.example;

import java.util.ArrayList;
import java.util.List;

class TextParser {
    public static TextbookText parse(String sourceText) {
        TextbookText text = new TextbookText();

        String normalizedText = normalizeSpaces(sourceText);

        String[] paragraphParts = normalizedText.split("\\n+");

        for (String paragraphPart : paragraphParts) {
            paragraphPart = paragraphPart.trim();

            if (!paragraphPart.isEmpty()) {
                Paragraph paragraph = parseParagraph(paragraphPart);
                text.addParagraph(paragraph);
            }
        }

        return text;
    }

    private static Paragraph parseParagraph(String paragraphText) {
        Paragraph paragraph = new Paragraph();

        List<String> sentenceParts = splitIntoSentences(paragraphText);

        for (String sentencePart : sentenceParts) {
            Sentence sentence = parseSentence(sentencePart);

            if (!sentence.getLexemes().isEmpty()) {
                paragraph.addSentence(sentence);
            }
        }

        return paragraph;
    }

    private static Sentence parseSentence(String sentenceText) {
        Sentence sentence = new Sentence();

        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < sentenceText.length(); i++) {
            char currentChar = sentenceText.charAt(i);

            if (Character.isLetterOrDigit(currentChar)) {
                currentWord.append(currentChar);
            } else {
                if (currentWord.length() > 0) {
                    sentence.addLexeme(new Word(currentWord.toString()));
                    currentWord.setLength(0);
                }

                if (!Character.isWhitespace(currentChar)) {
                    sentence.addLexeme(new PunctuationMark(String.valueOf(currentChar)));
                }
            }
        }

        if (currentWord.length() > 0) {
            sentence.addLexeme(new Word(currentWord.toString()));
        }

        return sentence;
    }

    private static List<String> splitIntoSentences(String paragraphText) {
        List<String> sentences = new ArrayList<>();
        StringBuilder currentSentence = new StringBuilder();

        for (int i = 0; i < paragraphText.length(); i++) {
            char currentChar = paragraphText.charAt(i);
            currentSentence.append(currentChar);

            if (currentChar == '.' || currentChar == '!' || currentChar == '?') {
                sentences.add(currentSentence.toString().trim());
                currentSentence.setLength(0);
            }
        }

        if (currentSentence.length() > 0) {
            sentences.add(currentSentence.toString().trim());
        }

        return sentences;
    }

    private static String normalizeSpaces(String text) {
        String[] lines = text.split("\\R");
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            String normalizedLine = line.replaceAll("[\\t ]+", " ").trim();

            if (!normalizedLine.isEmpty()) {
                result.append(normalizedLine).append("\n");
            }
        }

        return result.toString().trim();
    }
}
