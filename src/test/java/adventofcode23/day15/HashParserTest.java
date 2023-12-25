package adventofcode23.day15;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashParserTest {


    @Test
    @DisplayName("HashParser")
    public void testHashParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day15/testinput.txt";
        HashParser parser;
        parser = new HashParser();

        assertEquals(52, parser.valueOf("HASH"));
        assertEquals(30, parser.valueOf("rn=1"));
        assertEquals(253,parser.valueOf("cm-"));
        assertEquals(97, parser.valueOf("qp=3"));
        assertEquals(47, parser.valueOf("cm=2"));
        assertEquals(14, parser.valueOf("qp-"));
        assertEquals(180, parser.valueOf("pc=4"));
        assertEquals(9,  parser.valueOf("ot=9"));
        assertEquals(197, parser.valueOf("ab=5"));
        assertEquals(48,  parser.valueOf("pc-"));
        assertEquals(214, parser.valueOf("pc=6"));
        assertEquals(231,  parser.valueOf("ot=7"));

        List<ParserResult> results = reader.parseContents(fileName, parser);
        assertEquals(1320, results.get(0).getValue());

        long sum = parser.processLenses();
        assertEquals(145, sum);
    }

}