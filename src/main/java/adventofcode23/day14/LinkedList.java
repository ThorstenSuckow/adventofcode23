package adventofcode23.day14;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
    private Node<T> head = null;

    private int size = 0;


    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        size++;
        newNode.next = head;
        head = newNode;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<T> getLast() {
        return get(size - 1);
    }

    public Node<T> get(int nodePos) {

        Node<T> temp = head;
        int counter = 0;
        for (; counter < nodePos; counter++) {
            temp = temp.next;  // Move pointer one node ahead
        }
        return temp;
    }

    public int getIndex(Node<T> n) {

        for (int i = 0; i < size; i++) {
            if (get(i) == n) {
                return size - i;
            }
        }
        return -1;
    }

    /**
     * Returns the most recent cycle found
     * @return
     */
    public Cycle<T> hasCycle() {
        if (isEmpty())
            return null;

        Node<T> fastPointer = head;
        Node<T> slowPointer = head;

        Cycle<T> c;
        int cycleLength = 0;
        List<T> list = new ArrayList<>();

        while (true) {
            list.add(slowPointer.getData());
            slowPointer = slowPointer.next;

            if (!fastPointer.hasNext() || !fastPointer.next.hasNext()){
                return null;
            }

            cycleLength++;

            fastPointer = fastPointer.next.next;

            if (slowPointer.equals(fastPointer)) {
                int start = getIndex(slowPointer);

                c = new Cycle<T>();
                c.start = start - cycleLength;
                c.length = cycleLength;
                c.list = list.reversed();

                return c;
            }
        }
    }


}
