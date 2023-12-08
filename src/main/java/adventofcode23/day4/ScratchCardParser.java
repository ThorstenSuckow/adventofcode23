package adventofcode23.day4;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.SymbolParser;
import adventofcode23.lib.TokenInformation;

import java.util.Arrays;
import java.util.List;

public class ScratchCardParser extends Parser {

    @Override
    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        // [Card 1]: [1 2 3 4 5 6 | 8 1 3 4 5 2]
        String[] parts = line.split(":");

        // Game [1]
        String[] tmp = parts[0].split(" ");
        String id = tmp[tmp.length - 1].trim();

        // [1 2 3 4 5 6] | [8 1 3 4 5 2]
        String[] numberParts = parts[1].split("\\|");

        // [1 2 3 4 5 6] | [8 1 3 4 5 2]
        Integer[] winningNumbers = getNumbersFrom(numberParts[0], lineIndex);
        Integer[] elfNumbers = getNumbersFrom(numberParts[1], lineIndex);

        System.out.println("[" + lineIndex + "] winning numbers: " + Arrays.toString(winningNumbers));
        System.out.println("[" + lineIndex + "] elf numbers: " + Arrays.toString(elfNumbers));

        res.setValue(0);

        int points = -1;

        for (int i = 0; i < winningNumbers.length; i++) {
            for (int j = 0; j < elfNumbers.length; j++) {
                if (elfNumbers[j] == winningNumbers[i]) {
                    points++;
                }
            }
        }

        if (points > -1) {
            res.setValue((int) Math.pow(2, points));
        }

        return res;
    }


    private Integer[] getNumbersFrom(final String numberLine, final int lineIndex) {
        System.out.println(numberLine);
        SymbolParser p = new SymbolParser("([0-9]+)");
        ParserResult res = p.parseLine(numberLine, lineIndex);

        List<TokenInformation> list = (List<TokenInformation>) res.getValue();

        return list.stream().map(item -> Integer.parseInt(item.token)).toArray(Integer[]::new);
    }
}
