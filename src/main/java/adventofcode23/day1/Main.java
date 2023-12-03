package adventofcode23.day1;


import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();

        List<ParserResult> results = reader.parseContents("input/day1/input.txt", new CalibrationValueParser());

        int sum = 0;
        for (ParserResult result: results) {
            sum += (Integer)result.getValue();
        }

        System.out.println("The sum of all calibration values is: " + sum);

        // part 2
        results = reader.parseContents("input/day1/input.txt", new FixedCalibrationValueParser());

        sum = 0;
        for (ParserResult result: results) {
            sum += (Integer)result.getValue();
        }

        System.out.println("The correct sum of all calibration values is: " + sum);
    }

}
