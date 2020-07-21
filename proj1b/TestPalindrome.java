import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("raar"));
        assertFalse(palindrome.isPalindrome("brab"));
        assertFalse(palindrome.isPalindrome("Racecar"));
    }

    @Test
    public void testOverloadedIsPalindrome() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("qabr", cc));
        assertTrue(palindrome.isPalindrome("qacbr", cc));
        assertFalse(palindrome.isPalindrome("caab", cc));
        assertFalse(palindrome.isPalindrome("qbcbd", cc));
    }
}
