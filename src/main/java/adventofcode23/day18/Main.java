package adventofcode23.day18;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day18/input.txt";
        DiggerParser parser;
        List<ParserResult> results;

        parser = new DiggerParser();
        results = reader.parseContents(fileName, parser);
        double sum = (double) results.get(0).getValue();
        System.out.println("The dig plan can hold a total of " +((int) sum) + " cubic meters.");

        parser = new DiggerParser(true);
        results = reader.parseContents(fileName, parser);
        sum = (double) results.get(0).getValue();

        System.out.println("The lagoon can hold a total of " +((long) sum) + " cubic meters of lava.");
    }

}
