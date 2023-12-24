package adventofcode23.day16;

import adventofcode23.lib.ResourceReader;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day16/input.txt";
        RayParser parser;
        parser = new RayParser();

        System.out.println("The sum of tiles ending up energized is "
                + reader.parseContents(fileName, parser).get(0).getValue());

    }
}
