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
        parser = new DiggerParser();

        List<ParserResult> results = reader.parseContents(fileName, parser);

        parser.logRows();

        assertEquals(62, (int) (double) results.get(0).getValue());



    }

}
