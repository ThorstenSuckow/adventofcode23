package adventofcode23.day8;

import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapParserTest {

    @Test
    @DisplayName("MapParser")
    public void testMapParser() {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day8/testinput_2.txt";

        MapParser parser = new MapParser();
        reader.parseContents(fileName, parser);

        System.out.println(parser.navigate());

    }

}
