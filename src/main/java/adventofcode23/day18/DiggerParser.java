package adventofcode23.day18;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

public class DiggerParser extends Parser{

    List<List<String>> rows = new ArrayList<>();

    List<String[]> directions = new ArrayList<>();

    boolean useHex;

    public DiggerParser() {
        this(false);
    }
    public DiggerParser(boolean useHex) {
        this.useHex = useHex;
    }

    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        line = line.trim();

        if (line.isEmpty()) {
            return res;
        }

        String[] parts = line.split(" ");

        String direction = parts[0];
        String steps = parts[1];
        String hex = parts[2].substring(1, parts[2].length() - 1);

        if (useHex) {
            direction = hexToDirection(hex);
            steps = hexToSteps(hex);
        }

        directions.add(new String[]{direction, steps});

        return res;
    }

    public String hexToSteps(String hex) {

        int steps = 0;

        hex = hex.substring(1, hex.length() - 1);

        steps = HexFormat.fromHexDigits(hex);

        return String.valueOf(steps);
    }

    public String hexToDirection(String hex) {

        String top = hex.substring(hex.length() - 1);
        switch (top) {
            case "0" -> {
                return "R";
            }
            case "1" -> {
                return "D";
            }
            case "2" -> {
                return "L";
            }
            case "3" -> {
                return "U";
            }
        }
        throw new IllegalArgumentException();
    }


    private List<long[]> detectCorners() {
        List<long[]> points = new ArrayList<>();

        long currentY = 0;
        long currentX = 0;

        for (String[] dir: directions) {
            String direction = dir[0];
            long steps = Integer.valueOf(dir[1]);
            points.add(new long[]{currentX, currentY});

            if (direction.equals("R")) {
                currentX += steps;
            } else if (direction.equals("D")) {
                currentY += steps;
            } else if (direction.equals("L")) {
                currentX -= steps;
            } else if (direction.equals("U")) {
                currentY -= steps;
            }
        }

        return points;
    }

    public List<List<String>> createGrid(int minX, int maxX, int minY, int maxY) {
        List<List<String>> rows = new ArrayList<>();
        int totalHeight = Math.abs(minY) + Math.abs(maxY);
        int totalWidth = Math.abs(minX) + Math.abs(maxX);

        for (int i = 0; i  < totalHeight + 1; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < totalWidth + 1; j++) {
                row.add(".");
            }
            rows.add(row);
        }

        return rows;
    }


    public List<ParserResult> postProcess(List<ParserResult> res) {

        List<ParserResult> results = new ArrayList<>();

        List<long[]> points = detectCorners();

        long maxX = Long.MIN_VALUE;
        long minX = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;

        for (long[] point: points) {
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
            maxY = Math.max(maxY, point[1]);
            minY = Math.min(minY, point[1]);
        }

       // rows = createGrid(minX, maxX, minY, maxY);
       // rows = drawRects(rows, minX, minY);

        double sum = countTiles(points);

        ParserResult sumResult = new ParserResult();
        sumResult.setValue(sum);
        results.add(sumResult);
        return results;
    }


    /**
     * See MazeParser of day 10
     * @param pointsList
     * @return
     */
    public double countTiles(List<long[]> pointsList) {

        long[][] points = pointsList.toArray(long[][]::new);

        double area = 0;
        long x1, x2, y1, y2;
        double circumference = 0;
        for (int i = 0; i < points.length; i++) {
            x1 = points[i][0];
            x2 = points[(i+1) % points.length][0];
            y1 = points[i][1];
            y2 = points[(i+1) % points.length][1];
            area += x1 * y2 - y1 * x2;
            if (i > 0) {
                circumference +=
                        Math.sqrt(
                                Math.pow(points[i][0] - points[i - 1][0], 2) +
                                        Math.pow(points[i][1] - points[i - 1][1], 2)
                        );
            } else {
                circumference +=
                        Math.sqrt(
                                Math.pow(points[0][0] - points[points.length - 1][0], 2) +
                                        Math.pow(points[0][1] - points[points.length - 1][1], 2)
                        );
            }
        }

        area = Math.abs(area) / 2d;

        // Picks theorem
        // compute area, then re-add circumference!
        double i = area - ((circumference / 2d ) - 1) + circumference;

        return i;
    }

    public List<List<String>> drawRects(List<List<String>> rows, int minX, int minY) {

        int currentX = Math.abs(minX);
        int currentY = Math.abs(minY);

        String symbol = "#";
        for (String[] dir: directions) {

            String direction = dir[0];
            int steps = Integer.valueOf(dir[1]);

            if (direction.equals("R")) {
                for (int i = currentX; i < steps + currentX; i++) {
                    rows.get(currentY).set(i, symbol);
                }
                currentX += steps;
            } else if (direction.equals("D")) {
                for (int i = currentY; i < steps + currentY; i++) {
                    rows.get(i).set(currentX, symbol);
                }
                currentY += steps;
            } else if (direction.equals("L")) {
                for (int i = currentX; i >=  currentX - steps; i--) {
                    rows.get(currentY).set(i, symbol);
                }
                currentX -= steps;
            } else if (direction.equals("U")) {
                for (int i = currentY; i >= currentY - steps; i--) {
                    rows.get(i).set(currentX, symbol);
                }
                currentY -= steps;
            }

            if (currentY < 0) {
                throw new RuntimeException();
            }
        }

        return rows;
    }





    public void logDirections() {
        for (String[] dir: directions) {
            System.out.println(dir[0] +" " +dir[1]);
        }
    }

    public void logRows() {
        for (List<String> row: rows) {
            System.out.println(row);
        }
    }
}