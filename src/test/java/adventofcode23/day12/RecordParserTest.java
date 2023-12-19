package adventofcode23.day12;

import adventofcode23.day11.GalaxyParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordParserTest {



    @Test
    @DisplayName("RecordParser")
    public void testRecordParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day12/testinput.txt";

        RecordParser parser;

        parser = new RecordParser();

      // assertEquals(4, parser.compute("????????? 3,1,2"));
        assertEquals(10, parser.compute("??????? 2,1"));
      /*  assertEquals(7, parser.compute("??????? 1"));
        assertEquals(6, parser.compute("??????? 2"));
        assertEquals(10, parser.compute("??????? 2,1"));
*/
        assertEquals(1, parser.compute("??????? 5,1"));
    }

}
