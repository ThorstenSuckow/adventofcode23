package adventofcode23.day11;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.*;

public class GalaxyParser extends Parser {

    List<String> galaxyList = new ArrayList<>();

    Map<Integer, int[]> galaxyMap = new HashMap<>();

    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if(line.trim().equals("")) {
            return res;
        }

        galaxyList.add(line.trim());

        return res;
    }

    public boolean processed(int a, int b, List<int[]> list) {
        for (int[] entry : list) {
            if ((entry[0] == a && entry[1] == b) ||
                    (entry[0] == b && entry[1] == a )) {
                return true;
            }
        }

        return false;
    }

    public int computePaths() {
        Map<Integer, int[]> map = getGalaxyMap();


        List<int[]> processed  = new ArrayList<>();

        int sum = 0;
        int galaxyCount = map.size();

        for (int i = 0; i < galaxyCount; i++) {
            for (int j = galaxyCount - 1; j > i; j--) {
                int length = computePath(i, j);
                sum += length;
            }
        }

        return sum;
    }


    public int computePath(int galaxyA, int galaxyB) {

        Map<Integer, int[]> map = getGalaxyMap();

        int x1, x2, y1, y2;

        int[] outer = map.get(galaxyA);
        int[] inner = map.get(galaxyB);

        x1 = outer[0];
        y1 = outer[1];
        x2 = inner[0];
        y2 = inner[1];

        return Math.abs(y2 - y1) + Math.abs(x2-x1);
    }


    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        List<String> remap = new ArrayList<>();

        ArrayList<Integer> cols = new ArrayList<>();
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

        // add cols
        for (int i = lineLength - 1; i >= 0; i--) {
            for (int j = 0; j < galaxyList.size(); j++) {
                if (cols.contains(i)) {
                    String line = galaxyList.get(j);
                    galaxyList.set(j, line.substring(0, i) + "." + line.substring(i));
                }
            }
            cols.add(i);
        }

        // multiply rows
        for (String line: galaxyList) {
            remap.add(line);
            if (!line.contains("#")) {
                remap.add(line);
            }
        }

        galaxyList = remap;

        // map galaxies
        int number = 0, y = 0, pos = 0;


        for (String line: galaxyList) {
            pos = line.indexOf("#", pos);
            while (pos != -1) {
                galaxyMap.put(number++, new int[]{pos, y});
                pos = line.indexOf("#", pos + 1);
            }

            y++;
        }

        return parserResults;
    }


    public Map<Integer, int[]> getGalaxyMap() {
        return galaxyMap;
    }

    public void logGalaxyMap() {

        Map<Integer, int[]> map = getGalaxyMap();

        for (Map.Entry<Integer, int[]> entry: map.entrySet()) {
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
