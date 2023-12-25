package adventofcode23.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedCalibrationValueParserTest {


    @Test
    @DisplayName("parseLine")
    public void testParseLine() {

        FixedCalibrationValueParser parser = new FixedCalibrationValueParser();

        HashMap<String, Integer> tests = new HashMap<>();
        tests.put("two1nine", 29);
        tests.put("eightwothree", 83);
        tests.put("abcone2threexyz", 13);
        tests.put("xtwone3four", 24);
        tests.put("4nineeightseven2", 42);
        tests.put("zoneight234", 14);
        tests.put("7pqrstsixteen", 76);

        int i = 0;
        for (HashMap.Entry<String, Integer> set: tests.entrySet()) {
            assertEquals(set.getValue(), (Integer)parser.parseLine(set.getKey(), i++).getValue());
        }



    }

}
