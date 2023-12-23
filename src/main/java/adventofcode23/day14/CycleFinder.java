package adventofcode23.day14;

import java.util.ArrayList;
import java.util.List;

public class CycleFinder<T> {


    List<Cycle<T>> cycleList = new ArrayList<>();


    public void add(Cycle<T> c) {
        if (c != null) {
            cycleList.add(c);
        }
    }


    /**
     * This should be improved. Mulitple cycles might exists in a list, so this
     * implmentation looks up the biggest cycle and afterwards simply checks if it occurs
     * a second time in the list.
     * @return
     */
    public Cycle<T> findUniqueCycle() {

        if (cycleList.isEmpty()) {
            return null;
        }
        Cycle<T> c = null;

        int max = 0;
        for (int i = 0; i < cycleList.size(); i++) {
            Cycle<T> tmp = cycleList.get(i);
            if (tmp.length > max) {
                max = tmp.length;
                c = tmp;
            }
        }

        for (int i = 0; i < cycleList.size(); i++) {
            if (c != null && cycleList.get(i).equals(c)) {
                return cycleList.get(i);
            }
        }

        return null;
    }

}
