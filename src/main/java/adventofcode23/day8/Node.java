package adventofcode23.day8;

public class Node {

    private Node lft;

    private Node rgt;

    private String name;


    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Node getLeft() {
        return lft;
    }

    public Node getRight() {
        return rgt;
    }

    public void setLeft(Node lft) {
        this.lft = lft;
    }

    public void setRight(Node rgt) {
        this.rgt = rgt;
    }


    public String toString() {
        return name + " = "
                + "("+ (lft != null ? lft.getName() : null)
                + ", " + (rgt != null ? rgt.getName() : null)  + ")";
    }
}
