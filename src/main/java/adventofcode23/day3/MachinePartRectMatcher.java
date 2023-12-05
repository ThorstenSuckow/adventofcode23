package adventofcode23.day3;

import adventofcode23.lib.ParserResult;

import java.util.List;
import java.util.Map;

public class MachinePartRectMatcher {

    List<ParserResult> machineParts;
    List<ParserResult> symbols;

    public MachinePartRectMatcher(List<ParserResult> machineParts, List<ParserResult> symbols) {
        this.machineParts = machineParts;
        this.symbols = symbols;
    }

    public int sumParts() {

        int sum = 0;
        for (ParserResult parts: machineParts) {

            List<TokenInformation> machinePartNumbers = (List<TokenInformation>)parts.getValue();

            for (TokenInformation tokenInfo: machinePartNumbers) {


                int position = tokenInfo.position;
                int line = tokenInfo.line;
                int parsedValue = -1;
                String numberString = tokenInfo.token;

                List<TokenInformation> lineBefore = null;
                List<TokenInformation> lineAfter = null;
                List<TokenInformation> currentLine = (List<TokenInformation>) symbols.get(line).getValue();;

                if (line > 0) {
                    lineBefore = (List<TokenInformation>) symbols.get(line - 1).getValue();
                }
                if (line + 1 < symbols.size()) {
                    lineAfter = (List<TokenInformation>) symbols.get(line + 1).getValue();
                }

                int length = position + numberString.length();

                if (lineBefore != null) {
                    for (TokenInformation symbolInfo: lineBefore) {
                        if (symbolInfo.position >= position - 1 && symbolInfo.position <= length) {
                            parsedValue = Integer.parseInt(numberString);
                        }
                    }
                }

                if (parsedValue == -1 && lineAfter != null) {
                    for (TokenInformation symbolInfo: lineAfter) {
                        if (symbolInfo.position >= position - 1 && symbolInfo.position <= length) {
                            parsedValue = Integer.parseInt(numberString);
                        }
                    }
                }

                if (parsedValue == -1) {
                    for (TokenInformation symbolInfo: currentLine) {
                        if (symbolInfo.position == position - 1 || symbolInfo.position == length) {
                            parsedValue = Integer.parseInt(numberString);
                        }
                    }
                }

                if (parsedValue != -1) {
                    sum += parsedValue;
                }
            }


        }
        return sum;
    }

}
