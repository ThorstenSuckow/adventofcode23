package adventofcode23.day11;

import adventofcode23.day10.MazeParser;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyParserTest {

    @Test
    @DisplayName("GalaxyParser")
    public void testGalaxyParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day11/testinput.txt";

        GalaxyParser parser;

        parser = new GalaxyParser();
        reader.parseContents(fileName, parser);

        assertEquals(9, parser.computePath(4, 8));
        assertEquals(15, parser.computePath(0, 6));
        assertEquals(17, parser.computePath(2, 5));
        assertEquals(5, parser.computePath(7, 8));

        assertEquals(374, parser.computePaths());

    }

}
