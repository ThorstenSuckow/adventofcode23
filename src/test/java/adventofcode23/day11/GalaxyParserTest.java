package adventofcode23.day11;

import adventofcode23.day10.MazeParser;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyParserTest {

    @Test
    @DisplayName("GalaxyParser")
    public void testGalaxyParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day11/testinput.txt";
        String fileName2 = "input/day11/testinput2.txt";

        GalaxyParser parser;

        parser = new GalaxyParser(2);
        reader.parseContents(fileName, parser);

        assertArrayEquals(new int[]{2, 5, 8}, parser.getMultipliedCols());
        assertArrayEquals(new int[]{3, 7}, parser.getMultipliedRows());

        assertEquals(9, parser.computePath(4, 8));
        assertEquals(15, parser.computePath(0, 6));
        assertEquals(17, parser.computePath(2, 5));
        assertEquals(5, parser.computePath(7, 8));

        assertEquals(374, parser.computePaths());

        parser = new GalaxyParser(10);
        reader.parseContents(fileName, parser);
        assertEquals(1030, parser.computePaths());

        parser = new GalaxyParser(100);
        reader.parseContents(fileName, parser);
        assertEquals(8410, parser.computePaths());

        parser = new GalaxyParser(1);
        reader.parseContents(fileName2, parser);

        assertEquals(1, parser.computePath(0, 1));
        assertEquals(4, parser.computePath(1, 2));
        assertEquals(5, parser.computePath(0, 2));
    }

}
