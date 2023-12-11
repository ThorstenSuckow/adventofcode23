package adventofcode23.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class IntervalTest {


    @Test
    @DisplayName("contains")
    public void testContains() {

        Interval a = Interval.make(40, 100);
        Interval b = Interval.make(50, 90);

        assertTrue(a.contains(b));
        assertFalse(b.contains(a));
    }

    @Test
    @DisplayName("complement")
    public void testComplement() {


        //   A       B
        // [1, 4], [1, 5] -> null
        assertNull(Interval.make(1, 4).complementTo(Interval.make(1, 5)));

        //   A       B
        // [3, 4], [1, 5] -> null
        assertNull(Interval.make(3, 4).complementTo(Interval.make(1, 5)));

        //   A       B
        // [40, 50], [51, 60] -> [51, 60]
        assertSame(1, Interval.make(40, 50).complementTo(Interval.make(51, 60)).length);
        assertEquals(Interval.make(40, 50), Interval.make(40, 50).complementTo(Interval.make(51, 60))[0]);

        //   A       B
        // [1, 4], [4, 5] -> [1, 3]
        assertSame(1, Interval.make(1, 4).complementTo(Interval.make(4, 5)).length);
        assertEquals(Interval.make(1, 3), Interval.make(1, 4).complementTo(Interval.make(4, 5))[0]);

        //   A       B
        // [1, 8], [3, 5] -> [1, 2]
        assertSame(2, Interval.make(1, 8).complementTo(Interval.make(3, 5)).length);
        assertEquals(Interval.make(1, 2), Interval.make(1, 8).complementTo(Interval.make(3, 5))[0]);
        assertEquals(Interval.make(6, 8), Interval.make(1, 8).complementTo(Interval.make(3, 5))[1]);

        //   A       B
        // [4, 8], [3, 5] -> [1, 2]
        assertSame(1, Interval.make(6, 8).complementTo(Interval.make(3, 5)).length);
        assertEquals(Interval.make(6, 8), Interval.make(4, 8).complementTo(Interval.make(3, 5))[0]);

        //   A       B
        // [5, 8], [3, 5] -> [6, 8]
        assertSame(1, Interval.make(6, 8).complementTo(Interval.make(3, 5)).length);
        assertEquals(Interval.make(6, 8), Interval.make(4, 8).complementTo(Interval.make(3, 5))[0]);

        Interval source = Interval.make(1, 12);
        Interval target = Interval.make(5, 10);
        assertSame(2, source.complementTo(target).length);
        assertEquals(Interval.make(1, 4), source.complementTo(target)[0]);
        assertEquals(Interval.make(11, 12), source.complementTo(target)[1]);

        source = Interval.make(1913008934, 2038014354);
        target = Interval.make(1975836414, 2004456646);
        assertEquals(Interval.make(1913008934, 1975836414 - 1), source.complementTo(target)[0]);
        assertEquals(Interval.make(2004456646 + 1, 2038014354), source.complementTo(target)[1]);

        assertArrayEquals(
            new Interval[]{Interval.make(9, 12)},
            Interval.make(9, 12).complementTo(Interval.make(0, 8))
        );

        assertArrayEquals(
            new Interval[]{Interval.make(0, 8)},
            Interval.make(0, 8).complementTo(Interval.make(9, 12))
        );

        assertArrayEquals(
            new Interval[]{Interval.make(0, 7)},
            Interval.make(0, 8).complementTo(Interval.make(8, 12))
        );

        assertArrayEquals(
            new Interval[]{Interval.make(0, 3)},
            Interval.make(0, 8).complementTo(Interval.make(4, 12))
        );

        assertArrayEquals(
            new Interval[]{Interval.make(9, 12)},
            Interval.make(4, 12).complementTo(Interval.make(0, 8))
        );

        assertArrayEquals(
            new Interval[]{Interval.make(9, 12)},
            Interval.make(9, 12).complementTo(Interval.make(0, 8))
        );

        assertArrayEquals(
            new Interval[]{Interval.make(9, 12)},
            Interval.make(8, 12).complementTo(Interval.make(0, 8))
        );
    }

    @Test
    @DisplayName("intersectionWith")
    public void testIntersectionWith() {
        //   A       B
        // [1, 4], [1, 5] -> [1, 4]
        assertEquals(Interval.make(1, 4), Interval.make(1, 4).intersectionWith(Interval.make(1, 5)));

        //   A       B
        // [3, 4], [1, 5] ->  [3, 4]
        assertEquals(Interval.make(3, 4), Interval.make(3, 4).intersectionWith(Interval.make(1, 5)));

        //   A       B
        // [1, 4], [4, 5] -> [4, 4]
        assertEquals(Interval.make(4, 4), Interval.make(1, 4).intersectionWith(Interval.make(4, 5)));

        //   A       B
        // [1, 8], [3, 5] -> [3, 5]
        assertEquals(Interval.make(3, 5), Interval.make(1, 8).intersectionWith(Interval.make(3, 5)));

        //   A       B
        // [4, 8], [3, 5] -> [4, 5]
        assertEquals(Interval.make(4, 5), Interval.make(4, 8).intersectionWith(Interval.make(3, 5)));

        //   A       B
        // [5, 8], [3, 5] -> [5, 5]
        assertEquals(Interval.make(5, 5), Interval.make(5, 8).intersectionWith(Interval.make(3, 5)));

        //   A       B
        // [1, 2], [3, 5] -> null
        assertNull(Interval.make(1, 2).intersectionWith(Interval.make(3, 5)));

        //   A       B
        // [9, 15], [8, 5] -> null
        assertNull(Interval.make(9, 15).intersectionWith(Interval.make(5, 8)));

        assertEquals(Interval.make(5, 5), Interval.make(5, 5).intersectionWith(Interval.make(5, 5)));

        assertEquals(Interval.make(6, 6), Interval.make(5, 6).intersectionWith(Interval.make(6, 7)));

        assertEquals(Interval.make(8, 9), Interval.make(8, 10).intersectionWith(Interval.make(6, 9)));

        assertEquals(Interval.make(8, 10), Interval.make(1, 12).intersectionWith(Interval.make(8, 10)));

        assertEquals(Interval.make(8, 10), Interval.make(8, 10).intersectionWith(Interval.make(1, 12)));

    }


}
