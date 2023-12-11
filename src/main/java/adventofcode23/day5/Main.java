package adventofcode23.day5;

import adventofcode23.day4.AlmanacParser;
import adventofcode23.day4.CopyScratchCardParser;
import adventofcode23.day4.ScratchCardParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Long start;
        Long min;

        AlmanacParser almanacParser;
        ResourceReader reader = new ResourceReader();

        String fileName = "input/day5/input.txt";

        almanacParser = new AlmanacParser();
        reader.parseContents(fileName, almanacParser);
        min = almanacParser.getMinFromIntervals(almanacParser.findLocationIntervals());
        System.out.println("The lowest location number that corresponds to any of the initial seed numbers is: " + min);


        almanacParser = new AlmanacParser(true);
        start = System.currentTimeMillis();
        reader.parseContents(fileName, almanacParser);
        min = almanacParser.getMinFromIntervals(almanacParser.findLocationIntervals());
        System.out.println("The lowest location number that corresponds to the intervalled seed numbers is: " + min);
        System.out.println("(solution found in  " + (System.currentTimeMillis() - start) + "ms)");
    }

}
