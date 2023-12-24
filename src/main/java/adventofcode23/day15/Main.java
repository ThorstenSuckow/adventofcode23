package adventofcode23.day15;

import adventofcode23.lib.ResourceReader;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day15/input.txt";
        HashParser parser;
        parser = new HashParser();

        System.out.println("The sum of the steps of the initialization sequence is "
                + reader.parseContents(fileName, parser).get(0).getValue());

        long sum = parser.processLenses();
        System.out.println("The checksum of the lenses is " + sum);
    }
}
