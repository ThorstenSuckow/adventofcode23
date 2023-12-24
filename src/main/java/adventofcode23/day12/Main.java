package adventofcode23.day12;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day12/input.txt";
        RecordParser parser = new RecordParser();

        List<ParserResult> results = reader.parseContents(fileName, parser);

        long sum = 0;
        for (ParserResult res: results) {
            sum += (long) res.getValue();
        }

        System.out.println("The sum of the counts is " + sum);

    }
}
