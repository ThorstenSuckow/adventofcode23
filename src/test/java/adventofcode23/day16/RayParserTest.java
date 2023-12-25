package adventofcode23.day16;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayParserTest {


    @Test
    @DisplayName("RayParser")
    public void testRayParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day16/testinput.txt";
        RayParser parser;
        parser = new RayParser();



        List<ParserResult> results = reader.parseContents(fileName, parser);
        parser.logRays();
        assertEquals(46, results.get(0).getValue());

    }

}