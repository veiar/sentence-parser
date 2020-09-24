package io.rtg.sentence_parser;

import io.rtg.sentence_parser.domain.SentenceParser;
import io.rtg.sentence_parser.domain.SentencePrinter;

import java.io.InputStream;
import java.io.PrintStream;

public class Application {

    private static final String USAGE_INSTRUCTION = "Usage: <usage here>";

    private SentenceParser sentenceParser = new SentenceParser();
    private SentencePrinter sentencePrinter = new SentencePrinter();

    public static void main(String[] args) {
        if (args.length < 1 ||
                !SentencePrinter.isSupportedFormat(args[0])) {
            System.out.println(USAGE_INSTRUCTION);
            return;
        }
        new Application().parseAndPrintSentences(System.in, System.out, args[0]);
    }

    void parseAndPrintSentences(InputStream inputStream, PrintStream outputStream, String printFormatName) {
        sentencePrinter.print(sentenceParser.parse(inputStream), printFormatName, outputStream);
    }
}
