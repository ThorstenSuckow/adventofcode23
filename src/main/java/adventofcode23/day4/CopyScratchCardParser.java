package adventofcode23.day4;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.SymbolParser;
import adventofcode23.lib.TokenInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyScratchCardParser extends Parser {


    HashMap<Integer, Integer> copies = new HashMap<>();

    public void setAmountOfCards(int cardLength) {
        for (int i = 1; i <= cardLength; i++) {
            copies.put(i, 1);;
        }
    }

    /**
     * Returns the result with the sum of all copied cards when lineIndex lines
     * have been parsed.
     * Resulting sum for all cards will always be in the most recent ParserResult returned.
     *
     * @param line
     * @param lineIndex
     * @return
     */
    @Override
    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        // [Card 1]: [1 2 3 4 5 6 | 8 1 3 4 5 2]
        String[] parts = line.split(":");

        // Game [1]
        String[] tmp = parts[0].split(" ");
        Integer id = Integer.parseInt(tmp[tmp.length - 1].trim());


        // [1 2 3 4 5 6] | [8 1 3 4 5 2]
        String[] numberParts = parts[1].split("\\|");

        // [1 2 3 4 5 6] | [8 1 3 4 5 2]
        Integer[] winningNumbers = getNumbersFrom(numberParts[0], lineIndex);
        Integer[] elfNumbers = getNumbersFrom(numberParts[1], lineIndex);

        res.setValue(0);

        int points = 0;

        for (int i = 0; i < winningNumbers.length; i++) {
            for (int j = 0; j < elfNumbers.length; j++) {
                if (elfNumbers[j] == winningNumbers[i]) {
                    points++;
                }
            }
        }


        if (points > 0) {
            System.out.println("Card " + id + " wins " + points + " cards ");

            for (int i = 0; i < points; i++) {
                int copyKey = id + i + 1;

                copies.put(copyKey, copies.get(copyKey) != null ? copies.get(copyKey) + (copies.get(id) * 1) : 1);
                System.out.println("-- [" + copyKey + "]" +  copies.get(copyKey));
            }
        }

        int sum = 0;
        //System.out.println("----------");
        for (Map.Entry<Integer, Integer> entry: copies.entrySet()) {
          //  System.out.println(entry.getKey() + " " + entry.getValue());
            sum += entry.getValue();
        }
        res.setValue(sum);

       // System.out.println("----------");

        return res;
    }


    private Integer[] getNumbersFrom(final String numberLine, final int lineIndex) {
        SymbolParser p = new SymbolParser("([0-9]+)");
        ParserResult res = p.parseLine(numberLine, lineIndex);

        List<TokenInformation> list = (List<TokenInformation>) res.getValue();

        return list.stream().map(item -> Integer.parseInt(item.token)).toArray(Integer[]::new);
    }
}
