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

    String[] graph ={"soil", "fertilizer", "water", "light", "temperature",
                    "humidity", "location"};

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


    public Long[] getSeeds() {
        return seedsToPlant;
    }

    public Long getMinFor(String key) {

        Long[] seeds = getSeeds();

        Long min = Long.MAX_VALUE;
        for (int i = 0; i < seeds.length; i++) {
            min = Math.min(min, getValueFor(seeds[i], key));
        }

        return min;
    }

    /**
     *
     * @param type any of soil, fertilizer, water, light, temperature,
     *             humidity, location
     * @return
     */
    public Long getMapping (Long id, String type) {

        String key;
        Long mapping = id;

        switch (type) {
            case "soil":
                key = "seed-to-soil";
                break;
            case "fertilizer":
                key = "soil-to-fertilizer";
                break;
            case "water":
                key = "fertilizer-to-water";
                break;
            case "light":
                key = "water-to-light";
                break;
            case "temperature":
                key = "light-to-temperature";
                break;
            case "humidity":
                key = "temperature-to-humidity";
                break;
            case "location":
                key = "humidity-to-location";
                break;
            default:
                System.out.println(type);
                throw new IllegalArgumentException();


        }

        for (Map.Entry<String, List<Range>> entry: sections.entrySet()) {
            if (!entry.getKey().equals(key)) {
                continue;
            }

            List<Range> rangeList = entry.getValue();

            for (Range r: rangeList) {

                for (Long i = 0L; i < r.rangeLength; i++) {
                    Long source = r.sourceRangeStart + i;
                    Long destination = r.destinationRangeStart + i;
                    if (source == id) {
                        return destination;
                    }
                }
            }
        }

        return mapping;
    }

    public Long getValueFor(Long id, String key) {

        Long value = id;

        for (int i = 0; i < graph.length; i++) {

            value = getMapping(value, graph[i]);

            if (graph[i] == key) {
                break;
            }
        }

        return value;
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
