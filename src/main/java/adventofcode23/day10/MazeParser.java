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

    private final String[] dirUp = new String[]{"|", "7", "F", "S"};
    private final String[] dirDown = new String[]{"|", "L", "J", "S"};

    private final String[] dirRight = new String[]{"-", "J", "7", "S"};

    private final String[] dirLeft = new String[]{"-", "F", "L", "S"};


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

    public int[][] getPossibleDirections(int[] position) {
        if (maze == null) {
            throw new IllegalStateException();
        }

        if (!getSymbol(position).equals("S")) {
            throw new RuntimeException();
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
        if (isUp(dir) && Arrays.asList(dirUp).contains(peek(position, dir))) {
            return true;
        }

        if (isRight(dir) && Arrays.asList(dirRight).contains(peek(position, dir))) {
            return true;
        }

        if (isLeft(dir) && Arrays.asList(dirLeft).contains(peek(position, dir))) {
            return true;
        }


        if (isDown(dir) && Arrays.asList(dirDown).contains(peek(position, dir))) {
            return true;
        }

        throw new RuntimeException("cannot move :(");
    }

    public String peek(int[] position, int[] dir) {
        return maze[position[0] + dir[0]][position[1] + dir[1]];
    }

    public long compute() {

        int[] position = findStart();

        // check possible direction
        int[][] dirs = getPossibleDirections(position);

        if (dirs.length == 0) {
            throw new RuntimeException();
        }

        int steps = 0;
        position = move(position, dirs[0]);
        while (!getSymbol(position).equals("S")) {
            position = move(position);
            steps++;
        }


        return 0;
    }

    public int[] move(int[] position, int[] dir) {

        if (canMove(position, dir)) {
            position[0] = position[0] + dir[0];
            position[1] = position[1] + dir[1];
        }

        return position;
    }

    /**
     * | is a vertical pipe connecting north and south.
     * - is a horizontal pipe connecting east and west.
     * L is a 90-degree bend connecting north and east.
     * J is a 90-degree bend connecting north and west.
     * 7 is a 90-degree bend connecting south and west.
     * F is a 90-degree bend connecting south and east.
     * . is ground; there is no pipe in this tile.
     * S is t
     * @param symbol
     * @param dir = 2 for right, dir = 4 for left, dir = 3 for down, dir = 1 for up
     * @return
     */
    public int[] move(String symbol, int[] dir) {

        int[] steps = new int[2];

        switch (symbol) {
            case "|":
                if (isUp(dir)) {
                    return UP;
                }
                if (isDown(dir)) {
                    return DOWN;
                }
                break;
            case "-":
                if (isLeft(dir)) {
                    return LEFT;
                }
                if (isRight(dir)) {
                    return RIGHT;
                }
                break;
            case "L":
                // north and eas
                if (isDown(dir)) {
                    return RIGHT;
                }
                if (isLeft(dir)) {
                    return UP;
                }
                break;
            case "J":
                // north and west
                if (isDown(dir)) {
                    return LEFT;
                }
                if (isRight(dir)) {
                    return UP;
                }
                break;
            case "7":
                // south and west
                if (isUp(dir)) {
                    return LEFT;
                }
                if (isRight(dir)) {
                    return DOWN;
                }

                break;
            case "F":
                // down and right
                if (isUp(dir)) {
                    return RIGHT;
                }
                if (isLeft(dir)) {
                    return DOWN;
                }
                break;
            case ".", "S":
                return BLOCKED;

        }

        return BLOCKED;
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



    public void log(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public void logMaze() {
        makeMaze();
        log(maze);
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

}
