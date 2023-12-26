package adventofcode23.day18;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class DiggerParser extends Parser{

    List<List<String>> rows = new ArrayList<>();

    List<String[]> directions = new ArrayList<>();

    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        line = line.trim();

        if (line.isEmpty()) {
            return res;
        }

        String[] parts = line.split(" ");

        String direction = parts[0];
        String steps = parts[1];

        if (rows.size() != lineIndex) {
            rows.add(new ArrayList<>());
        }

        directions.add(new String[]{direction, steps});

        return res;
    }

    public List<ParserResult> postProcess(List<ParserResult> res) {

        List<ParserResult> results = new ArrayList<>();

        int sum = 0;



        ParserResult sumResult = new ParserResult();
        sumResult.setValue(sum);
        results.add(sumResult);
        return results;
    }

}