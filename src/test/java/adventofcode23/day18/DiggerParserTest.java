package adventofcode23.day18;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiggerParserTest {

    @Test
    @DisplayName("DiggerParser")
    public void testDiggerParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day18/testinput.txt";
        DiggerParser parser;
        List<ParserResult> results;

        parser = new DiggerParser();
        results = reader.parseContents(fileName, parser);
        assertEquals(62, (int) (double) results.get(0).getValue());

        parser = new DiggerParser(true);
        results = reader.parseContents(fileName, parser);
        assertEquals( 952408144115L, (long) (double)results.get(0).getValue() );
    }

}
