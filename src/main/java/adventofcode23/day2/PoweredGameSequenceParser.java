package adventofcode23.day2;

import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoweredGameSequenceParser extends GameSequenceParser {

    public ParserResult parseLine(String line) {

        ParserResult res = new ParserResult();

        // [Game 1]: [3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green]
        String[] parts = line.split(":");

        // Game [1]
        String id = parts[0].split(" ")[1].trim();

        // [3 blue, 4 red];[ 1 red, 2 green, 6 blue]; [2 green]
        String[] games = parts[1].split(";");

        HashMap<String, List<Integer>> colorToNumbers = new HashMap<>();

        for (String sequence: games) {

            // [3 blue], [4 red]
            String[] items = sequence.trim().split(",");

            for (String entry: items) {
                entry = entry.trim();
                String color = entry.split(" ")[1];
                String count = entry.split(" ")[0];


                colorToNumbers.computeIfAbsent(color, k -> new ArrayList<>());
                List<Integer> numbers = colorToNumbers.get(color);

                numbers.add(Integer.valueOf(count));
            }
        }

        int max = 1;
        for (HashMap.Entry<String, List<Integer>> set: colorToNumbers.entrySet()) {
            max *= set.getValue().stream().max(Integer::compare).get();
        }

        res.setValue(max);

        return res;
    }


}
