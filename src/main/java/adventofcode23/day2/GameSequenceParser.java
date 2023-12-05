package adventofcode23.day2;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import javax.print.attribute.IntegerSyntax;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class GameSequenceParser extends Parser {

    public ParserResult parseLine(String line, int index) {

        ParserResult res = new ParserResult();

        // [Game 1]: [3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green]
        String[] parts = line.split(":");

        // Game [1]
        String id = parts[0].split(" ")[1].trim();

        // [3 blue, 4 red];[ 1 red, 2 green, 6 blue]; [2 green]
        String[] games = parts[1].split(";");

        boolean isValid = true;

        sequence:{
            for (String sequence: games) {

                // [3 blue], [4 red]
                String[] items = sequence.trim().split(",");

                for (String entry: items) {
                    entry = entry.trim();
                    String color = entry.split(" ")[1];
                    String count = entry.split(" ")[0];

                    isValid = isValid(count, color);

                    if (!isValid) {
                        break sequence;
                    }
                }
            }
        }

        if (!isValid) {
            res.setValue(0);
        } else {
            res.setValue(Integer.valueOf(id));
        }


        return res;
    }

    private boolean isValid(final String count, final String color) {

        //  12 red cubes, 13 green cubes, and 14 blue cubes
        switch (color) {

            case "red":
                return Integer.parseInt(count) <= 12;
            case "green":
                return Integer.parseInt(count) <= 13;
            case "blue":
                return Integer.parseInt(count) <= 14;

        }

        return false;

    }


}
