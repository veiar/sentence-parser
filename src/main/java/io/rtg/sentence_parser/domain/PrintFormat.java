package io.rtg.sentence_parser.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

enum PrintFormat {
    CSV, XML;

    private static final Set<String> FORMAT_NAMES = Arrays.stream(PrintFormat.values())
                                                          .map(Enum::name)
                                                          .collect(Collectors.toSet());

    static boolean isValidFormat(String formatName) {
        return FORMAT_NAMES.contains(formatName);
    }
}
