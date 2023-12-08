package adventofcode23.day3;


import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;
import adventofcode23.lib.SymbolParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();

        // part one
        List<ParserResult> machineParts = reader.parseContents("input/day3/input.txt", new MachinePartNumberParser());
        List<ParserResult> symbols = reader.parseContents("input/day3/input.txt", new SymbolParser());

        MachinePartRectMatcher rectMatcher = new MachinePartRectMatcher(machineParts, symbols);

        System.out.println("The sum of all part numbers is: " + rectMatcher.sumParts());

        // part one
        machineParts = reader.parseContents("input/day3/input.txt", new MachinePartNumberParser());
        symbols = reader.parseContents("input/day3/input.txt", new SymbolParser("(\\*)"));

        AdjacentMachinePartMatcher adjacentMatcher = new AdjacentMachinePartMatcher(machineParts, symbols);

        System.out.println("\uD83D\uDCAF The sum of all adjacent part numbers is: " + adjacentMatcher.sumParts());

    }

}
