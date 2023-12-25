package adventofcode23.lib;

import java.util.List;

public abstract class Parser {


    public abstract ParserResult parseLine(String line, int lineIndex);


    /**
     * Hook for any Post processing.
     * Gets called once all lines where parsed.
     */
    public List<ParserResult> postProcess(List<ParserResult> parserResults) {
        return parserResults;
    }


}
