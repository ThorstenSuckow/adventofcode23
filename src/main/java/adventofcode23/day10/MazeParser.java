package adventofcode23.day10;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeParser extends Parser {

    private final int[] UP = new int[]{-1, 0};
    private final int[] DOWN = new int[]{1, 0};
    private final int[] RIGHT = new int[]{0, 1};
    private final int[] LEFT = new int[]{0, -1};

    private final int[] BLOCKED = new int[]{0, 0};

    private final String[] dirUp = new String[]{"|", "L", "J", "S"};
    private final String[] dirDown = new String[]{"|", "F", "7", "S"};

    private final String[] dirRight = new String[]{"-", "F", "L", "S"};

    private final String[] dirLeft = new String[]{"-", "7", "J", "S"};

    private List<int[]> cornerPoints = new ArrayList<>();

    String[][] maze;

    List<String[]> mazeList = new ArrayList<>();

    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if(line.trim().equals("")) {
            return res;
        }

        String[] parts = line.trim().split("");

        mazeList.add(parts);

        return res;
    }



    public int[] findStart() {
        makeMaze();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j].equals("S")) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public String getSymbol(int[] position) {
        return maze[position[0]][position[1]];
    }

    public int[][] getMovementOptionsToNextTile(int[] position, int[][] directions) {

        List<int[]> dirs= new ArrayList<>();

        for (int i = 0; i < directions.length; i++) {

            if (isUp(directions[i]) && Arrays.asList(dirDown).contains(peek(position, UP))) {
                dirs.add(UP);
            }

            if (isDown(directions[i]) && Arrays.asList(dirUp).contains(peek(position, DOWN))) {
                dirs.add(DOWN);
            }

            if (isRight(directions[i]) && Arrays.asList(dirLeft).contains(peek(position, RIGHT))) {
                dirs.add(RIGHT);
            }

            if (isLeft(directions[i]) && Arrays.asList(dirRight).contains(peek(position, LEFT))) {
                dirs.add(LEFT);
            }
        }

        int[][] options = new int[dirs.size()][];

        int j = 0;
        for (int[] dir: dirs) {
            options[j++] = dir;
        }

        return options;
    }

    public int[] getPossibleDirection(int[] position, int[] direction) {
        int[][] directions = getPossibleDirectionsForTile(position);

        int[][] options = getMovementOptionsToNextTile(position, directions);

        if (options.length != 2) {
            System.err.println("Unexpected options for "
                    + Arrays.toString(position)
                    + " (" + getSymbol(position) + ") and direction "
                    + Arrays.toString(direction)
                    + "(" + dirToString(direction) + ")"
            );

            for (int i = 0; i < options.length; i++) {
                System.err.println(Arrays.toString(options[i]));
            }
            throw new RuntimeException();
        }

        // System.out.println(" :: Got possible next direction for " + getSymbol(position) + " and direction " + dirToString(direction));
        //System.out.println(Arrays.toString(options[0]));
        // System.out.println(Arrays.toString(options[1]));


        for (int i = 0; i < options.length; i++) {
            if (direction[0] + options[i][0] != 0
                || options[i][1] + direction[1] != 0
            ) {
                //System.out.println(" -> Returning direction " + dirToString(options[i]));
                return options[i];
            }
        }

        return BLOCKED;
    }

    public int[][] getPossibleDirectionsForTile(int[] position) {
        if (maze == null) {
            throw new IllegalStateException();
        }


        List<int[]> dirs= new ArrayList<>();

        if (canMove(position, UP)) {
            dirs.add(UP);
        }

        if (canMove(position, DOWN)) {
            dirs.add(DOWN);
        }

        if (canMove(position, LEFT)) {
            dirs.add(LEFT);
        }

        if (canMove(position, RIGHT)) {
            dirs.add(RIGHT);
        }

        int[][] directions = new int[dirs.size()][];

        int j = 0;
        for (int[] dir: dirs) {
            directions[j++] = dir;
        }

        return directions;
    }

    public boolean canMove(int[] position, int[] dir) {
        if (isUp(dir) && Arrays.asList(dirUp).contains(getSymbol(position))) {
            return true;
        }

        if (isRight(dir) && Arrays.asList(dirRight).contains(getSymbol(position))) {
            return true;
        }

        if (isLeft(dir) && Arrays.asList(dirLeft).contains(getSymbol(position))) {
            return true;
        }


        if (isDown(dir) && Arrays.asList(dirDown).contains(getSymbol(position))) {
            return true;
        }

        return false;
    }

    public String peek(int[] position, int[] dir) {
        String peek = "X";

        try {
            return maze[position[0] + dir[0]][position[1] + dir[1]];
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        return peek;
    }


    /**
     * Solution for part two uses shoelace formula and Pick's theorem
     * @return
     */
    public int computeEnclosedTiles() {

        int[] position = findStart();

        // check possible direction
        int[][] directions = getPossibleDirectionsForTile(position);
        int[][] dirs = getMovementOptionsToNextTile(position, directions);

        List<int[]> path = computePath(position, dirs[0]);

        int[][] points = cornerPoints.toArray(int[][]::new);

        // shoelace algorithm, and circumfence
        double area = 0;
        int x1, x2, y1, y2;
        double circumfence = 0;
        for (int i = 0; i < points.length; i++) {
            x1 = points[i][1];
            x2 = points[(i+1) % points.length][1];
            y1 = points[i][0];
            y2 = points[(i+1) % points.length][0];
            area += x1 * y2 - y1 * x2;
            if (i > 0) {
                circumfence +=
                    Math.sqrt(
                        Math.pow(points[i][0] - points[i - 1][0], 2) +
                        Math.pow(points[i][1] - points[i - 1][1], 2)
                    );
            } else {
                circumfence +=
                    Math.sqrt(
                        Math.pow(points[0][0] - points[points.length - 1][0], 2) +
                        Math.pow(points[0][1] - points[points.length - 1][1], 2)
                    );
            }
        }
        area = Math.abs(area) / 2d;

        // Picks theorem
        double i = area - ((circumfence / 2) -1);


        return (int)i;
    }


    public List<int[]> computePath(int[] startPosition, int[] direction) {

        List<int[]> path = new ArrayList<>();

        int[] position = move(startPosition, direction);
        int[] oldDirection;

        addCornerPoint(null, direction, startPosition);

        //System.out.println("\uD83D\uDEB6 " + dirToString(direction ) + " " + getSymbol(position) + " ("+Arrays.toString(position)+")");

        int steps = 0;
        path.add(position);
        while (!getSymbol(position).equals("S")) {
            oldDirection = direction;
            direction = getPossibleDirection(position, direction);

            addCornerPoint(oldDirection, direction, position);

            if (isBlocked(direction)) {
                throw new RuntimeException("Blocked :(");
            }
            position = move(position, direction);


            path.add(position);

            //System.out.println("\uD83D\uDEB6 " + dirToString(direction ) + " " + getSymbol(position) + " ("+Arrays.toString(position)+")");
            steps++;
        }

        //System.out.println("\uD83C\uDFC1 reached " + getSymbol(position) + " in " +steps + " steps");

        return path;
    }


    public void addCornerPoint(int[] oldDirection, int[] newDirection, int[] position) {

        if (pointEquals(oldDirection, newDirection)) {
            return;
        }

        for (int[] corner: cornerPoints) {
            if (pointEquals(corner, position)) {
                return;
            }
        }

        cornerPoints.add(position);

    }

    public boolean pointEquals(int[] x, int[] y) {
        if (x == null || y == null) {
            return false;
        }

        return x[0] == y[0] && x[1] == y[1];
    }

    public int compute() {

        int[] position = findStart();

        // check possible direction
        int[][] directions = getPossibleDirectionsForTile(position);
        int[][] dirs = getMovementOptionsToNextTile(position, directions);

        if (dirs.length != 2) {
            throw new RuntimeException("Cannot move from start: " + dirs.length + " options, should be 2");
        }
        List<int[]> path1 = computePath(position, dirs[0]);
        List<int[]> path2 = computePath(position, dirs[1]);

        return intersectPaths(path1, path2);
    }


    public int intersectPaths(List<int[]> path1, List<int[]> path2) {

        int steps1 = 0;
        for (int[] step1: path1) {
            steps1++;
            int steps2 = 0;
            for (int[] step2: path2) {
                steps2++;
                if (step1[0] == step2[0] && step1[1] == step2[1] && steps1 == steps2) {
                    return steps1;
                }
            }
        }

        return -1;
    }

    public int[] move(int[] position, int[] dir) {

        int[] newPosition = new int[]{0, 0};
        if (canMove(position, dir)) {
            newPosition[0] = position[0] + dir[0];
            newPosition[1] = position[1] + dir[1];
        }

        return newPosition;
    }



    public boolean isRight(int[] dir) {
        return dir[0] == 0 && dir[1] == 1;
    }

    public boolean isLeft(int[] dir) {
        return dir[0] == 0 && dir[1] == -1;
    }

    public boolean isDown(int[] dir) {
        return dir[0] == 1 && dir[1] == 0;
    }

    public boolean isUp(int[] dir) {
        return dir[0] == -1 && dir[1] == 0;
    }

    public boolean isBlocked(int[] dir) {
        return dir[0] == 0 && dir[1] == 0;
    }


    public String[][] makeMaze() {
        if (maze != null) {
            return maze;
        }

        maze = new String[mazeList.size()][];

        int i = 0;
        for (String[] line: mazeList) {
            maze[i++] = line;
        }

        return maze;
    }


    public String dirToString(int[] dir) {
        if (isUp(dir)) {
            return "UP";
        }
        if (isDown(dir)) {
            return "DOWN";
        }
        if (isRight(dir)) {
            return "RIGHT";
        }
        if (isLeft(dir)) {
            return "LEFT";
        }

        return "BLOCKED";
    }
}
