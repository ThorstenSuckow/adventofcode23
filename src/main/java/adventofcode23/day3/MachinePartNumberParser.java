package adventofcode23.day3;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachinePartNumberParser extends Parser {



    public ParserResult parseLine(final String line, final int lineIndex) {

        ParserResult res = new ParserResult();

        final String regex = "([0-9]+)";
        final String string = line;

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);


        List<TokenInformation> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(new TokenInformation(matcher.group(0), lineIndex, matcher.start()));

        }

        res.setValue(list);

        return res;
    }


}
