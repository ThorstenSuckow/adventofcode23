package adventofcode23.day4;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CopyScratchCardParserTest {


    @Test
    @DisplayName("parseLine")
    public void testParseLine() {

        ResourceReader reader = new ResourceReader();

        String fileName = "input/day4/testinput.txt";

        CopyScratchCardParser parser = new CopyScratchCardParser();
        parser.setAmountOfCards(reader.getLineCount(fileName));

        List<ParserResult> sums = reader.parseContents(fileName, parser);
        assertEquals(30, sums.getLast().getValue());

    }

}
