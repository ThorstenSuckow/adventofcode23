package adventofcode23.lib;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangeTest {


    @Test
    @DisplayName("Range")
    public void testRange() {

        Range r = new Range(1, 0, 1);
        assertEquals(Interval.make(0, 0), r.getSourceInterval());

        r = new Range(895998030, 0, 382128379);
        assertEquals(Interval.make(0, 382128379 - 1), r.getSourceInterval());

        assertEquals(Interval.make(895998030, 895998030 + 382128379 - 1), r.getDestinationInterval());

        r = new Range(0, 1243838521, 558555556);
        assertEquals(Interval.make(0, 558555556 - 1), r.getDestinationInterval());
    }
}
