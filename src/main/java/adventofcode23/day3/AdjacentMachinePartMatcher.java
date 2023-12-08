package adventofcode23.day3;

import adventofcode23.lib.ParserResult;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AdjacentMachinePartMatcher {

    List<ParserResult> machineParts;
    List<ParserResult> symbols;

    public AdjacentMachinePartMatcher(List<ParserResult> machineParts, List<ParserResult> symbols) {
        this.machineParts = machineParts;
        this.symbols = symbols;
    }

    public long sumParts() {

        long sum = 0;

        System.out.println(machineParts.size() + " " +  symbols.size());

        for (int line = 0; line < machineParts.size(); line++) {

            ParserResult asterisks = symbols.get(line);
            List<TokenInformation> asteriskList = (List<TokenInformation>) asterisks.getValue();

            for (TokenInformation asteriskInfo: asteriskList) {

                List<TokenInformation> current = getAdjacentNumbersForSameLine(asteriskInfo, machineParts);
                List<TokenInformation> next = getAdjacentNumbersForNextLine(asteriskInfo, machineParts);
                List<TokenInformation> previous = getAdjacentNumbersForPreviousLine(asteriskInfo, machineParts);

                System.out.println(":: adjacents: " + previous.size() + " " + current.size()+ " " + next.size());

                if (current.size() + next.size() + previous.size() != 2) {
                    System.out.println(" -- too many adjacents for " + asteriskInfo);
                    continue;
                }

                sum += multiply(current) * multiply(next) * multiply(previous);

            }
        }



        return sum;
    }


    private int multiply (List<TokenInformation> numbers) {
        int product = 1;
        for (TokenInformation number: numbers) {
            product *= Integer.parseInt(number.token);
        }

        return product;
    }


    public List<TokenInformation> getAdjacentNumbersForSameLine(
        final TokenInformation asterisk,
        final List<ParserResult> machineParts
    ) {
        int line = asterisk.line;

        List<TokenInformation> current =  (List<TokenInformation>) machineParts.get(line).getValue();

        List<TokenInformation> adjacents = findAdjacents(asterisk, current);

        if (adjacents.size() > 2) {
            throw new RuntimeException("unexpected adjacent count for " + asterisk);
        }

        return adjacents;
    }

    public List<TokenInformation> getAdjacentNumbersForNextLine(
        final TokenInformation asterisk,
        final List<ParserResult> machineParts
    ) {
        int line = asterisk.line;

        List<TokenInformation> adjacents = new ArrayList<>();

        if (line + 1 == machineParts.size()) {
            // no next line, return empty list
            return adjacents;

        }
        List<TokenInformation> next = (List<TokenInformation>) machineParts.get(line + 1).getValue();

        adjacents = findAdjacents(asterisk, next);

        if (adjacents.size() > 2) {
            throw new RuntimeException("unexpected adjacent count for " + asterisk);
        }

        return adjacents;
    }


    public List<TokenInformation> getAdjacentNumbersForPreviousLine(
        final TokenInformation asterisk,
        final List<ParserResult> machineParts
    ) {
        int line = asterisk.line;

        List<TokenInformation> adjacents = new ArrayList<>();

        if (line == 0) {
            // no previous line, return empty list
            return adjacents;
        }

        List<TokenInformation> previous =  (List<TokenInformation>) machineParts.get(line - 1).getValue();

        adjacents = findAdjacents(asterisk, previous);

        if (adjacents.size() > 2) {
            throw new RuntimeException("unexpected adjacent count for " + asterisk);
        }

        return adjacents;
    }


    private List<TokenInformation> findAdjacents(
        final TokenInformation asterisk,
        final List<TokenInformation> next
    ) {
        List<TokenInformation> adjacents = new ArrayList<>();
        int position = asterisk.position;

        for (int i = 0; i < next.size(); i++) {
            TokenInformation number = next.get(i);
            if (number.position + number.token.length() == position
                || number.position - 1  == position
                || (number.position <= position && number.position + number.token.length() >= position)
            ) {
                System.out.println("✅ adjacent number found for: " + asterisk + " " + number);
                adjacents.add(number);
            }
        }

        return adjacents;
    }

}
