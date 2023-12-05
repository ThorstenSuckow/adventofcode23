package adventofcode23.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalibrationValueParserTest {


    @Test
    @DisplayName("parseLine")
    public void testParseLine() {

        CalibrationValueParser parser = new CalibrationValueParser();

        HashMap<String, Integer> tests = new HashMap<>();
        tests.put("1abc2", 12);
        tests.put("pqr3stu8vwx", 38);
        tests.put("a1b2c3d4e5f", 15);
        tests.put("treb7uchet", 77);

        int i = 0;
        for (HashMap.Entry<String, Integer> set: tests.entrySet()) {

            assertEquals(set.getValue(), (Integer)parser.parseLine(set.getKey(), i++).getValue());
        }



    }

}
