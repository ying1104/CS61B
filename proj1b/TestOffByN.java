import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static OffByN oN1 = new OffByN(1);
    static OffByN oN5 = new OffByN(5);


    @Test
    public void testOffByN1() {
        assertTrue(oN1.equalChars('a', 'b'));
        assertTrue(oN1.equalChars('r', 'q'));
        assertFalse(oN1.equalChars('a', 'a'));
        assertFalse(oN1.equalChars('B', 'a'));
        assertTrue(oN1.equalChars('&', '%'));
    }
    @Test
    public void testOffByN5() {
        assertTrue(oN5.equalChars('a', 'f'));
        assertTrue(oN5.equalChars('f', 'a'));
        assertFalse(oN5.equalChars('a', 'a'));
        assertFalse(oN5.equalChars('B', 'a'));
        assertFalse(oN5.equalChars('a', 'F'));
    }
}
