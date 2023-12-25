package adventofcode23.day13;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class MapParser extends Parser {


    List<List<String>> maps = new ArrayList<>();

    int cursor = 0;


    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        List<String> currentMap = maps.size() != 0 && maps.size() == cursor + 1 ? maps.get(cursor) : null;

        if (currentMap == null) {
            maps.add(cursor, new ArrayList<>());
            currentMap = maps.get(cursor);
        }

        if (line.trim().isEmpty()) {
            cursor++;

        } else {
            currentMap.add(line.trim());
        }

        return res;
    }


    @Override
    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        long x;
        long y;

        long sum = 0;
        for (List<String> map: maps) {
            long[] matches = processMap(map);
            x = matches[0];
            y = matches[1];
            sum += y * 100 + x;
        }

        List<ParserResult> results = new ArrayList<>();
        ParserResult res = new ParserResult();
        res.setValue(sum);

        results.add(res);

        return results;
    }


    public long convert(String line) {
        return Long.parseUnsignedLong(
                line.replace("#", "1").replace(".", "0"), 2);
    }

    public List<Long> getNumericalMap(List<String> mapObject, boolean vertical) {

        List<Long> newMap = new ArrayList<>();

        if (vertical) {
            int lineLength = mapObject.get(0).length();

            for (int i = 0; i < lineLength; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < mapObject.size(); j++) {
                    line.append(mapObject.get(j).charAt(i));
                }
                newMap.add(convert(line.toString()));
            }

            return newMap;
        }

        for (int j = 0; j < mapObject.size(); j++) {
            newMap.add(convert(mapObject.get(j)));
        }

        return newMap;
    }

    public long[] processMap(List<String> mapObject) {

        long[] matches = new long[2];

        List<Long> vert = getNumericalMap(mapObject, true);
        List<Long> hor = getNumericalMap(mapObject, false);

        matches[0] = perfectMatch(vert, processSymmetry(vert));
        matches[1] = perfectMatch(hor, processSymmetry(hor));

        return matches;
    }

    public int processSymmetry(List<Long> mapObject) {
        int match = 0;

        for (int i = 0; i < mapObject.size(); i++) {
            if (i == mapObject.size() - 1) {
                continue;
            }

            if (mapObject.get(i).equals(mapObject.get(i + 1))) {
                if (perfectMatch(mapObject, i) != 0) {
                    match = i;
                    break;
                }
            }
        }

        return match;
    }


    public long perfectMatch(List<Long> map, int index) {
        int h = index + 1;
        int perfectMatch = 0;
        int i;

        for(i = index; i >= 0; i--) {
            long left = map.get(i);
            long right = 0;
            if (h < map.size()) {
                right = map.get(h);
                if (left != right) {
                    perfectMatch = 0;
                    break;
                }
            }
             h++;
            perfectMatch++;
        }
        if (i == -1) {
            return perfectMatch;
        }
        return 0;
    }
}


