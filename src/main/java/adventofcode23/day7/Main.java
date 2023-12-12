package adventofcode23.day7;

import adventofcode23.lib.ResourceReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) {
        ResourceReader reader = new ResourceReader();

        String fileName = "input/day7/input.txt";

        HandParser parser = new HandParser();
        reader.parseContents(fileName, parser);

        long sum = parser.computeTotalWinnings();

        System.out.println("The total winnings are " + sum);

    }

}
