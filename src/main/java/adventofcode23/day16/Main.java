package adventofcode23.day16;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day16/input.txt";
        RayParser parser;
        parser = new RayParser();

        List<ParserResult> results = reader.parseContents(fileName, parser);


        System.out.println("The sum of tiles ending up energized is "
                + results.get(0).getValue());


        System.out.println("The largest sum of energized tiles in the alternative configuration is " + parser.getMax());

    }
}
