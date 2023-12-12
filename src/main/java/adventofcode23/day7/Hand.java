package adventofcode23.day7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hand implements Comparable<Hand> {

    public static String[] CARD_ORDER = new String[]{
            "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4","3", "2"
    };
    public final static HandType[] HAND_ORDER = new HandType[]{
            HandType.FIVE_OF_A_KIND,
            HandType.FOUR_OF_A_KIND,
            HandType.FULL_HOUSE,
            HandType.THREE_OF_A_KIND,
            HandType.TWO_PAIR,
            HandType.ONE_PAIR,
            HandType.HIGH_CARD
    };

    private final String cards;

    private int bid;

    private Stack stack;

    private HandType type;

    public long getBid() {
        return bid;
    }

    class Stack {

        Map<String, Integer> stack = new HashMap<>();

        String cards;
        public Stack(String s) {
            cards = s;
            for (int i = 0; i < s.length(); i++) {
                add(s.charAt(i));
            }
        }
        public void add(char c) {
            String s = Character.toString(c);
            stack.put(s, stack.get(s) != null ? stack.get(s) + 1 : 1);
        }
        public Collection<Integer> values() {
            return stack.values();
        }
        public int size() {
            return stack.size();
        }
    }

    public static Hand make(String cards) {
        return new Hand(cards);
    }

    public static Hand make(String cards, int bid) {
        return new Hand(cards, bid);
    }

    public Hand(String cards) {
        if (cards.length() != 5) {
            throw new IllegalArgumentException();
        }

        String regex = "^[AKQJT98765432]+$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(cards);

        if (matcher.hasMatch()) {
            throw new IllegalArgumentException();
        }

        this.stack = new Stack(cards);
        this.cards = cards;
        type = getType();
    }
    public Hand(String cards, int bid) {
        this(cards);
        this.bid = bid;
    }

    public HandType getType() {
        if (type != null) {
            return type;
        }

        if (isFiveOfAKind()) {
            return HandType.FIVE_OF_A_KIND;
        }

        if (isFourOfAKind()) {
            return HandType.FOUR_OF_A_KIND;
        }

        if (isThreeOfAKind()) {
            return HandType.THREE_OF_A_KIND;
        }

        if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        }

        if (isTwoPair()) {
            return HandType.TWO_PAIR;
        }

        if (isOnePair()) {
            return HandType.ONE_PAIR;
        }

        if (isHighCard()) {
            return HandType.HIGH_CARD;
        }

        throw new RuntimeException();
    }

    /**
     * Five of a kind, where all five cards have the same label: AAAAA
     * @return
     */
    public boolean isFiveOfAKind() {
        return stack.size() == 1;
    }


    /**
     * Four of a kind, where four cards have the same label
     * and one card has a different label: AA8AA
     * @return
     */
    public boolean isFourOfAKind() {
        return stack.size() == 2
                && stack.values().stream().reduce(1, (a, b) -> a *b) == 4;
    }

    /**
     * Full house, where three cards have the same label,
     * and the remaining two cards share a different label: 23332
     * @return
     */
    public boolean isFullHouse() {
        return stack.size() == 2
                && stack.values().stream().reduce(1, (a, b) -> a *b) == 6;
    }

    /**
     * Three of a kind, where three cards have the same label, and the remaining two
     * cards are each different from any other card in the hand: TTT98
     *
     * @return
     */
    public boolean isThreeOfAKind() {
        return stack.size() == 3
                && stack.values().stream().reduce(1, (a, b) -> a *b) == 3;

    }

    /**
     * Two pair, where two cards share one label, two other cards share a second label,
     * and the remaining card has a third label: 23432
     * @return
     */
    public boolean isTwoPair() {
        return stack.size() == 3
                && stack.values().stream().reduce(1, (a, b) -> a *b) == 4;

    }

    public boolean isOnePair() {
        return stack.size() == 4
                && stack.values().stream().reduce(1, (a, b) -> a *b) == 2;

    }

    public boolean isHighCard() {
        return stack.size() == 5;
    }

    /**
     * Order (strongest to weakest):
     * 1. Five of a kind
     * 2. Four of a kind
     * 3. Full house
     * 4. Three of a kind
     * 5. Two pair
     * 6. One pair
     * 7. High card
     */
    @Override
    public int compareTo(Hand o) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < HAND_ORDER.length; i++) {
            if (HAND_ORDER[i] == getType()) {
                x = i;
            }
            if (HAND_ORDER[i] == o.getType()) {
                y = i;
            }
        }

        if (x == y) {
            return compareCards(cards, o.getCards());
        }

        if (x < y) {
            return 1;
        }

        return -1;

    }

    public int compareCards(String cardsA, String cardsB) {

        String a = Character.toString(cardsA.charAt(0)).toUpperCase();
        String b = Character.toString(cardsB.charAt(0)).toUpperCase();

        int x = 0;
        int y = 0;
        for (int i = 0; i < CARD_ORDER.length; i++) {
            if (CARD_ORDER[i].equals(a)) {
                x = i;
            }
            if (CARD_ORDER[i].equals(b)) {
                y = i;
            }
        }

        if (x == y) {
            if (cardsA.length() == 1) {
                return 0;
            }
            return compareCards(cardsA.substring(1), cardsB.substring(1));
        }

        if (x < y) {
            return 1;
        }

        return -1;

    }

    public String getCards() {
        return cards;
    }

    public String toString() {
        return "[" + getType().toString() + "]:" + cards + ":" + bid;
    }


}
