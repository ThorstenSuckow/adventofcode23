package adventofcode23.day16;

import adventofcode23.lib.Direction;
import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class RayParser extends Parser {

    private String[][] stepOverlay;

    private boolean[][][] visitedNodes;

    List<List<String>> rows = new ArrayList<>();


    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        List<String> row = new ArrayList<>();

        line = line.trim();

        if (line.trim().isEmpty()) {
            return res;
        }

        for (int c = 0; c < line.length(); c++) {
            row.add(String.valueOf(line.charAt(c)));
        }

        rows.add(row);

        return res;
    }

    public List<ParserResult> postProcess(List<ParserResult> res) {

        List<ParserResult> results = new ArrayList<>();

        int sum = beamMeUp();

        ParserResult sumResult = new ParserResult();
        sumResult.setValue(sum);
        results.add(sumResult);
        return results;
    }

    public boolean maxPos(int x, int y, Direction dir) {
        switch (dir) {
            case EAST: return x >= rows.get(0).size();
            case WEST: return x < 0;
            case NORTH: return y < 0;
            case SOUTH: return y >= rows.size();
        }
        return false;
    }

    public String symbolAt(int x, int y) {
        if (0 <= y && y < rows.size() && 0 <= x && x < rows.get(0).size()) {
            return rows.get(y).get(x);
        }
        return null;
    }


    public int beamMeUp() {
        Direction[] dir = computeDirection(0, 0, Direction.EAST);

        if (dir.length != 0) {
            return beamMeUp(0, 0, dir[0]);
        }

        return beamMeUp(0, 0, Direction.EAST);

    }


    public int beamMeUp(int x, int y, Direction dir) {


        int sum = 0;
        move(x, y, dir);

        for (y = 0; y < stepOverlay.length; y++) {
            for (x = 0; x < stepOverlay[y].length; x++) {
                if (stepOverlay[y][x] != null) {
                    sum++;
                }
            }
        }

        return sum;
    }


    public int getMax() {

        int max = 0;
        for (int y = 0; y < rows.size(); y++) {
            stepOverlay = null;
            visitedNodes = null;
            max = Math.max(max, beamMeUp(0, y, Direction.EAST));

            stepOverlay = null;
            visitedNodes = null;
            max = Math.max(max, beamMeUp(rows.get(0).size() - 1, y, Direction.WEST));
        }

        for (int x = 0; x < rows.get(0).size(); x++) {
            stepOverlay = null;
            visitedNodes = null;
            max = Math.max(max, beamMeUp(x, 0, Direction.SOUTH));

            stepOverlay = null;
            visitedNodes = null;
            max = Math.max(max, beamMeUp(x, rows.size() - 1, Direction.NORTH));
        }

        return max;
    }



    public Direction[] computeDirection(int x, int y, Direction toDir) {

        String symbol = symbolAt(x, y);

        if (symbol.equals("|")) {
            if (toDir == Direction.EAST || toDir == Direction.WEST) {
                return new Direction[]{Direction.NORTH, Direction.SOUTH};
            }
            return new Direction[]{toDir};
        }

        if (symbol.equals("-")) {
            if (toDir == Direction.SOUTH || toDir == Direction.NORTH) {
                return new Direction[]{Direction.EAST, Direction.WEST};
            }
            return new Direction[]{toDir};
        }

        if (symbol.equals("\\")) {
            if (toDir == Direction.EAST) {
                return new Direction[]{Direction.SOUTH};
            }
            if (toDir == Direction.NORTH) {
                return new Direction[]{Direction.WEST};
            }
            if (toDir == Direction.SOUTH) {
                return new Direction[]{Direction.EAST};
            }
            if (toDir == Direction.WEST) {
                return new Direction[]{Direction.NORTH};
            }
        }

        if (symbol.equals("/")) {
            if (toDir == Direction.EAST) {
                return new Direction[]{Direction.NORTH};
            }
            if (toDir == Direction.NORTH) {
                return new Direction[]{Direction.EAST};
            }
            if (toDir == Direction.SOUTH) {
                return new Direction[]{Direction.WEST};
            }
            if (toDir == Direction.WEST) {
                return new Direction[]{Direction.SOUTH};
            }
        }

        return new Direction[0];
    }

    public boolean isPassage(int x, int y, Direction toDir) {

        String symbol = symbolAt(x, y);

        if (symbol.equals(".")) {
            return true;
        }
        if (computeDirection(x, y, toDir).length == 1 && computeDirection(x, y, toDir)[0] == toDir) {
            return true;
        }

        return false;
    }


    public boolean isSplit(int x, int y, Direction toDir) {

        if (maxPos(x, y, toDir)) {
            return false;
        }

        String symbol = symbolAt(x, y);
        return ((symbol.equals("|") || symbol.equals("-")) && !isPassage(x, y, toDir));
    }

    public boolean isReflect(int x, int y, Direction toDir) {


        if (maxPos(x, y, toDir)) {
            return false;
        }

        String symbol = symbolAt(x, y);
        return symbol.equals("/") || symbol.equals("\\");
    }


    public void move(int x, int y, Direction toDir) {

        int nextX = x;
        int nextY = y;
        int peekX, peekY;


        while (true) {

            markStep(nextX, nextY, toDir);

            peekX = nextX(nextX, toDir);
            peekY = nextY(nextY, toDir);

            if (maxPos(peekX, peekY, toDir)) {
                break;
            }


            // -, . , #
            if (isPassage(peekX, peekY, toDir)) {
                move(peekX, peekY, toDir);
                return;
            }

            if (isSplit(peekX, peekY, toDir) || isReflect(peekX, peekY, toDir)) {
                Direction[] nextDirs = computeDirection(peekX, peekY, toDir);

                for (int i = 0; i < nextDirs.length; i++) {
                    if (!isVisited(peekX, peekY, nextDirs[i])) {
                        markVisited(peekX, peekY, nextDirs[i]);
                        move(peekX, peekY, nextDirs[i]);
                    }
                }
                return;

            }

            nextX = peekX;
            nextY = peekY;

        }

        return;
    }


    private int nextY(int value, Direction toDir) {
        return switch (toDir) {
            case Direction.NORTH -> --value;
            case Direction.SOUTH -> ++value;
            default -> value;
        };
    }

    private int nextX(int value, Direction toDir) {
        return switch (toDir) {
            case Direction.WEST -> --value;
            case Direction.EAST -> ++value;
            default -> value;
        };
    }


    private void markStep(int x, int y, Direction toDir) {

        if (stepOverlay == null) {
            stepOverlay = new String[rows.size()][rows.get(0).size()];
        }

        if (maxPos(x, y, toDir)) {
            return;
        }

        switch (toDir) {
            case EAST -> stepOverlay[y][x] = ">";
            case WEST -> stepOverlay[y][x] = "<";
            case NORTH -> stepOverlay[y][x] = "^";
            case SOUTH -> stepOverlay[y][x] = "v";
        }

    }


    private void markVisited(int x, int y, Direction toDir) {
        if (!isVisited(x, y, toDir)) {
            visitedNodes[y][x][dirToInt(toDir)] = true;
        }
    }

    private int dirToInt(Direction dir) {
        switch (dir) {
            case NORTH -> {
                return 0;
            }
            case EAST -> {
                return 1;
            }
            case SOUTH -> {
                return 2;
            }
            case WEST -> {
                return 3;
            }
        }
        return -1;
    }

    private boolean isVisited(int x, int y, Direction toDir) {

        if (visitedNodes == null) {
            visitedNodes = new boolean[rows.size()][rows.get(0).size()][4];
        }

        return visitedNodes[y][x][dirToInt(toDir)];
    }

    public void logRays() {

        for (int y = 0; y < rows.size(); y++) {
            for (int x = 0; x < rows.get(0).size(); x++) {
                if (symbolAt(x, y).equals(".") && stepOverlay[y][x] != null) {
                    System.out.print(stepOverlay[y][x] + " ");
                } else {
                    System.out.print(symbolAt(x, y)+ " ");
                }
            }
            System.out.println();
        }
    }

}