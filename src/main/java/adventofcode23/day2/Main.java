package adventofcode23.day2;


import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();

        List<ParserResult> results = reader.parseContents("input/day2/input.txt", new GameSequenceParser());

        int sum = 0;
        for (ParserResult result: results) {
            sum += (Integer)result.getValue();
        }

        System.out.println("The sum of all valid Game ids is: " + sum);

        results = reader.parseContents("input/day2/input.txt", new PoweredGameSequenceParser());

        sum = 0;
        for (ParserResult result: results) {
            sum += (Integer)result.getValue();
        }

        System.out.println("The sum of the power all Game ids is: " + sum);
    }

}
