package adventofcode23.day6;


import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        long[][] records = new long[][]{
            new long[]{60L, 475L},
            new long[]{94L, 2138L},
            new long[]{78L, 1015L},
            new long[]{82L, 1650L}
        };

        Main main = new Main();

        System.out.println("The solution for part one of day 6 is " + main.getMultipliedBests(records));

        records = new long[][]{
            new long[]{60947882L, 475213810151650L}
        };

        System.out.println("The solution for part two of day 6 is " + main.getMultipliedBests(records));

    }


    public long getDistanceTravelled(long startupTime, long raceTime) {

        if (startupTime > raceTime) {
            throw new IllegalArgumentException();
        }

        long travelTime = raceTime - startupTime;
        long speed = startupTime * 1;
        long distance = travelTime * speed;
        return distance;
    }

    public long getMultipliedBests(long[][] records) {

        long product = 1;

        for (int a = 0; a < records.length; a++) {
            long[] bests = getStartupTimesToBeatRecord(records[a]);
            product *= bests.length;
        }

        return product;
    }

    public long[] getStartupTimesToBeatRecord(long[] record) {

        long raceTime = record[0];
        long recordTime = record[1];
        List<Long> bests = new ArrayList<>();

        for (int i = 0; i <= raceTime; i++) {
            if (getDistanceTravelled(i, raceTime) > recordTime) {
                bests.add((long) i);
            }
        }

        return bests.stream().mapToLong(item -> item).toArray();
    }



}
