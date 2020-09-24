package io.rtg.sentence_parser


import io.rtg.sentence_parser.domain.SentenceParser
import spock.lang.Specification

import java.util.stream.Collectors

class SentenceParserTest extends Specification {

    def "Sentence parser reads single sentence"() {
        given:
        SentenceParser parser = new SentenceParser()

        when:
        def sentenceStream = parser.parse(streamFromString("This is test sentence."))
        List<String> sentenceWords = sentenceStream.map({ it -> it.sortedWords })
                                                   .flatMap({ it -> it.stream() })
                                                   .collect(Collectors.toList())
        then:
        sentenceWords == ["is", "sentence", "test", "This"]
    }

    // This test requires "run configuration" modified with "-Xmx32m" VM argument to test properly
    def "Sentence parser reads large input"() {
        given:
        SentenceParser parser = new SentenceParser()

        when:
        def sentenceStream = parser.parse(ClassLoader.getSystemResourceAsStream("test-input/large.in"))
        long sentenceCount = sentenceStream.map({ it -> it.sortedWords }).count()
        then:
        sentenceCount == 692737
    }

    def streamFromString(String string) {
        new ByteArrayInputStream(string.getBytes())
    }
}
