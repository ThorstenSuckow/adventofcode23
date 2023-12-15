package adventofcode23.day9;


import adventofcode23.lib.ResourceReader;



public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day9/input.txt";

        SensorParser parser = new SensorParser();
        reader.parseContents(fileName, parser);

        System.out.println("The sum of the extrapolated values is " + parser.compute());

        System.out.println("The sum of the extrapolated values for part two is " + parser.computePartTwo());

    }
}
