package adventofcode23.day14;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class TiltParser extends Parser {

    enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST

    }

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

    private int nextRow(Direction dir, int value) {
        return switch (dir) {
            case Direction.NORTH -> ++value;
            case Direction.SOUTH -> --value;
            default -> value;
        };
    }

    private int nextChar(Direction dir, int value) {
        return switch (dir) {
            case Direction.WEST -> ++value;
            case Direction.EAST -> --value;
            default -> value;
        };
    }

    private int getMax(Direction dir) {
        return switch (dir) {
            case Direction.WEST -> rows.get(0).size();
            case Direction.EAST -> -1;
            case Direction.NORTH -> rows.size();
            case Direction.SOUTH -> -1;
        };
    }

    private boolean maxed(Direction dir, int value) {
        int max = getMax(dir);
        return switch (dir) {
            case Direction.WEST -> value >= max;
            case Direction.EAST -> value <= max;
            case Direction.NORTH -> value >= max;
            case Direction.SOUTH -> value <= max;
        };
    }

    public void move (int row, int c, Direction dir) {
        int nextRow;
        int nextChar;

        if (maxed(dir, row) || maxed(dir, c)) {
            return;
        }

        if (isBlocked(row, c)) {
            move(nextRow(dir, row), nextChar(dir, c), dir);
            return;
        }
        nextRow = row;
        nextChar = c;

        while (true) {

            if (maxed(dir, nextRow) || maxed(dir, nextChar)) {
                return;
            }

            if (isBlockSymbol(nextRow, nextChar)) {
                move(nextRow(dir, nextRow), nextChar(dir, nextChar), dir);
                return;
            }

            if (isMoveable(nextRow, nextChar)) {
                break;
            }

            nextRow = nextRow(dir, nextRow);
            nextChar = nextChar(dir, nextChar);
        }

        if (dir.equals(Direction.SOUTH) || dir.equals(Direction.NORTH)) {
            moveVert(nextRow, row, nextChar);
        } else {
            moveHor(row, nextChar, c);
        }

        move(nextRow(dir, row), nextChar(dir, c), dir);

    }

    public TiltParser tiltNorth() {
        int lineLength = rows.get(0).size();

        for (int c = 0; c < lineLength; c++) {
            move(0, c, Direction.NORTH);
        }
        return this;
    }

    public TiltParser tiltWest() {

        for (int c = 0; c < rows.size(); c++) {
            move(c, 0, Direction.WEST);
        }

        return this;
    }


    public TiltParser tiltEast() {

        for (int c = 0; c < rows.size(); c++) {
            move(c, rows.get(0).size() - 1, Direction.EAST);
        }

        return this;
    }


    public TiltParser tiltSouth() {

        int lineLength = rows.get(0).size();

        for (int c = 0; c < lineLength; c++) {
            move(rows.size() - 1, c, Direction.SOUTH);
        }
        return this;
    }

    public TiltParser cycle() {
        return tiltNorth().tiltWest().tiltSouth().tiltEast();
    }

    public TiltParser cycle(int count) {
        for (int i = 0; i < count; i++) {
            cycle();
        }
        return this;
    }

    public boolean moveVert(List<String> from, List<String> to, int position) {
        if (!isBlocked(to, position) && isMoveable(from, position)) {
            to.set(position, from.get(position));
            from.set(position, ".");
            return true;
        }
        return false;
    }

    public boolean moveHor(List<String> row, int from, int to) {
        if (!isBlocked(row, to) && isMoveable(row, from)) {
            row.set(to, row.get(from));
            row.set(from, ".");
            return true;
        }
        return false;
    }


    public boolean moveVert(int from, int to, int position) {
        return moveVert(rows.get(from), rows.get(to), position);
    }

    public boolean moveHor(int row, int from, int to) {
        return moveHor(rows.get(row), from, to);
    }



    public boolean isMoveable(int row, int position) {
        return isMoveable(rows.get(row), position);
    }


    public boolean isBlockSymbol(int row, int position) {
        return isBlockSymbol(rows.get(row), position);
    }

    public boolean isBlocked(int row, int position) {
        return isBlocked(rows.get(row), position);
    }

    public boolean isMoveable(List<String> line, int position) {
        return line.get(position).equals("O");
    }

    public boolean isBlockSymbol(List<String> line, int position) {
        return line.get(position).equals("#");
    }

    public boolean isBlocked(List<String> line, int position) {
        return !line.get(position).equals(".");
    }

    @Override
    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        return parserResults;
    }

    public long getSum() {
        int weight = rows.size();
        long sum = 0;
        for (int i = 0; i < rows.size(); i++) {
            for (int c = 0; c < rows.get(0).size(); c++) {
                sum = sum + (isMoveable(i, c) ? weight : 0);
            }
            weight--;
        }

        return sum;
    }

    public long getMoveableCount() {
        long sum = 0;
        for (int i = 0; i < rows.size(); i++) {
            for (int c = 0; c < rows.get(0).size(); c++) {
                sum = sum + (isMoveable(i, c) ? 1 : 0);
            }
        }

        return sum;
    }

    public void log(List<List<String>> rows) {

        for (List<String> row: rows) {
            System.out.println(row);
        }
    }
}