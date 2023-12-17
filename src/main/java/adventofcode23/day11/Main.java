package adventofcode23.day11;

import adventofcode23.lib.ResourceReader;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day11/input.txt";
        GalaxyParser parser = new GalaxyParser();
        reader.parseContents(fileName, parser);

        System.out.println("The sum of the shortest path between every pair of galaxies is " + parser.computePaths());

        parser = new GalaxyParser(1_000_000);
        reader.parseContents(fileName, parser);

        System.out.println("The sum of the shortest path between every pair of galaxies is with a growth factor of 1.000.000 is " + parser.computePaths());

    }
}
