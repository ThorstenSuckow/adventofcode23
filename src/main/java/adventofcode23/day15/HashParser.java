package adventofcode23.day15;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class HashParser extends Parser {

    List<String> steps = new ArrayList<>();


    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        line = line.trim();

        if (line.trim().isEmpty()) {
            return res;
        }


        String[] parts = line.split(",");

        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                steps.add(parts[i]);
            }
        }

        return res;
    }

    @Override
    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        List<ParserResult> results = new ArrayList<>();

        long sum = 0;

        for (String step: steps) {
            sum += valueOf(step);
        }

        ParserResult res = new ParserResult();
        res.setValue(sum);
        results.add(res);

        return results;
    }

    public long valueOf(String step) {
        long currentValue = 0;
        for (int i = 0; i < step.length(); i++) {

            char c = step.charAt(i);

            if (c >= 128) {
                throw new IllegalArgumentException("no ASCII char");
            }

            currentValue += c;
            currentValue *= 17;
            currentValue = currentValue % 256;
        }

        return currentValue;
    }


    public void log(List<String> rows) {

        System.out.println(rows);
    }
}