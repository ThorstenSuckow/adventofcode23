package adventofcode23.day13;

import adventofcode23.day11.GalaxyParser;
import adventofcode23.day12.RecordParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapParserTest {



    @Test
    @DisplayName("MapParser")
    public void testMapParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day13/testinput.txt";
        MapParser parser;
        parser = new MapParser();

        List<ParserResult> result = reader.parseContents(fileName, parser);

        assertEquals(405, (long) result.get(0).getValue());

    }

}
