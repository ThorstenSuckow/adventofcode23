package adventofcode23.day10;

import adventofcode23.lib.ResourceReader;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day10/input.txt";
        MazeParser parser = new MazeParser();
        reader.parseContents(fileName, parser);

        parser = new MazeParser();
        reader.parseContents(fileName, parser);

        System.out.println(
                "Steps along the loop required to get from the starting " +
                "position to the point farthest from the starting position: " +
                parser.compute()
        );

    }
}
