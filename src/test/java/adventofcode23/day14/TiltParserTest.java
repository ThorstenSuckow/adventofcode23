package adventofcode23.day14;

import adventofcode23.day13.MapParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TiltParserTest {



@Test
    @DisplayName("TiltParser")
    public void testTiltParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day14/testinput.txt";
        TiltParser parser;
        parser = new TiltParser();

        reader.parseContents(fileName, parser);

        assertEquals(136, parser.tilt());

    }

}
