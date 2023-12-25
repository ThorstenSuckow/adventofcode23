package adventofcode23.day14;

import adventofcode23.lib.ResourceReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TiltParserTest {


    @Test
    @DisplayName("TiltParser")
    public void testTiltParser() {


        ResourceReader reader = new ResourceReader();
        String fileName = "input/day14/testinput.txt";
        String fileName2 = "input/day14/input.txt";
        TiltParser parser;
        parser = new TiltParser();

        reader.parseContents(fileName, parser);
        assertEquals(18, parser.tiltNorth().getMoveableCount());
        assertEquals(136, parser.tiltNorth().getSum());

        assertEquals(69, parser.cycle(3).getSum());

        parser = new TiltParser();
        reader.parseContents(fileName, parser);
        LinkedList<Long> linkedList = new LinkedList<>();
        CycleFinder<Long> cycleFinder = new CycleFinder<>();
        for (int i = 0; i < 100; i++) {
            long sum = parser.cycle().getSum();
            linkedList.add(sum);
            cycleFinder.add(linkedList.hasCycle());
        }


        Cycle<Long> c = cycleFinder.findUniqueCycle();
        assertNotNull(c);
        int start = c.start;
        int length = c.length;
        int set = 1_000_000_000;
        assertEquals(64, c.getAt(((set - start) % length) -1));

        parser = new TiltParser();
        reader.parseContents(fileName2, parser);
        linkedList = new LinkedList<>();
        cycleFinder = new CycleFinder<>();
        for (int i = 0; i < 2900; i++) {
            linkedList.add(parser.cycle().getSum());
            cycleFinder.add(linkedList.hasCycle());
        }

        c = cycleFinder.findUniqueCycle();
        assertNotNull(c);
        start = c.start;
        length = c.length;
        set = 1_000_000_000;
        assertEquals(98894, c.getAt(((set - start) % length) - 1));



}


}
