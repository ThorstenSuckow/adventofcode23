package adventofcode23.day3;


import adventofcode23.lib.ParserResult;
import adventofcode23.lib.ResourceReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();

        List<ParserResult> machineParts = reader.parseContents("input/day3/input.txt", new MachinePartNumberParser());
        List<ParserResult> symbols = reader.parseContents("input/day3/input.txt", new SymbolParser());

        MachinePartRectMatcher rectMatcher = new MachinePartRectMatcher(machineParts, symbols);

        System.out.println("The sum of all part numbers is: " + rectMatcher.sumParts());

    }

}
