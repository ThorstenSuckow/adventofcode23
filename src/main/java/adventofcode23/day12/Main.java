package adventofcode23.day12;

import adventofcode23.lib.ResourceReader;


public class Main {


    public static void main(String[] args) {

        ResourceReader reader = new ResourceReader();
        String fileName = "input/day12/input.txt";
        RecordParser parser = new RecordParser();
        reader.parseContents(fileName, parser);


    }
}
