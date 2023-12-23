package adventofcode23.day14;

import java.util.ArrayList;
import java.util.List;

public class Cycle<T> {

    List<T> list = new ArrayList<>();

    public int start;

    public int length;

    public boolean equals(Cycle<T> c) {

        if (c.length != length) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(c.list.get(i))) {
                return false;
            }
        }

        return true;
    }


    public String toString() {
        return "[cycle]" + start +", " + length + list;
    }



    public T getAt(int i) {
        return list.get(i);
    }
}
