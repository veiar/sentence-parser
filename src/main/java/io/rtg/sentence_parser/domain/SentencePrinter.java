package io.rtg.sentence_parser.domain;

import java.io.PrintStream;
import java.util.EnumMap;
import java.util.stream.Stream;

public class SentencePrinter {

    public static boolean isSupportedFormat(String printFormatName) {
        return PrintFormat.isValidFormat(printFormatName);
    }

    public void print(Stream<Sentence> sentenceStream, String printFormatName, PrintStream printStream) {
        if (!isSupportedFormat(printFormatName)) {
            throw new RuntimeException(String.format("\"%s\" is not a supported format name!", printFormatName));
        }

        formatters.get(PrintFormat.valueOf(printFormatName)).printFormatted(sentenceStream, printStream);
    }

    private static final EnumMap<PrintFormat, SentenceFormatter> formatters = createFormattersMap();

    private static EnumMap<PrintFormat, SentenceFormatter> createFormattersMap() {
        EnumMap<PrintFormat, SentenceFormatter> formattersMap = new EnumMap<>(PrintFormat.class);
        formattersMap.put(PrintFormat.CSV, new CsvFormatter());
        return formattersMap;
    }
}
