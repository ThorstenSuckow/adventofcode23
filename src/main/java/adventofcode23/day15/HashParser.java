package adventofcode23.day15;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.ArrayList;
import java.util.List;

public class HashParser extends Parser {

    List<String> steps = new ArrayList<>();

    Box[] boxes;

    public HashParser() {
        initBoxes();
    }

    class Box {

        int index;

        public Box(int index) {
            this.index = index;
        }

        public List<Lens> lenses = new ArrayList<>();


        public void add(String label, int focalLength) {
            if (contains(label)) {
                replace(label, focalLength);
                return;
            }
            lenses.add(new Lens(label, focalLength));
        }

        private void replace(String label, int focalLength) {

            for (int i = lenses.size() - 1; i >= 0;  i--) {
                if (lenses.get(i).label.equals(label)) {
                    lenses.set(i, new Lens(label, focalLength));
                }
            }
        }

        public void remove(String label) {
            if (!contains(label)) {
                return;
            }
            for (int i = lenses.size() - 1; i >= 0;  i--) {
                if (lenses.get(i).label.equals(label)) {
                    lenses.remove(i);
                }
            }
        }

        public boolean contains(String label) {
            for (Lens lens: lenses) {
                if (lens.label.equals(label)) {
                    return true;
                }
            }

            return false;
        }

        public String toString() {
            StringBuilder str = new StringBuilder("Box " + index+ ": ");

            for (Lens l: lenses) {
                str.append(l+" ");
            }

            return str.toString();
        }

    }
    class Lens {
        public String label;

        public int focalLength;

        public Lens() {
            this("", 0);
        }
        public Lens(String l, int fl) {
            label = l;
            focalLength = fl;
        }

        public String toString() {
            if (label.isEmpty()) {
                return "[_]";
            }
            return "[" + label + " " + focalLength + "]";
        }
    }



    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        line = line.trim();

        if (line.trim().isEmpty()) {
            return res;
        }


        String[] parts = line.split(",");

        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                steps.add(parts[i]);
            }
        }

        return res;
    }

    private void initBoxes() {
        boxes = new Box[256];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box(i);
        }
    }

    public long processLenses() {

        int index;
        for (String step: steps) {
            index = valueOf(labelOf(step));
            if (step.contains("-")) {
                removeLens(step.split("-")[0], index);
            } else if (step.contains("=")) {
                addLens(step.split("=")[0], Integer.parseInt(step.split("=")[1]), index);
            }
        }

        long sum = 0;
        for (int i = 0; i < boxes.length; i++) {
            int boxIndex = i + 1;
            Box box = boxes[i];

            for (int j = 0; j < box.lenses.size(); j++) {
                int lensIndex = j + 1;
                int focalLength = box.lenses.get(j).focalLength;
                if (focalLength == 0) {
                    continue;
                }

                sum += (long) boxIndex * lensIndex * focalLength;
            }
        }

        return sum;
    }


    public void removeLens(String label, int boxIndex) {
        boxes[boxIndex].remove(label);
    }

    public void addLens(String label, int focalLength, int boxIndex) {

        Box box = boxes[boxIndex];

        box.add(label, focalLength);

    }

    @Override
    public List<ParserResult> postProcess(List<ParserResult> parserResults) {

        List<ParserResult> results = new ArrayList<>();

        int sum = 0;

        for (String step: steps) {
            sum += valueOf(step);
        }

        ParserResult res = new ParserResult();
        res.setValue(sum);
        results.add(res);

        return results;
    }

    public String labelOf(String step) {
        if (step.contains("-")) {
            return step.split("-")[0];
        }

        if (step.contains("=")) {
            return step.split("=")[0];
        }

        return step;
    }

    public int valueOf(String step) {
        int currentValue = 0;
        for (int i = 0; i < step.length(); i++) {

            char c = step.charAt(i);

            if (c >= 128) {
                throw new IllegalArgumentException("no ASCII char");
            }

            currentValue += c;
            currentValue *= 17;
            currentValue = currentValue % 256;
        }

        return currentValue;
    }


    public void log(List<String> rows) {
        System.out.println(rows);
    }

    public void logBoxes() {
        for (Box box: boxes) {
            System.out.println(box);
        }
    }

}