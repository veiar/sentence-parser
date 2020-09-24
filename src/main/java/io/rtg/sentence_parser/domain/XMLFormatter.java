package io.rtg.sentence_parser.domain;

import java.io.PrintStream;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XMLFormatter implements SentenceFormatter {

    private static final String FIRST_XML_ROW = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>";
    private static final String LAST_XML_ROW = "</text>";

    @Override
    public void printFormatted(Stream<Sentence> sentenceStream, PrintStream printStream) {
        printStream.println(FIRST_XML_ROW);
        sentenceStream.filter(
                Predicate.not(Sentence::isEmpty)
        ).forEach(sentence -> printStream.println(format(sentence)));
        printStream.print(LAST_XML_ROW);
    }

    private String format(Sentence sentence) {
        return String.format("%s%s%s", "<sentence>", formatWords(sentence), "</sentence>");
    }

    private String formatWords(Sentence sentence) {
        return sentence.getSortedWords().stream()
                                        .map(word -> word.replace("'", "&apos;"))
                                        .map(word -> String.format("%s%s%s", "<word>", word, "</word>"))
                                        .collect(Collectors.joining(""));
    }
}
