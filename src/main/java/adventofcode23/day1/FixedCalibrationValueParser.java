package adventofcode23.day1;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class FixedCalibrationValueParser extends Parser {

    public ParserResult parseLine(final String line) {

        ParserResult res = new ParserResult();

        final String regex = "([0-9]|one|two|three|four|five|six|seven|eight|nine){1}"+
                            "(.*([0-9]|one|two|three|four|five|six|seven|eight|nine))?";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(line);

        String first = "";
        String last = "";

        if (matcher.find()) {
            if (matcher.group(1) != null) {
                first = toNumberString(matcher.group(1));
            }
            if (matcher.group(3) != null) {
                last = toNumberString(matcher.group(3));
            } else {
                last = first;
            }

            res.setValue(Integer.valueOf(first + last));
        } else {
            res.setValue(0);
        }

        return res;
    }

    private String toNumberString(String value) {

        switch (value) {
            case "one":
                return "1";
            case "two":
                return "2";
            case "three":
                return "3";
            case "four":
                return "4";
            case "five":
                return "5";
            case "six":
                return "6";
            case "seven":
                return "7";
            case "eight":
                return "8";
            case "nine":
                return "9";
        }

        return value;
    }

}
