package adventofcode23.day14;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class TiltParser extends Parser {


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

    /**
     * ...0.....#....
     * .....0.....#
     * .......0.....
     *
     * @return
     */
    public long tilt() {

        List<String> current;
        List<String> head;

        int lineLength = rows.get(0).size();

        for (int c = 0; c < lineLength; c++) {
            move(0, c);
        }

        int weight = rows.size();
        long sum = 0;
        for (int i = 0; i < rows.size(); i++) {
            for (int c = 0; c < lineLength; c++) {
                sum = sum + (isMoveable(i, c) ? weight : 0);
            }
            weight--;
        }

        return sum;
    }

    public void move (int row, int c) {
        int next;
        int max = rows.size();

        if (row >= max) {
            return;
        }

        if (isBlocked(row, c)) {
            move(++row, c);
            return;
        }
        next = row;

        while (true) {

            if (next >= max) {
                return;
            }

            if (isBlockSymbol(next, c)) {
                move(++next, c);
                return;
            }

            if (isMoveable(next, c)) {
                break;
            }
            next++;
        }

        move(next, row, c);

        move(row+1, c);

    }


    public boolean move(List<String> from, List<String> to, int position) {
        if (!isBlocked(to, position) && isMoveable(from, position)) {
            to.set(position, from.get(position));
            from.set(position, ".");
            return true;
        }
        return false;
    }


    public boolean move(int from, int to, int position) {
        return move(rows.get(from), rows.get(to), position);
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

    public void log(List<List<String>> rows) {

        for (List<String> row: rows) {
            System.out.println(row);
        }

    }
}