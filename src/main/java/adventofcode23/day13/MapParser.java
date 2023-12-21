package adventofcode23.day13;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.Arrays;
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


        long x = 0;
        long y = 0;

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


    public long[] processMap(List<String> mapObject) {

        long[] matches = new long[2];

        long horizontal = processSymmetry(mapObject);
        //System.out.println("hor: " + horizontal);

        matches[1] = perfectMatch(mapObject, horizontal);
        //System.out.println("[hor] Perfect match: " + matches[1]);



        int lineLength = mapObject.get(0).length();

        List<String> vert = new ArrayList<>();
        for (int i = 0; i < lineLength; i++) {
            String line = "";
            for (int j = 0; j < mapObject.size(); j++) {
                line += mapObject.get(j).charAt(i);
            }
            vert.add(line);
        }

        int vertical = processSymmetry(vert);
        //System.out.println("vert: " + vertical);
        matches[0] = perfectMatch(vert, vertical);
        //System.out.println("[vert] Perfect match: " + matches[0]);
        //System.out.println();

        return matches;
    }

    public int processSymmetry(List<String> mapObject) {

        int match = -1;

        for (int i = 0; i < mapObject.size(); i++) {
            if (i == mapObject.size() -1) {
                continue;
            }
            if (mapObject.get(i).equals(mapObject.get(i + 1))) {
                if (perfectMatch(mapObject, i) != 0) {
                    match = i;
                }
            }
        }



        return match;
    }


    public long perfectMatch(List<String> map, long index) {
        long h = index + 1;
        long perfectMatch = 0;
        for(long i = index; i >= 0; i--) {
            String left = map.get((int) i);
            String right = "";
            if (h < map.size()) {
                right = map.get((int) h++);
                if (!left.equals(right)) {
                    perfectMatch = 0;
                    break;
                }
            }
            perfectMatch++;
        }
        return perfectMatch;
    }
}


