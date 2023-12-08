package adventofcode23.day3;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import adventofcode23.lib.SymbolParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdjacentMachinePartMatcherTest {

    @Test
    @DisplayName("parseLine")
    public void testParseLine() {

        ResourceReader reader = new ResourceReader();

        List<ParserResult> machineParts = reader.parseContents("input/day3/testinput.txt", new MachinePartNumberParser());
        List<ParserResult> symbols = reader.parseContents("input/day3/testinput.txt", new SymbolParser("(\\*)"));

        AdjacentMachinePartMatcher rectMatcher = new AdjacentMachinePartMatcher(machineParts, symbols);

        assertEquals(467835, rectMatcher.sumParts());


    }
}
