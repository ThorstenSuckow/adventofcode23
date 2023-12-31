package adventofcode23.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser extends Parser {

    String regex = "([^0-9,.])";

    public SymbolParser() {

    }

    public SymbolParser(String regex) {
        this.regex = regex;
    }

    public ParserResult parseLine(final String line, final int lineIndex) {

        ParserResult res = new ParserResult();

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
