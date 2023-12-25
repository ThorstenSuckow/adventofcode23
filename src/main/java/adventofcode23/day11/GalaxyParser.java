package adventofcode23.day11;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalaxyParser extends Parser {

    List<String> galaxyList = new ArrayList<>();

    Map<Integer, long[]> galaxyMap = new HashMap<>();

    int[] multipliedCols;

    int[] multipliedRows;

    private int growthFactor;

    public GalaxyParser() {
        this(2);
    }

    public GalaxyParser(int growthFactor) {
        this.growthFactor = growthFactor;
    }


    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if(line.trim().equals("")) {
            return res;
        }

        galaxyList.add(line.trim());

        return res;
    }

    public long computePaths() {
        Map<Integer, long[]> map = getGalaxyMap();


        long sum = 0;
        int galaxyCount = map.size();

        for (int i = 0; i < galaxyCount; i++) {
            for (int j = galaxyCount - 1; j > i; j--) {

                long length = computePath(i, j);
                sum += length;
            }
        }

        return sum;
    }


    public long computePath(int galaxyA, int galaxyB) {

        Map<Integer, long[]> map = getGalaxyMap();

        long x1, x2, y1, y2;

        long[] outer = map.get(galaxyA);
        long[] inner = map.get(galaxyB);

        x1 = outer[0];
        y1 = outer[1];
        x2 = inner[0];
        y2 = inner[1];

        return Math.abs(y2 - y1) + Math.abs(x2-x1);
    }


    public int[] getMultipliedCols() {
        return multipliedCols;
    }

    public int[] getMultipliedRows() {
        return multipliedRows;
    }

    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        ArrayList<Integer> cols = new ArrayList<>();
        ArrayList<Integer> rows = new ArrayList<>();

        // find empty cols
        int lineLength = galaxyList.get(0).length();
        for (int i = 0; i < lineLength; i++) {
            boolean addToCol = true;
            for (String line: galaxyList) {
                if (line.charAt(i) != '.') {
                    addToCol = false;
                    break;
                }
            }
            if (addToCol) {
                cols.add(i);
            }

        }


        // multiply rows
        int rowIndex = 0;
        for (String line: galaxyList) {
            if (!line.contains("#")) {
                rows.add(rowIndex);
            }
            rowIndex++;
        }


        // map galaxies
        int number = 0, y = 0, pos = 0;

        multipliedCols = cols.stream().mapToInt(i -> i).toArray();
        multipliedRows = rows.stream().mapToInt(i -> i).toArray();

        for (String line: galaxyList) {
            pos = line.indexOf("#", pos);


            long rowDelta = 0;

            for (int j = 0; j < multipliedRows.length; j++) {
                if (multipliedRows[j] < y) {
                   rowDelta += (growthFactor - 1);
                }
            }

            while (pos >= 0) {
                long colDelta = 0;
                for (int j = 0; j < multipliedCols.length; j++) {
                    if (multipliedCols[j] < pos) {
                        colDelta += (growthFactor - 1);
                    }
                }

                galaxyMap.put(number++, new long[]{pos + colDelta, y + rowDelta});

                pos = line.indexOf("#", pos + 1);
            }

            y++;
        }

        return parserResults;
    }


    public Map<Integer, long[]> getGalaxyMap() {
        return galaxyMap;
    }

    public void logGalaxyMap() {

        Map<Integer, long[]> map = getGalaxyMap();

        for (Map.Entry<Integer, long[]> entry: map.entrySet()) {
            System.out.println("Galaxy "
                    + entry.getKey() + ": ["
                    + entry.getValue()[0]
                    + ", "
                    +  entry.getValue()[1] + "]");
        }

    }

    public void log() {
        for (String line: galaxyList) {
            System.out.println(line);
        }
    }
}
