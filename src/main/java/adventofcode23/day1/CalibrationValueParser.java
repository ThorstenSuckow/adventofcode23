package adventofcode23.day1;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import javax.print.attribute.IntegerSyntax;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CalibrationValueParser extends Parser {

    public ParserResult parseLine(String line) {

        ParserResult res = new ParserResult();

        final String regex = "([0-9])";
        final String string = line;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        String first = "";
        String last = "";

        while (matcher.find()) {
            if (first != "") {
                last = matcher.group(0);
            }

            if (first == "") {
                first = matcher.group(0);
            }
        }

        res.setValue(Integer.valueOf(first + (last == null ? "" : last)));
        return res;
    }


}
