package io.rtg.sentence_parser

import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

class ApplicationTest extends Specification {

    @Unroll
    def "Application parses and prints #formatName properly"() {
        given:
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-input/small.in")
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        PrintStream printStream = new PrintStream(outputStream)
        String expectedOutput = readFromResource("test-output/small." + formatName.toLowerCase())

        when:
        new Application().parseAndPrintSentences(inputStream, printStream, formatName)

        then:
        outputStream.toString() == expectedOutput

        where:
        formatName << ["CSV", "XML"]
    }

    String readFromResource(String resourcePath) {
        new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resourcePath).toURI())), Charset.defaultCharset())
    }
}
