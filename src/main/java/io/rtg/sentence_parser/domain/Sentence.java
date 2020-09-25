package io.rtg.sentence_parser.domain;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sentence {

    private static final String WORD_SEPARATOR_REGEX = "\\s+|;|:|\\(|\\)|,|-";
    private static final Map<Character, Character> REPLACEMENTS = Map.of('’', '\'');
    private final List<String> sortedWords;

    static Sentence from(String sentenceString) {
        return new Sentence(sortedWordList(sentenceString));
    }

    private Sentence(List<String> sortedWords) {
        this.sortedWords = sortedWords;
    }

    private static List<String> sortedWordList(String sentenceString) {
        String fixedSentence = applyReplacements(sentenceString);
        return Arrays.stream(fixedSentence.trim().split(WORD_SEPARATOR_REGEX))
                     .filter(Predicate.not(String::isEmpty))
                     .sorted(String::compareToIgnoreCase)
                     .collect(Collectors.toUnmodifiableList());
    }

    private static String applyReplacements(String sentenceString) {
        String result = sentenceString;
        for (Map.Entry<Character, Character> characterCharacterEntry : REPLACEMENTS.entrySet()) {
            result = result.replace(characterCharacterEntry.getKey(), characterCharacterEntry.getValue());
        }
        return result;
    }

    public List<String> getSortedWords() {
        return sortedWords;
    }

    public boolean isEmpty() {
        return sortedWords.isEmpty()
                || sortedWords.stream().allMatch(String::isBlank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return getSortedWords().equals(sentence.getSortedWords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSortedWords());
    }

    @Override
    public String toString() {
        return sortedWords.toString();
    }
}
