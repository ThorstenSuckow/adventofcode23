package adventofcode23.day8;

import adventofcode23.lib.ResourceReader;

public class Main {

    public static void main(String[] args) {
        ResourceReader reader = new ResourceReader();
        String fileName = "input/day8/input.txt";

        MapParser parser = new MapParser("AAA");
        reader.parseContents(fileName, parser);

        int sum = parser.navigate();
        System.out.println("Steps required to reach ZZZ: " + sum);
    }

}
