package io.rtg.sentence_parser.domain;

import java.io.PrintStream;
import java.util.stream.Stream;

interface SentenceFormatter {
    void printFormatted(Stream<Sentence> sentenceStream, PrintStream printStream);
}
