package io.rtg.sentence_parser.domain;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

class CsvFormatter implements SentenceFormatter {

    private static final String FIRST_CSV_ROW = ", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8, Word 9, Word 10, Word 11, Word 12, Word 13, Word 14, Word 15, Word 16, Word 17, Word 18, Word 19, Word 20, Word 21, Word 22, Word 23, Word 24, Word 25, Word 26, Word 27, Word 28, Word 29, Word 30, Word 31, Word 32, Word 33";

    @Override
    public void printFormatted(Stream<Sentence> sentenceStream, PrintStream printStream) {
        printStream.println(FIRST_CSV_ROW);
        sentenceStream.filter(
                Predicate.not(Sentence::isEmpty)
        ).forEach(new Consumer<>() {
            int sentenceCounter = 1;

            @Override
            public void accept(Sentence sentence) {
                printStream.print(String.format("Sentence %d, ", sentenceCounter++));
                printStream.println(format(sentence));
            }
        });
    }

    private String format(Sentence sentence) {
        return String.join(", ", sentence.getSortedWords());
    }
}
