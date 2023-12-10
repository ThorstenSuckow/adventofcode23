package adventofcode23.day5;

import adventofcode23.day4.AlmanacParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlmanacParserTest {


    @Test
    @DisplayName("parseLine")
    public void testParseLine() {

        ResourceReader reader = new ResourceReader();

        String fileName = "input/day5/testinput.txt";

        AlmanacParser almanacParser = new AlmanacParser();
        reader.parseContents(fileName, almanacParser);

        /**
         * Seed 79, soil 81, fertilizer 81, water 81, light 74, temperature 78, humidity 78, location 82.
         * Seed 14, soil 14, fertilizer 53, water 49, light 42, temperature 42, humidity 43, location 43.
         * Seed 55, soil 57, fertilizer 57, water 53, light 46, temperature 82, humidity 82, location 86.
         * Seed 13, soil 13, fertilizer 52, water 41, light 34, temperature 34, humidity 35, location 35.
         */
        assertEquals(78L, almanacParser.getValueFor(79L, "temperature"));
        assertEquals(35L, almanacParser.getValueFor(13L, "location"));
        assertEquals(82L, almanacParser.getValueFor(55L, "humidity"));

        assertEquals(35L, almanacParser.getMinFor("location"));


    }

}
