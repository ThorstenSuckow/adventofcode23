package adventofcode23.day5;

import adventofcode23.day4.AlmanacParser;
import adventofcode23.lib.Interval;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AlmanacParserTest {


    @Test
    @DisplayName("parseLine")
    public void testParseLine() throws InterruptedException {

        ResourceReader reader = new ResourceReader();

        String fileName = "input/day5/testinput.txt";

        AlmanacParser almanacParser = new AlmanacParser(false);
        reader.parseContents(fileName, almanacParser);

        /**
         * Seed 79, soil 81, fertilizer 81, water 81, light 74, temperature 78, humidity 78, location 82.
         * Seed 14, soil 14, fertilizer 53, water 49, light 42, temperature 42, humidity 43, location 43.
         * Seed 55, soil 57, fertilizer 57, water 53, light 46, temperature 82, humidity 82, location 86.
         * Seed 13, soil 13, fertilizer 52, water 41, light 34, temperature 34, humidity 35, location 35.
         */
        assertEquals(78L, almanacParser.getDestinationValue(79L, "temperature"));
        assertEquals(35L, almanacParser.getDestinationValue(13L, "location"));
        assertEquals(82L, almanacParser.getDestinationValue(55L, "humidity"));


        Interval[] res = almanacParser.getDestination(new Interval[]{Interval.make(79, 79)}, "soil");
        assertArrayEquals(
            new Interval[]{
                Interval.make(81, 81),
            },
            almanacParser.getDestination(new Interval[]{Interval.make(79, 79)}, "soil")
        );

        res = almanacParser.getDestinationIntervals(Interval.make(79, 79), "soil");
        assertArrayEquals(
            new Interval[]{
                Interval.make(81, 81),
            },
            almanacParser.getDestinationIntervals(Interval.make(79, 79), "soil")
        );


        res = almanacParser.getDestination(new Interval[]{Interval.make(13, 13)}, "location");
        System.out.println(Arrays.toString(res));
        assertArrayEquals(
            new Interval[]{
                Interval.make(35, 35),
            },
            almanacParser.getDestination(new Interval[]{Interval.make(13, 13)}, "location")
        );

       // assertEquals(35L, almanacParser.getMinFor("location", 4));

        almanacParser = new AlmanacParser();
        reader.parseContents(fileName, almanacParser);
        assertSame(35L, almanacParser.getMinFromIntervals(almanacParser.findLocationIntervals()));

        almanacParser = new AlmanacParser(true);
        reader.parseContents(fileName, almanacParser);
        assertSame(46L, almanacParser.getMinFromIntervals(almanacParser.findLocationIntervals()));
    }

}
