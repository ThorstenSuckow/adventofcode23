package adventofcode23.day16;

package adventofcode23.day15;

import adventofcode23.day15.HashParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RayParserTest {


    @Test
    @DisplayName("RayParser")
    public void testRayParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day16/testinput.txt";
        RayParser parser;
        parser = new RayParser();



        List<ParserResult> results = reader.parseContents(fileName, parser);
        assertEquals(1320, results.get(0).getValue());

    }

}