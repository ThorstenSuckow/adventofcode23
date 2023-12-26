package adventofcode23.day18;

import adventofcode23.day16.RayParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiggerParserTest {

    @Test
    @DisplayName("RayParser")
    public void testRayParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day18/testinput.txt";
        DiggerParser parser;
        parser = new DiggerParser();



        List<ParserResult> results = reader.parseContents(fileName, parser);
        assertEquals(62, results.get(0).getValue());



    }

}
