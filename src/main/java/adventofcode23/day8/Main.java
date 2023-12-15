package adventofcode23.day8;

import adventofcode23.lib.ResourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ResourceReader reader = new ResourceReader();
        String fileName = "input/day8/input.txt";

        // Part one
        MapParser parser = new MapParser("AAA", "ZZZ");
        reader.parseContents(fileName, parser);

        int sum = parser.navigate();
        System.out.println("Steps required to reach ZZZ: " + sum);

        // Part two
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


        System.out.println(
            "Steps required to be on nodes that end on Z: "
            + lcm(results.stream().mapToLong(i->i).toArray())
        );
    }

    private static long gcd(long x, long y)
    {
        while (y > 0) {
            long tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }

    private static long lcm(long x, long y) {
        return x * (y / gcd(x, y));
    }

    private static long lcm(long[] numbers) {
        long res = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            res = lcm(res, numbers[i]);
        }
        return res;
    }
}
