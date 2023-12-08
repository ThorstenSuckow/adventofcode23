package adventofcode23.day4;

import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();

        List<ParserResult> results = reader.parseContents("input/day4/input.txt", new ScratchCardParser());

        int sum = 0;
        for (ParserResult result : results) {
            sum += (Integer) result.getValue();
        }

        System.out.println("The sum of all scratchcard-points is: " + sum);



    }

}
