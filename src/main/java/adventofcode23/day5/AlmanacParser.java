package adventofcode23.day4;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.SymbolParser;
import adventofcode23.lib.TokenInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlmanacParser extends Parser {

    class Range {

        public Long destinationRangeStart;
        public Long sourceRangeStart;
        public Long rangeLength;

        public Range (
            final Long destinationRangeStart,
            final Long sourceRangeStart,
            final Long rangeLength
        ) {

            this.destinationRangeStart = destinationRangeStart;
            this.sourceRangeStart = sourceRangeStart;
            this.rangeLength = rangeLength;
        }

        public String toString() {
            return  destinationRangeStart + " " + sourceRangeStart + " " +  rangeLength;
        }

    }

    String currentGroup;

    Long[] seedsToPlant;

    HashMap<String, List<Range>> sections = new HashMap<>();

    @Override
    public ParserResult parseLine(final String line, final int lineIndex) {

        ParserResult res = new ParserResult();

        if (line.equals("")) {
            return res;
        }

        String section = getSection(line, lineIndex);

        if (section != null) {
            if (!section.equals("seeds")) {
              //  System.out.println("found section: '" + section + "'");
                currentGroup = section;
                sections.put(section, new ArrayList<>());
            } else if (section.equals("seeds")) {
                seedsToPlant = getNumbers(line, lineIndex);
            }
        } else {

            Range r = getRange(line, lineIndex);
           // System.out.println("-- added range " + r);
            sections.get(currentGroup).add(r);
        }



        return res;
    }

    public HashMap<Long, Long> getSeedToSoilMap () {

        HashMap<Long, Long> map = new HashMap<>();

        for (Map.Entry<String, List<Range>> entry: sections.entrySet()) {

            if (!entry.getKey().equals("seed-to-soil")) {
                continue;
            }

            List<Range> rangeList = entry.getValue();

            for (Range r: rangeList) {

                System.out.println("--checking range " + r);

                for (Long i = 0L; i < r.rangeLength; i++) {
                    Long seedNumber = r.sourceRangeStart + i;
                    Long soilNumber = r.destinationRangeStart + i;
                    //if (Arrays.asList(seedsToPlant).contains(seedNumber)) {
                        map.put(seedNumber, soilNumber);
                    //}
                }
            }
        }

        return map;
    }

    public Long getMinLocationFromSeedToSoilMap() {

        HashMap<Long, Long> map = getSeedToSoilMap();

        Long min = Long.MAX_VALUE;
        for (Map.Entry<Long, Long> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            min = Math.min(min, entry.getValue());
        }

        return min;
    }


    private String getSection(final String line, final int lineIndex) {
        SymbolParser p = new SymbolParser("(^[a-z-]+)");
        ParserResult res = p.parseLine(line, lineIndex);
        List<TokenInformation> list = (List<TokenInformation>) res.getValue();

        return list.size() == 1 ? list.get(0).token : null;
    }

    private Range getRange(final String line, final int lineIndex) {

        Long[] numbers = getNumbers(line, lineIndex);

        Range range = new Range(numbers[0], numbers[1], numbers[2]);

        return range;
    }

    private Long[] getNumbers(final String line, final int lineIndex) {

        SymbolParser p = new SymbolParser("([0-9]+)");
        ParserResult res = p.parseLine(line, lineIndex);

        List<TokenInformation> list = (List<TokenInformation>) res.getValue();

        return list.stream().map(item -> Long.parseLong(item.token)).toArray(Long[]::new);

    }



}
