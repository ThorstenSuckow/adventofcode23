package adventofcode23.day1;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import javax.print.attribute.IntegerSyntax;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CalibrationValueParser extends Parser {

    public ParserResult parseLine(String line) {

        ParserResult res = new ParserResult();

        final String regex = "([0-9]){1}(.*([0-9]))?";
        final String string = line;

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        String first = "";
        String last = "";

        if (matcher.find()) {
            if (matcher.group(1) != null) {
                first = matcher.group(1);
            }
            if (matcher.group(3) != null) {
                last = matcher.group(3);
            } else {
                last = first;
            }

            res.setValue(Integer.valueOf(first + last));
        } else {
            res.setValue(0);
        }


        return res;
    }


}
