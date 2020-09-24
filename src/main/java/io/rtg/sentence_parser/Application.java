package io.rtg.sentence_parser;

import io.rtg.sentence_parser.domain.SentenceParser;

public class Application {

    private static final String USAGE_INSTRUCTION = "Usage: <usage here>";

    public static void main(String[] args) {
        if (args.length < 1 ||
                !SentenceParser.isSupportedFormat(args[0])) {
            System.out.println(USAGE_INSTRUCTION);
            return;
        }
        // FIXME
        System.out.println(new SentenceParser().parse(System.in).count());
    }
}
