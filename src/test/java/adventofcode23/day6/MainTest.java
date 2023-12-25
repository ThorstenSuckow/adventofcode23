package adventofcode23.day6;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

   @Test
   @DisplayName("getDistanceTravelled")
   public void testGetDistanceTravelled() {

       Main main = new Main();


       assertSame(0L, main.getDistanceTravelled(0, 7));
       assertSame(6L, main.getDistanceTravelled(1, 7));
       assertSame(10L, main.getDistanceTravelled(2, 7));
       assertSame(12L, main.getDistanceTravelled(3, 7));
       assertSame(12L, main.getDistanceTravelled(4, 7));
       assertSame(10L, main.getDistanceTravelled(5, 7));
       assertSame(6L, main.getDistanceTravelled(6, 7));
       assertSame(0L, main.getDistanceTravelled(7, 7));
   }

    @Test
    @DisplayName("getPersonalBests")
    public void testGetPersonalBests() {

        Main main = new Main();

        long[] record = new long[]{7L, 9L};

        assertArrayEquals(new long[]{2L, 3L, 4L, 5L}, main.getStartupTimesToBeatRecord(record));
    }

    @Test
    @DisplayName("getMultipliedBests")
    public void testGetMultipliedBests() {

        Main main = new Main();

        long[][] record = new long[][]{new long[]{7L, 9L}};
        assertSame(4L, main.getMultipliedBests(record));

        record = new long[][]{new long[]{15L, 40L}};
        assertSame(8L, main.getMultipliedBests(record));

        record = new long[][]{new long[]{30L, 200L}};
        assertSame(9L, main.getMultipliedBests(record));

        record = new long[][]{
            new long[]{7L, 9L},
            new long[]{15L, 40L},
            new long[]{30L, 200L}
        };

        assertEquals(288L, main.getMultipliedBests(record));
    }

}
