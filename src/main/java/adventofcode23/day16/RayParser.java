package adventofcode23.day16;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class RayParser extends Parser {



    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        line = line.trim();

        if (line.trim().isEmpty()) {
            return res;
        }



        return res;
    }


    @Override
    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        List<ParserResult> results = new ArrayList<>();

        int sum = 0;


        ParserResult res = new ParserResult();
        res.setValue(sum);
        results.add(res);

        return results;
    }


}