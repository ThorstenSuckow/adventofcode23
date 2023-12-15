package adventofcode23.day10;

import adventofcode23.day9.SensorParser;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeParserTest {

    @Test
    @DisplayName("MazeParser")
    public void testMazeParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day10/testinput_1.txt";


        MazeParser parser = new MazeParser();
        reader.parseContents(fileName, parser);

        parser.logMaze();

        assertArrayEquals(new int[]{1, 1}, parser.findStart());


        assertEquals(0, parser.compute());

    }

}
