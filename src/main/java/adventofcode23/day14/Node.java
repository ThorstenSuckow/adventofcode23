package adventofcode23.day14;

public class Node<T> {

    private T data;

    public Node<T> next;

    public Node<T> previous;

    public boolean hasNext() {
        return next != null;
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public boolean equals(Node<T> n) {
        if (n == null) {
            return false;
        }

        return getData().equals(n.getData());
    }

    public String toString() {
        return String.valueOf(data);
    }

}
