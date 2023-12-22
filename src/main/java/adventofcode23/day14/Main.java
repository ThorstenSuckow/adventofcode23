package adventofcode23.day14;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day14/input.txt";
        TiltParser parser;
        parser = new TiltParser();

        reader.parseContents(fileName, parser);
        System.out.println("The total load on the north support beams is " + parser.tilt());



    }
}
