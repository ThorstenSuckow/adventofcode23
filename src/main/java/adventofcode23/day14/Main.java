package adventofcode23.day14;

import adventofcode23.lib.ResourceReader;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day14/input.txt";
        TiltParser parser;
        parser = new TiltParser();

        reader.parseContents(fileName, parser);
        System.out.println("The total load on the north support beams is " + parser.tiltNorth().getSum());


        parser = new TiltParser();
        reader.parseContents(fileName, parser);
        LinkedList<Long> linkedList = new LinkedList<>();
        CycleFinder<Long> cycleFinder = new CycleFinder<>();
        // provide enough epochs to fill the list with cycles
        for (int i = 0; i < 2900; i++) {
            linkedList.add(parser.cycle().getSum());
            cycleFinder.add(linkedList.hasCycle());
        }

        Cycle c = cycleFinder.findUniqueCycle();
        int start = c.start;
        int length = c.length;
        int set = 1_000_000_000;
        long sum = (long) c.getAt((set - start) % length - 1);
        System.out.println("The total load on the north support beams after " + set + " cycles is " + sum);


    }
}
