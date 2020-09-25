package io.rtg.sentence_parser


import io.rtg.sentence_parser.domain.SentenceParser
import spock.lang.Ignore
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

    def "Sentence parser reads words properly"() {
        given:
        SentenceParser parser = new SentenceParser()

        when:
        def sentenceStream = parser.parse(streamFromString(sentence))
        List<List<String>> sentenceWords = sentenceStream.map({ it -> it.sortedWords })
                                                   .collect(Collectors.toList())
        then:
        sentenceWords == expectedWords

        where:
        sentence                              || expectedWords
        "This is test sentence."              |  [["is", "sentence", "test", "This"]]
        " This is test sentence. "            |  [["is", "sentence", "test", "This"], []]
        " This   is   test   sentence. "      |  [["is", "sentence", "test", "This"], []]
        " These . Are Many .. Sentences. "    |  [["These"], ["Are", "Many"], [], ["Sentences"], []]
        "These,words"                         |  [["These", "words"]]
        "停在那儿,你这肮脏的掠夺者"              |  [["你这肮脏的掠夺者", "停在那儿"]]
        "These!words?form;a:sen-te(nc)e.here" |  [["These"], ["words"], ["a", "e", "form", "nc", "sen", "te"], ["here"]]
        "Hello Mr. Zang!"                     |  [["Hello", "Mr.", "Zang"]]
        "TEst Test test"                      |  [["test", "Test", "TEst"]]
    }

    def "Checking for emptiness works properly"() {
        given:
        SentenceParser parser = new SentenceParser()

        when:
        def sentenceStream = parser.parse(streamFromString(sentence))
        List<List<String>> sentenceWords = sentenceStream.filter({it -> !it.isEmpty() })
                                                         .map({ it -> it.sortedWords })
                                                         .collect(Collectors.toList())
        then:
        sentenceWords == expectedWords

        where:
        sentence                              || expectedWords
        " This is test sentence. "            |  [["is", "sentence", "test", "This"]]
        " This   is   test   sentence. "      |  [["is", "sentence", "test", "This"]]
        " These . Are Many .. Sentences. "    |  [["These"], ["Are", "Many"], ["Sentences"]]
    }

    // This test requires "run configuration" modified with "-Xmx32m" VM argument to test properly
    @Ignore
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
