package adventofcode23.day9;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SensorParser extends Parser {



    List<Long[]> lines = new ArrayList<>();

    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if(line.trim().equals("")) {
            return res;
        }

        String[] parts = line.trim().split(" ");

        Long[] numbers = Arrays.stream(parts).map(Long::valueOf).toArray(Long[]::new);

        lines.add(numbers);
        System.out.println("added " + Arrays.toString(numbers));

        return res;
    }


    public long compute() {

        long sum = 0;
        for (int i = 0; i < lines.size(); i++) {

            Long[] line = lines.get(i);

            List<Long[]> diffs = new ArrayList<>();
            Long[] diff = findDifference(line);

            log(line);

            // start nesting diffs
            diffs.add(diff);
            while (!allZeroes(diff)) {
                diff = findDifference(diff);
                diffs.add(diff);
            }
            log(diffs);

            int pos = diffs.size() - 1;
            int add = 0;
            while (pos >= 0) {
                diff = diffs.get(pos--);
                add += diff[diff.length - 1];
            }
            long lineResult = line[line.length - 1] + add;
            System.out.println("Result for line " + i + " is " + lineResult);
            sum += lineResult;
        }

        return sum;
    }


    public long computePartTwo() {

        long sum = 0;
        for (int i = 0; i < lines.size(); i++) {

            Long[] line = lines.get(i);

            List<Long[]> diffs = new ArrayList<>();
            Long[] diff = findDifference(line);

            log(line);

            // start nesting diffs
            diffs.add(diff);
            while (!allZeroes(diff)) {
                diff = findDifference(diff);
                diffs.add(diff);
            }
            log(diffs);

            int pos = diffs.size() - 2;
            long add = 0;
            while (pos >= 0) {
                diff = diffs.get(pos--);
                add = diff[0] - add;
            }
            long lineResult = line[0] - add;
            System.out.println("Result for line " + i + " is " + lineResult);
            sum += lineResult;
        }

        return sum;
    }


    public Long[] findDifference(Long[] line) {

        Long[] res = new Long[line.length - 1];
        for (int i = 1; i < line.length; i++) {
            long result = line[i] - line[i - 1];
            res[i-1] = result;
        }

        return res;
    }

    public boolean allZeroes(Long[] line) {
        return Arrays.stream(line).noneMatch(e -> e != 0);
    }


    public void log(List<Long[]> list) {
        for (Long[] numbers: list) {
            System.out.println(" - " + Arrays.toString(numbers));
        }
    }

    public void log(Long[] numbers) {
        System.out.println(Arrays.toString(numbers));
    }
}
