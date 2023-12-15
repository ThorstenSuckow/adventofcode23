package adventofcode23.day8;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.*;

public class MapParser extends Parser {


    private Node root;

    private Map<String, String[]> strMap = new HashMap<>();

    private String directions;

    private String start = "";
    private String end = "";

    private List<Node> nodePool = new ArrayList<>();


    public MapParser(String start, String end) {
        if (start.equals("") || end.equals("")) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }

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

        String name = parts[0];
        String[] nodeChilds = parts[1].split(", ");

        String leftNodeStr = nodeChilds[0].substring(1);
        String rightNodeStr = nodeChilds[1].substring(0, nodeChilds[1].length() - 1);

        nodePool.add(new Node(name));

        strMap.put(name, new String[]{leftNodeStr, rightNodeStr});


        return res;
    }

    public Node buildTree() {

        if (root != null) {
            return root;
        }

        root = createNode(start);
        return root;
    }

    Node getNode(String name) {
        //System.out.println("looking up " + name + "... ");
        for (Node n: nodePool) {
            if (n.getName().equals(name)) {
                //System.out.println(name + " found in graph!");
                return n;
            }
        }

        //System.out.println(name + " not found. Creating and adding to graph.");
        Node n = new Node(name);
        nodePool.add(n);
        return n;
    }

    public Map<String, String[]> getStrMap() {
        return strMap;
    }

    private Node createNode(String name) {

        //System.out.println("Creating node " + name +"...");
        Node node = getNode(name);

        if (node.getLeft() == null) {

            String leftNodeName = strMap.get(name)[0];
            //System.out.println("[left] Creating left node " + leftNodeName + "...");
            Node leftNode = getNode(leftNodeName);
            node.setLeft(leftNode);
            //System.out.println("[left] Added left node to " + node + "...");

            if (node.getParent() != leftNode  && !leftNodeName.equals(start)) {// && !node.hasParent(leftNode)) {
                createNode(leftNodeName);
            }
        }

        if (node.getRight() == null) {
            String rightNodeName = strMap.get(name)[1];
            //System.out.println("[right] Creating right node " + rightNodeName + "...");
            Node rightNode = getNode(rightNodeName);
            node.setRight(rightNode);
            //System.out.println("[right] Added right node to " + node + "...");

            if (node.getParent() != rightNode && !rightNodeName.equals(start)) {// && !node.hasParent(rightNode)) {
                createNode(rightNodeName);
            }
        }

        return node;
    }


    public int navigate() {

        buildTree();

        int steps = 0;

        Node node = getNode(start);
        for (int i = 0; i < directions.length(); i++) {
            steps++;

            char dir = directions.charAt(i);

            if (node == null) {
                throw new RuntimeException("node is null");
            }

            if (dir == 'L') {
                node = node.getLeft();
            } else if (dir == 'R') {
                node = node.getRight();
            } else {
                throw new RuntimeException();
            }

            //System.out.println("[" + steps + "]" + dir + "'" + node + "'");

            if (i == directions.length() - 1) {
                i = -1;
            }

            if (node.getName().endsWith(end)) {
                return steps;
            }

        }

        return steps;
    }

}
