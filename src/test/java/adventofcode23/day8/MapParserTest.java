package adventofcode23.day8;

import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapParserTest {

    @Test
    @DisplayName("MapParser")
    public void testMapParser() {

        int expected = 14681;

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day8/input.txt";


        MapParser parser = new MapParser("AAA", "ZZZ");
        reader.parseContents(fileName, parser);
        parser.buildTree();

        assertEquals(expected, parser.navigate());

        // part two
        List<Integer> results = new ArrayList<>();

        Map<String, String[]> strMap =  parser.getStrMap();
        for (Map.Entry<String, String[]> entry: strMap.entrySet()) {
            if (entry.getKey().endsWith("A")) {
                parser = new MapParser(entry.getKey(), "Z");
                reader.parseContents(fileName, parser);
                parser.buildTree();
                results.add(parser.navigate());
            }
        }

        int[] expectedResults = new int[]{
                14681,
                16343,
                13019,
                21883,
                20221,
                16897
        };
        int j = 0;
        for (int res: results) {
            assertEquals(expectedResults[j++], res);
        }


    }

}
