package adventofcode23.day7;

import adventofcode23.lib.ResourceReader;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();

        String fileName = "input/day7/input.txt";

        Hand.USE_JOKER = false;
        HandParser parser = new HandParser();
        reader.parseContents(fileName, parser);

        long sum = parser.computeTotalWinnings();

        System.out.println("The total winnings are " + sum);

        Hand.USE_JOKER = true;
        parser = new HandParser();
        reader.parseContents(fileName, parser);

        sum = parser.computeTotalWinnings();
        System.out.println("The total winnings (w/ Joker-rule) are " + sum);

    }

}
