package adventofcode23.day4;

import adventofcode23.lib.Parser;
import adventofcode23.lib.Interval;
import adventofcode23.lib.Range;
import adventofcode23.lib.ParserResult;
import adventofcode23.lib.SymbolParser;
import adventofcode23.lib.TokenInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AlmanacParser extends Parser {


    String[] graph ={"seeds", "soil", "fertilizer", "water", "light", "temperature",
                    "humidity", "location"};

    String currentGroup;

    boolean useIntervals;

    long[] seedsToPlant;

    HashMap<String, List<Range>> sections = new HashMap<>();

    public AlmanacParser() {
        this(false);
    }

    public AlmanacParser(boolean useIntervals) {
        this.useIntervals = useIntervals;
    }

    @Override
    public ParserResult parseLine(final String line, final int lineIndex) {

        ParserResult res = new ParserResult();

        if (line.equals("")) {
            return res;
        }

        String section = getSection(line, lineIndex);

        if (section != null) {
            if (!section.equals("seeds")) {
                currentGroup = section;
                sections.put(section, new ArrayList<>());
            } else if (section.equals("seeds")) {
                if (sections.get("seeds") != null) {
                    throw new RuntimeException();
                }
                sections.put("seeds", new ArrayList<>());
                seedsToPlant = getNumbers(line, lineIndex);
                if (useIntervals) {
                    for (int i = 0; i < seedsToPlant.length; i += 2) {
                        Range range = new Range(seedsToPlant[i], seedsToPlant[i], seedsToPlant[i + 1]);
                        sections.get("seeds").add(range);
                    }
                } else {
                    for (int i = 0; i < seedsToPlant.length; i++) {
                        Range range = new Range(seedsToPlant[i], seedsToPlant[i], 1);
                        sections.get("seeds").add(range);

                    }
                }
            }
        } else {
            Range r = getRange(line, lineIndex);
            sections.get(currentGroup).add(r);
        }

        return res;
    }

    public Long getMinFromIntervals(Interval[] intervals) {

        Long min = Long.MAX_VALUE;

        for (Interval interval: intervals) {
            min = Math.min(min, interval.getStart());
        }

        return min;
    }

    public Interval[] findLocationIntervals() {

        List<Range> seedList = sections.get("seeds");

        Interval[] intervals = new Interval[seedList.size()];
        int i = 0;
        for (Range range: seedList) {
            intervals[i++] = range.getDestinationInterval();
        }

        Interval[] res = getDestination(intervals, "location");

        return res;
    }

    public Interval[] getDestination(final Interval[] sourceIntervals, String key) {

        Interval[] sources = sourceIntervals;

        int i = 0;
        do {
            List<Interval> intervalList = new ArrayList<>();
            for (int j =  0; j < sources.length; j++) {
                Interval[] intervals = getDestinationIntervals(sources[j], graph[i]);
                for (Interval item: intervals) {
                    intervalList.add(item);
                }
            }

            sources = toIntervalArray(intervalList);

        } while (graph[i++] != key);


        return sources;//toIntervalArray(intervalList);
    }


    public Interval[] getDestinationIntervals(final Interval interval, String key) {

        List<Interval> destinationIntervals = new ArrayList<>();
        String resolvedKey = resolveKey(key);
        Interval[] complements = null;

        List<Range> rangeList = sections.get(resolvedKey);

        for (Range range: rangeList) {
            complements = interval.complementTo(range.getSourceInterval());
            Interval intersection = range.toDestinationInterval(interval.intersectionWith(range.getSourceInterval()));

            if (intersection != null) {
                destinationIntervals.add(intersection);
                if (complements != null) {

                    for (Interval complement: complements) {
                        Interval[] comp = getDestinationIntervals(complement, key);
                        // complements need to be checked for intersections
                        destinationIntervals.addAll(Arrays.asList(comp));
                    }
                }
                return toIntervalArray(destinationIntervals);
            }
        }


        return new Interval[]{interval};
    }

    public Interval[] toIntervalArray(List<Interval> list) {
        Interval[] res = new Interval[list.size()];
        int i = 0;
        for (Interval item: list) {
            res[i++] = item;
        }
        return res;
    }


    public long getMapping (long id, String type) {

        String key = resolveKey(type);

        Interval interval = Interval.make(id, id);

        for (Map.Entry<String, List<Range>> entry: sections.entrySet()) {
            if (!entry.getKey().equals(key)) {
                continue;
            }

            List<Range> rangeList = entry.getValue();

            for (Range range: rangeList) {
                if (interval.intersects(range.getSourceInterval())) {
                    return range.getDestinationeValue(id);
                }
            }
        }

        return id;
    }


// +-----------------------------------------------
// | Utils
// +-----------------------------------------------

    public long getDestinationValue(long id, String key) {

        long value = id;

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

        long[] numbers = getNumbers(line, lineIndex);
        Range range = new Range(numbers[0], numbers[1], numbers[2]);
        return range;
    }

    private long[] getNumbers(final String line, final int lineIndex) {

        SymbolParser p = new SymbolParser("([0-9]+)");
        ParserResult res = p.parseLine(line, lineIndex);
        List<TokenInformation> list = (List<TokenInformation>) res.getValue();
        return list.stream().mapToLong(item -> Long.parseLong(item.token)).toArray();
    }


    private String resolveKey(final String key) {

        return switch (key) {
            case "seeds" -> key;
            case "soil" -> "seed-to-soil";
            case "fertilizer" -> "soil-to-fertilizer";
            case "water" -> "fertilizer-to-water";
            case "light" -> "water-to-light";
            case "temperature" -> "light-to-temperature";
            case "humidity" -> "temperature-to-humidity";
            case "location" -> "humidity-to-location";
            default -> throw new IllegalArgumentException();
        };

    }
}
