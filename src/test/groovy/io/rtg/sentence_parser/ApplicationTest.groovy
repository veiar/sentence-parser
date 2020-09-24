package io.rtg.sentence_parser

import spock.lang.Specification

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

class ApplicationTest extends Specification {

    def "Application parses and prints csv properly"() {
        given:
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-input/small.in")
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        PrintStream printStream = new PrintStream(outputStream)
        String expectedOutput = readFromResource("test-output/small.csv")

        when:
        new Application().parseAndPrintSentences(inputStream, printStream, "CSV")

        then:
        outputStream.toString() == expectedOutput
    }

    String readFromResource(String resourcePath) {
        new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resourcePath).toURI())), Charset.defaultCharset())
    }
}
