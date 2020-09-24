package io.rtg.sentence_parser.domain;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SentenceParser {

    private static final String SENTENCE_SEPARATOR = "\\.";

    public Stream<Sentence> parse(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter(SENTENCE_SEPARATOR);
        Stream<String> scannerStream = streamFromScanner(scanner);
        return scannerStream.map(Sentence::from);
    }

    private Stream<String> streamFromScanner(Scanner scanner) {
        Spliterator<String> scannerSpliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.NONNULL);
        return StreamSupport.stream(scannerSpliterator, false);
    }

    public static boolean isSupportedFormat(String printFormatName) {
        // FIXME
        return true;
    }
}
