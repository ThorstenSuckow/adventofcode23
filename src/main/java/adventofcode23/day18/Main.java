package adventofcode23.day18;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day18/input.txt";
        DiggerParser parser;
        parser = new DiggerParser();

        List<ParserResult> results = reader.parseContents(fileName, parser);

        System.out.println("The dig plan can hold a total of  " + results.get(0).getValue() + " cubic meters");


    }

}
