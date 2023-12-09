package adventofcode23.day5;

import adventofcode23.day4.AlmanacParser;
import adventofcode23.day4.CopyScratchCardParser;
import adventofcode23.day4.ScratchCardParser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fileName = "input/day5/testinput.txt";

        ResourceReader reader = new ResourceReader();

        AlmanacParser almanacParser = new AlmanacParser();
        reader.parseContents(fileName, almanacParser);

        Long min = almanacParser.getMinLocationFromSeedToSoilMap();
       // for (ParserResult result : results) {
       //     sum += (Integer) result.getValue();
       // }

        System.out.println("The lowest location number that corresponds to any of the initial seed numbers is: " + min);


    }

}
