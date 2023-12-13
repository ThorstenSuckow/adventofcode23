package adventofcode23.day8;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.*;

public class MapParser extends Parser {


    private Node root;

    private Map<String, Node> nodeMap = new HashMap<>();

    private Map<String, String[]> strMap = new HashMap<>();

    private String directions;

    private String start = "";

    private String end;

    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if(line.trim().equals("")) {
            return res;
        }

        if (!line.contains("=")) {
            directions = line;
            return res;
        }

        String[] parts = line.split(" = ");

        String[] nodeChilds = parts[1].split(", ");

        String leftNodeStr = nodeChilds[0].substring(1);
        String rightNodeStr = nodeChilds[1].substring(0, nodeChilds[1].length() - 1);

        String name = parts[0];

        strMap.put(name, new String[]{leftNodeStr, rightNodeStr});

        if (start.equals("")) {
            start = name;
        }

        end = name;

        return res;
    }

    public int navigate() {

        int steps = 0;

        String node = "AAA";
        for (int i = 0; i < directions.length(); i++) {
            steps++;

            char dir = directions.charAt(i);

            if (dir == 'L') {
                node = strMap.get(node)[0];
            } else if (dir == 'R') {
                node = strMap.get(node)[1];
            } else {
                throw new RuntimeException();
            }

            if (node.contains("ZZ")) {
                return steps;
            }

            if (i == directions.length() - 1) {
                i = -1;
            }
        }

        return steps;
    }


}
