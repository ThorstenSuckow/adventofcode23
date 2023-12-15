package adventofcode23.day9;

import adventofcode23.day8.MapParser;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SensorParserTest {

    @Test
    @DisplayName("SensorParser")
    public void testSensorParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day9/testinput.txt";


        SensorParser parser = new SensorParser();
        reader.parseContents(fileName, parser);

        assertTrue(parser.allZeroes(new Long[]{0L, 0L, 0L}));
        assertFalse(parser.allZeroes(new Long[]{0L, 1L, 0L}));

        assertEquals(114, parser.compute());

        assertEquals(2, parser.computePartTwo());

    }

}
