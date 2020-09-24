package io.rtg.sentence_parser.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sentence {
    private final List<String> sortedWords;

    static Sentence from(String sentenceString) {
        return new Sentence(sortedWordList(sentenceString));
    }

    private Sentence(List<String> sortedWords) {
        this.sortedWords = sortedWords;
    }

    private static List<String> sortedWordList(String sentenceString) {
        return Arrays.stream(sentenceString.split("\\W+"))
                     .sorted(String::compareToIgnoreCase)
                     .collect(Collectors.toList());
    }

    public List<String> getSortedWords() {
        return sortedWords;
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
