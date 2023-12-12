package adventofcode23.day7;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.util.*;

public class HandParser extends Parser {


    private Map<Hand, Integer> hands = new HashMap<>();

    private final Map<HandType, List<Hand>> sets = new HashMap<>();


    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        // [77888] [123]
        String[] parts = line.split(" ");

        Hand hand = Hand.make(parts[0], Integer.valueOf(parts[parts.length - 1]));
        HandType type = hand.getType();

        sets.computeIfAbsent(type, k -> new ArrayList<>());
        sets.get(type).add(hand);

        return res;
    }

    public long computeTotalWinnings() {

        long sum = 0;
        int i = 1;
        List<Hand> sortedHands = sortHands();
        for (Hand hand: sortedHands) {
            sum += hand.getBid() * (i++);
        }

        return sum;
    }


    public List<Hand> sortHands() {

        List<Hand> sortedHands = new ArrayList<>();

        for (Map.Entry<HandType, List<Hand>> entry: sets.entrySet()) {
            List<Hand> set = entry.getValue();

            set.sort((o1, o2) -> o1.compareTo(o2));
        }

        HandType[] types = Hand.HAND_ORDER;

        for (int i = types.length - 1; i >= 0; i--) {
            if (sets.get(types[i]) != null) {
                List<Hand> set = sets.get(types[i]);

                sortedHands.addAll(set);

            }
        }

        return sortedHands;
    }


}
