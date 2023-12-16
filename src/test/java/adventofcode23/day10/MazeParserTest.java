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
        String fileName2 = "input/day10/testinput_2.txt";
        MazeParser parser;

        parser = new MazeParser();
        reader.parseContents(fileName, parser);

        assertEquals(4, parser.compute());

        parser = new MazeParser();
        reader.parseContents(fileName2, parser);
        assertEquals(8, parser.compute());


    }

}
