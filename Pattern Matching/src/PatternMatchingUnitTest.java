import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class PatternMatchingUnitTest {

    private static final int TIMEOUT = 200;

    private String string1 = "qeqerdefgd";
    private String string2 = "abababbabababbab";
    private String string3 = "racecaracecar";
    private String string4 = "qeqerqeqer";
    private String string5 = "a";

    private String pattern1 = "qeqer";
    private String pattern2 = "abab";
    private String pattern3 = "racecar";

    private CharacterComparator comparator;


    @Before
    public void setUp() {
        comparator = new CharacterComparator();
    }

    // 16 Tests for KMP

    @Test(timeout = TIMEOUT)
    public void testKMPFailureTable1() {
        int[] failureTable = PatternMatching
                .buildFailureTable(pattern1, comparator);
        int[] expected = {0, 0, 1, 2, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 5.", 5, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPFailureTable2() {
        int[] failureTable = PatternMatching
                .buildFailureTable(pattern2, comparator);
        int[] expected = {0, 0, 1, 2};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 3.", 3, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPFailureTable3() {
        int[] failureTable = PatternMatching
                .buildFailureTable(pattern3, comparator);
        int[] expected = {0, 0, 0, 0, 0, 0, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 6.", 6, comparator.getComparisonCount());
    }
    @Test(timeout = TIMEOUT)
    public void testKMP1() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        assertEquals(answer, PatternMatching.kmp(pattern1, string1, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 11.", 11, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP2() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(6);
        assertEquals(answer, PatternMatching.kmp(pattern3, string3, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 19.", 19, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP3() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(2);
        answer.add(7);
        answer.add(9);
        assertEquals(answer, PatternMatching.kmp(pattern2, string2, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 18.", 18, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP4() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(5);
        assertEquals(answer, PatternMatching.kmp(pattern1, string4, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 15.", 15, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP5() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer, PatternMatching.kmp(pattern1, string3, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 14.", 14, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP6() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer, PatternMatching.kmp(pattern1, string5, comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 0.", 0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP7() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        assertEquals(answer, PatternMatching.kmp(pattern1, pattern1, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        if (comparator.getComparisonCount() == 5) {
                assertEquals("Comparison count was " + comparator.getComparisonCount()
                        + ". Should be 5 or 10.", 5, comparator.getComparisonCount());
        } else {
                assertEquals("Comparison count was " + comparator.getComparisonCount()
                        + ". Should be 5 or 10.", 10, comparator.getComparisonCount());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKMPError1() {
        PatternMatching.kmp(null, string1, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKMPError2() {
        PatternMatching.kmp(pattern1, null, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKMPError3() {
        PatternMatching.kmp(pattern1, string1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKMPError4() {
        PatternMatching.kmp("", string1, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKMPFailureError1() {
        PatternMatching.buildFailureTable(null, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKMPFailureError2() {
        PatternMatching.buildFailureTable(pattern1, null);
    }

    // 15 Tests for Boyer-Moore

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence1() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(pattern1);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('q', 2);
        expectedLastTable.put('e', 3);
        expectedLastTable.put('r', 4);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence2() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(pattern2);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('a', 2);
        expectedLastTable.put('b', 3);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence3() {
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(pattern3);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('e', 3);
        expectedLastTable.put('c', 4);
        expectedLastTable.put('a', 5);
        expectedLastTable.put('r', 6);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBM1() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(6);
        assertEquals(answer,
                PatternMatching.boyerMoore(pattern3, string3, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 17.", 17, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM2() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        assertEquals(answer,
                PatternMatching.boyerMoore(pattern1, string1, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 6.", 6, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM3() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(2);
        answer.add(7);
        answer.add(9);
        assertEquals(answer,
                PatternMatching.boyerMoore(pattern2, string2, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 33.", 33, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM4() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(5);
        assertEquals(answer,
                PatternMatching.boyerMoore(pattern1, string4, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 12.", 12, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM5() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer,
                PatternMatching.boyerMoore(pattern1, string3, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 3.", 3, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM6() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer, PatternMatching.boyerMoore(pattern1, string5, comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 0.", 0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM7() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        assertEquals(answer, PatternMatching.boyerMoore(pattern1, pattern1, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 5.", 5, comparator.getComparisonCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBMError1() {
        PatternMatching.boyerMoore(null, string1, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBMError2() {
        PatternMatching.boyerMoore(pattern1, null, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBMError3() {
        PatternMatching.boyerMoore(pattern1, string1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBMError4() {
        PatternMatching.boyerMoore("", string1, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBMETableError1() {
        PatternMatching.buildLastTable(null);
    }

    // 9 Tests for Rabin-Karp

    @Test(timeout = TIMEOUT)
    public void testRabinKarp1() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(3);
        assertEquals(answer,
                PatternMatching.rabinKarp("ab", "abcabde", comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp2() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(2);
        assertEquals(answer,
                PatternMatching.rabinKarp("ab", "ababdebdba", comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp3() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer,
                PatternMatching.rabinKarp("qq", "r\u0000", comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 1.", 1, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp4() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer,
                PatternMatching.rabinKarp("sdcm", "sdcs", comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 0.", 0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp5() {
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer,
                PatternMatching.rabinKarp("\u0002\u0002", "\u0001\u0073", comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 1.", 1, comparator.getComparisonCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRKError1() {
        PatternMatching.rabinKarp(null, string1, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRKError2() {
        PatternMatching.rabinKarp(pattern1, null, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRKError3() {
        PatternMatching.rabinKarp(pattern1, string1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRKError4() {
        PatternMatching.rabinKarp("", string1, comparator);
    }
    
}