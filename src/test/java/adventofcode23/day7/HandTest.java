package adventofcode23.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {


    @Test
    @DisplayName("isFiveOfAKind")
    public void testIsFiveOfAKind() {

        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("AAAAA").getType());
        assertTrue(Hand.make("AAAAA").isFiveOfAKind());
        assertFalse(Hand.make("AAKAA").isFiveOfAKind());

    }


    @Test
    @DisplayName("isFourOfAKind")
    public void testIsFourOfAKind() {

        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("AAKAA").getType());
        assertTrue(Hand.make("AAKAA").isFourOfAKind());
        assertFalse(Hand.make("AAKAK").isFourOfAKind());

    }


    @Test
    @DisplayName("isFullHouse")
    public void testIsFullHouse() {

        assertSame(HandType.FULL_HOUSE, Hand.make("KAKAK").getType());
        assertFalse(Hand.make("AQKAA").isFullHouse());
        assertTrue(Hand.make("KAKAK").isFullHouse());

    }

    @Test
    @DisplayName("isThreeOfAKind")
    public void testIsThreeOfAKind() {

        assertSame(HandType.THREE_OF_A_KIND, Hand.make("AQKAA").getType());
        assertTrue(Hand.make("AQKAA").isThreeOfAKind());
        assertFalse(Hand.make("KAKAK").isThreeOfAKind());
    }

    @Test
    @DisplayName("isTwoPair")
    public void testIsTwoPair() {

        assertSame(HandType.TWO_PAIR, Hand.make("KQKAA").getType());
        assertTrue(Hand.make("KQKAA").isTwoPair());
        assertFalse(Hand.make("KAKAK").isTwoPair());
    }

    @Test
    @DisplayName("isOnePair")
    public void testIsOnePair() {

        assertSame(HandType.ONE_PAIR, Hand.make("JQKAA").getType());
        assertTrue(Hand.make("JQKAA").isOnePair());
        assertFalse(Hand.make("AAQAK").isOnePair());
    }


    @Test
    @DisplayName("isHighCard")
    public void testIsHighCard() {

        assertSame(HandType.HIGH_CARD, Hand.make("AKQJT").getType());
        assertTrue(Hand.make("AKQJT").isHighCard());
        assertFalse(Hand.make("AKQTT").isHighCard());
    }

    @Test
    @DisplayName("compareTo")
    public void testCompareTo() {

        assertSame(1, Hand.make("88777").compareTo(Hand.make("78788")));
        assertSame(-1, Hand.make("AKAKK").compareTo(Hand.make("AAKKK")));
        assertSame(1, Hand.make("KKKKK").compareTo(Hand.make("AKAKK")));
        assertSame(-1, Hand.make("AKQJT").compareTo(Hand.make("AKAKK")));

        assertSame(1, Hand.make("33332").compareTo(Hand.make("2AAAA")));
        assertSame(1, Hand.make("77888").compareTo(Hand.make("77788")));
        assertSame(-1, Hand.make("77788").compareTo(Hand.make("77888")));

        assertSame(0, Hand.make("77788").compareTo(Hand.make("77788")));
        assertSame(1, Hand.make("77789").compareTo(Hand.make("77768")));

    }
}
