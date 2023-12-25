package adventofcode23.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {



    @Test
    @DisplayName("isFiveOfAKind")
    public void testIsFiveOfAKind() {

        Hand.USE_JOKER = false;
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("AAAAA").getType());
        assertTrue(Hand.make("AAAAA").isFiveOfAKind());
        assertFalse(Hand.make("AAKAA").isFiveOfAKind());

    }


    @Test
    @DisplayName("isFourOfAKind")
    public void testIsFourOfAKind() {

        Hand.USE_JOKER = false;
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("AAKAA").getType());
        assertTrue(Hand.make("AAKAA").isFourOfAKind());
        assertFalse(Hand.make("AAKAK").isFourOfAKind());

    }


    @Test
    @DisplayName("isFullHouse")
    public void testIsFullHouse() {

        Hand.USE_JOKER = false;
        assertSame(HandType.FULL_HOUSE, Hand.make("KAKAK").getType());
        assertFalse(Hand.make("AQKAA").isFullHouse());
        assertTrue(Hand.make("KAKAK").isFullHouse());

    }

    @Test
    @DisplayName("isThreeOfAKind")
    public void testIsThreeOfAKind() {

        Hand.USE_JOKER = false;
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("AQKAA").getType());
        assertTrue(Hand.make("AQKAA").isThreeOfAKind());
        assertFalse(Hand.make("KAKAK").isThreeOfAKind());
    }

    @Test
    @DisplayName("isTwoPair")
    public void testIsTwoPair() {

        Hand.USE_JOKER = false;
        assertSame(HandType.TWO_PAIR, Hand.make("KQKAA").getType());
        assertTrue(Hand.make("KQKAA").isTwoPair());
        assertFalse(Hand.make("KAKAK").isTwoPair());
    }

    @Test
    @DisplayName("isOnePair")
    public void testIsOnePair() {

        Hand.USE_JOKER = false;
        assertSame(HandType.ONE_PAIR, Hand.make("JQKAA").getType());
        assertTrue(Hand.make("JQKAA").isOnePair());
        assertFalse(Hand.make("AAQAK").isOnePair());
    }


    @Test
    @DisplayName("isHighCard")
    public void testIsHighCard() {

        Hand.USE_JOKER = false;
        assertSame(HandType.HIGH_CARD, Hand.make("AKQJT").getType());
        assertTrue(Hand.make("AKQJT").isHighCard());
        assertFalse(Hand.make("AKQTT").isHighCard());
    }

    @Test
    @DisplayName("compareTo")
    public void testCompareTo() {

        Hand.USE_JOKER = false;
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


    @Test
    @DisplayName("joker")
    public void testJoker() {

        Hand.USE_JOKER = true;
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("KJAAA").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJAAA").getType());
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("JKCAA").getType());

        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJJJ").getType());

        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJ99").getType());

        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("JKKK2").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("QQQQ2").getType());

        assertSame(HandType.ONE_PAIR, Hand.make("ABCDJ").getType());
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("ABBDJ").getType());

        assertSame(HandType.FULL_HOUSE, Hand.make("JK7K7").getType());
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("J4Q33").getType());

        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("TT2TT").getType());

        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJJJ").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJJA").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJAA").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("JJFAA").getType());
        assertSame(HandType.FULL_HOUSE, Hand.make("JFFAA").getType());
        assertSame(HandType.ONE_PAIR, Hand.make("ABCDJ").getType());
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("ABCJJ").getType());

        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJJ2").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("T55J5").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("KTJJT").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("QQQJA").getType());

        assertSame(HandType.FULL_HOUSE, Hand.make("QQKKJ").getType());

        assertSame(HandType.HIGH_CARD, Hand.make("12345").getType());

        assertSame(HandType.ONE_PAIR, Hand.make("12344").getType());
        assertSame(HandType.ONE_PAIR, Hand.make("1234J").getType());

        assertSame(HandType.TWO_PAIR, Hand.make("12233").getType());

        assertSame(HandType.THREE_OF_A_KIND, Hand.make("12333").getType());
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("1233J").getType());
        assertSame(HandType.THREE_OF_A_KIND, Hand.make("123JJ").getType());

        assertSame(HandType.FULL_HOUSE, Hand.make("11222").getType());
        assertSame(HandType.FULL_HOUSE, Hand.make("1122J").getType());

        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("12222").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("1222J").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("122JJ").getType());
        assertSame(HandType.FOUR_OF_A_KIND, Hand.make("12JJJ").getType());

        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("11111").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("1111J").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("111JJ").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("11JJJ").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("1JJJJ").getType());
        assertSame(HandType.FIVE_OF_A_KIND, Hand.make("JJJJJ").getType());

        assertSame(1, Hand.make("QQQQ2").compareTo(Hand.make("JKKK2")));
    }
}
