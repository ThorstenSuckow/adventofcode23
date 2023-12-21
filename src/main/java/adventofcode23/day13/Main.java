package adventofcode23.day13;

import adventofcode23.day12.RecordParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day13/input.txt";
        MapParser parser;
        parser = new MapParser();

        List<ParserResult> result = reader.parseContents(fileName, parser);


        System.out.println("Summarizing all notes produces " + result.get(0).getValue());

    }
}
