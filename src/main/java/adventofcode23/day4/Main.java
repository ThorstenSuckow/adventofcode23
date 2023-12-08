package adventofcode23.day4;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fileName = "input/day4/input.txt";

        ResourceReader reader = new ResourceReader();

        List<ParserResult> results = reader.parseContents(fileName, new ScratchCardParser());

        int sum = 0;
        for (ParserResult result : results) {
            sum += (Integer) result.getValue();
        }

        System.out.println("The sum of all scratchcard-points is: " + sum);

        CopyScratchCardParser copyScratchCardParser = new CopyScratchCardParser();
        System.out.println(reader.getLineCount(fileName));
        copyScratchCardParser.setAmountOfCards(reader.getLineCount(fileName));
        results = reader.parseContents(fileName, copyScratchCardParser);

        System.out.println("The sum of original and copied scratchcards is: " + results.getLast().getValue());

    }

}
