package adventofcode23.day8;

public class Node {

    private Node lft;

    private Node rgt;

    private String name;

    private Node parent;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }


    public void setParent(Node parent) {
        this.parent = parent;
    }


    public Node getLeft() {
        return lft;
    }

    public Node getRight() {
        return rgt;
    }

    public void setLeft(Node lft) {
        this.lft = lft;
        lft.setParent(this);
    }

    public void setRight(Node rgt) {
        this.rgt = rgt;
        rgt.setParent(this);
    }


    public String toString() {
        return name + " = "
                + "(l: "+ (lft != null ? lft.getName() : null)
                + ", r:" + (rgt != null ? rgt.getName() : null)  + ", p: " + (parent != null ? parent.getName() : null) + ")";
    }
}
