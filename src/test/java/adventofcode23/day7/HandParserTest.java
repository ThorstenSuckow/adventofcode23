package adventofcode23.day7;

import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandParserTest {



    @Test
    @DisplayName("HandParser")
    public void testHandParser() {

        ResourceReader reader = new ResourceReader();

        String fileName = "input/day7/testinput.txt";

        HandParser parser = new HandParser();
        reader.parseContents(fileName, parser);

        assertEquals(6440L, parser.computeTotalWinnings());
    }
}
