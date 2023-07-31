import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

/**
 * These Tests cover some of the edge cases I was able to think of.
 *
 * If you find any errors within the tests, please let me know on Piazza.
 *
 * Thank you for testing your code using my tests :)
 *
 * @author Aarsh Patel
 * @version 1.0
 */
public class SortingTestExtra {

    private static final int TIMEOUT = 200;
    private TeachingAssistant[] tas;
    private TeachingAssistant[] tasByName;
    private TeachingAssistant[] taSorted;
    private TeachingAssistant[] taReversed;
    private ComparatorPlus<TeachingAssistant> comp;

    private NewIntegers[] ints;
    private NewIntegers[] intsSorted;
    private NewIntegers[] intsSortedOut;
    private NewIntegers[] intsCocktail;
    private NewIntegers[] intsMergeSort;
    private NewIntegers[] intsMergeSorted;
    private ComparatorPlus2<NewIntegers> stableComp;

    @Before
    public void setUp() {
        /*
            Unsorted Names:
                index 0: Alvin -> 1
                index 1: Marie -> 6
                index 2: Reece -> 7
                index 3: Sidhesh -> 9
                index 4: Isaac -> 4
                index 5: Destini -> 2
                index 6: Yash -> 10
                index 7: Saket -> 8
                index 8: John -> 5
                index 9: Hannah -> 3
         */

        /*
            Sorted Names:
                index 0: Alvin
                index 1: Destini
                index 2: Hannah
                index 3: Isaac
                index 4: John
                index 5: Marie
                index 6: Reece
                index 7: Saket
                index 8: Sidhesh
                index 9: Yash
         */

        tas = new TeachingAssistant[10];
        tas[0] = new TeachingAssistant("Alvin"); //0
        tas[1] = new TeachingAssistant("Marie"); //5
        tas[2] = new TeachingAssistant("Reece"); //6
        tas[3] = new TeachingAssistant("Sidhesh"); //8
        tas[4] = new TeachingAssistant("Isaac"); //3
        tas[5] = new TeachingAssistant("Destini"); //1
        tas[6] = new TeachingAssistant("Yash"); //9
        tas[7] = new TeachingAssistant("Saket"); //7
        tas[8] = new TeachingAssistant("John"); //4
        tas[9] = new TeachingAssistant("Hannah"); //2
        tasByName = new TeachingAssistant[10];
        tasByName[0] = tas[0];
        tasByName[1] = tas[5];
        tasByName[2] = tas[9];
        tasByName[3] = tas[4];
        tasByName[4] = tas[8];
        tasByName[5] = tas[1];
        tasByName[6] = tas[2];
        tasByName[7] = tas[7];
        tasByName[8] = tas[3];
        tasByName[9] = tas[6];

        taSorted = new TeachingAssistant[10];
        taSorted[0] = tas[0];
        taSorted[1] = tas[5];
        taSorted[2] = tas[9];
        taSorted[3] = tas[4];
        taSorted[4] = tas[8];
        taSorted[5] = tas[1];
        taSorted[6] = tas[2];
        taSorted[7] = tas[7];
        taSorted[8] = tas[3];
        taSorted[9] = tas[6];

        taReversed = new TeachingAssistant[10];
        taReversed[0] = tasByName[9];
        taReversed[1] = tasByName[8];
        taReversed[2] = tasByName[7];
        taReversed[3] = tasByName[6];
        taReversed[4] = tasByName[5];
        taReversed[5] = tasByName[4];
        taReversed[6] = tasByName[3];
        taReversed[7] = tasByName[2];
        taReversed[8] = tasByName[1];
        taReversed[9] = tasByName[0];

        comp = TeachingAssistant.getNameComparator();

        ints = new NewIntegers[10];
        ints[0] = new NewIntegers("1");
        ints[1] = new NewIntegers("7a");
        ints[2] = new NewIntegers("19");
        ints[3] = new NewIntegers("12");
        ints[4] = new NewIntegers("3");
        ints[5] = new NewIntegers("256");
        ints[6] = new NewIntegers("7b");
        ints[7] = new NewIntegers("2");
        ints[8] = new NewIntegers("7c");
        ints[9] = new NewIntegers("5");

        intsSorted = new NewIntegers[10];
        intsSorted[0] = ints[0];
        intsSorted[1] = ints[7];
        intsSorted[2] = ints[4];
        intsSorted[3] = ints[9];
        intsSorted[4] = ints[1];
        intsSorted[5] = ints[6];
        intsSorted[6] = ints[8];
        intsSorted[7] = ints[3];
        intsSorted[8] = ints[2];
        intsSorted[9] = ints[5];

        intsSortedOut = new NewIntegers[10];
        intsSortedOut[0] = ints[0];
        intsSortedOut[1] = ints[7];
        intsSortedOut[2] = ints[4];
        intsSortedOut[3] = ints[9];
        intsSortedOut[4] = ints[6];
        intsSortedOut[5] = ints[1];
        intsSortedOut[6] = ints[8];
        intsSortedOut[7] = ints[3];
        intsSortedOut[8] = ints[2];
        intsSortedOut[9] = ints[5];

        intsCocktail = new NewIntegers[10];
        intsCocktail[0] = intsSorted[0];
        intsCocktail[1] = intsSorted[1];
        intsCocktail[2] = intsSorted[3];
        intsCocktail[3] = intsSorted[2];
        intsCocktail[4] = intsSorted[4];
        intsCocktail[5] = intsSorted[5];
        intsCocktail[6] = intsSorted[6];
        intsCocktail[7] = intsSorted[7];
        intsCocktail[8] = intsSorted[8];
        intsCocktail[9] = intsSorted[9];

        intsMergeSort = new NewIntegers[10];
        intsMergeSort[0] = new NewIntegers("1a");
        intsMergeSort[1] = new NewIntegers("7a");
        intsMergeSort[2] = new NewIntegers("19a");
        intsMergeSort[3] = new NewIntegers("12a");
        intsMergeSort[4] = new NewIntegers("3a");
        intsMergeSort[5] = new NewIntegers("1b");
        intsMergeSort[6] = new NewIntegers("7b");
        intsMergeSort[7] = new NewIntegers("19b");
        intsMergeSort[8] = new NewIntegers("12b");
        intsMergeSort[9] = new NewIntegers("3b");

        intsMergeSorted = new NewIntegers[10];
        intsMergeSorted[0] = intsMergeSort[0];
        intsMergeSorted[1] = intsMergeSort[5];
        intsMergeSorted[2] = intsMergeSort[4];
        intsMergeSorted[3] = intsMergeSort[9];
        intsMergeSorted[4] = intsMergeSort[1];
        intsMergeSorted[5] = intsMergeSort[6];
        intsMergeSorted[6] = intsMergeSort[3];
        intsMergeSorted[7] = intsMergeSort[8];
        intsMergeSorted[8] = intsMergeSort[2];
        intsMergeSorted[9] = intsMergeSort[7];

        stableComp = NewIntegers.getIntComparator();
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testInsertionSortNull1() {
        Sorting.insertionSort(tas, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testInsertionSortNull2() {
        Sorting.insertionSort(null, comp);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testInsertionSortNull3() {
        Sorting.insertionSort(null, null);
    }

    @Test(timeout = TIMEOUT)
    //test best case for Insertion Sort
    public void testInsertionSort1() {
        Sorting.insertionSort(taSorted, comp);
        assertArrayEquals(tasByName, taSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 9 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test worst case for Insertion Sort
    public void testInsertionSort2() {
        Sorting.insertionSort(taReversed, comp);
        assertArrayEquals(tasByName, taReversed);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test stability for Insertion Sort
    public void testInsertionSortStable() {
        Sorting.insertionSort(ints, stableComp);
        assertArrayEquals(intsSorted, ints);
        assertTrue("Number of comparisons: " + stableComp.getCount(),
                stableComp.getCount() <= 31 && stableComp.getCount() != 0);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testCocktailSortNull1() {
        Sorting.cocktailSort(tas, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testCocktailSortNull2() {
        Sorting.cocktailSort(null, comp);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testCocktailSortNull3() {
        Sorting.cocktailSort(null, null);
    }
    @Test(timeout = TIMEOUT)
    //cocktail sort best case
    public void testCocktailSort1() {
        Sorting.cocktailSort(taSorted, comp);
        assertArrayEquals(tasByName, taSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 9 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //cocktail sort worst case
    public void testCocktailSort2() {
        Sorting.cocktailSort(taReversed, comp);
        assertArrayEquals(tasByName, taSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //cocktail sort last swap optimization case test case
    public void testCocktailSort3() {
        Sorting.cocktailSort(intsCocktail, stableComp);
        assertArrayEquals(intsSorted, intsCocktail);
        assertTrue("Number of comparisons: " + stableComp.getCount(),
                stableComp.getCount() <= 11 && stableComp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test stability for Insertion Sort
    public void testCocktailSortStable() {
        Sorting.cocktailSort(ints, stableComp);
        assertArrayEquals(intsSorted, ints);
        assertTrue("Number of comparisons: " + stableComp.getCount(),
                stableComp.getCount() <= 35 && stableComp.getCount() != 0);
    }



    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testMergeSortNull1() {
        Sorting.mergeSort(tas, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testMergeSortNull2() {
        Sorting.mergeSort(null, comp);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testMergeSortNull3() {
        Sorting.mergeSort(null, null);
    }

    @Test(timeout = TIMEOUT)
    //test Merge Sort with Reversed Array
    public void testMergeSort1() {
        Sorting.mergeSort(taReversed, comp);
        assertArrayEquals(tasByName, taReversed);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 19 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test Merge Sort with Sorted Array
    public void testMergeSort2() {
        Sorting.mergeSort(taSorted, comp);
        assertArrayEquals(tasByName, taSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 15 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test Merge Sort Stability
    public void testMergeSort3() {
        Sorting.mergeSort(ints, stableComp);
        assertArrayEquals(intsSorted, ints);
        assertTrue("Number of comparisons: " + stableComp.getCount(),
                stableComp.getCount() <= 23 && stableComp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test when merging two equal arrays
    public void testMergeSortEdgeCase1() {
        Sorting.mergeSort(intsMergeSort, stableComp);
        assertArrayEquals(intsMergeSorted, intsMergeSort);
        assertTrue("Number of comparisons: " + stableComp.getCount(),
                stableComp.getCount() <= 30 && stableComp.getCount() != 0);
    }


    /**
     * LSD Radix Sort Tests
     */

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testLsdRadixSortNull() {
        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIMEOUT)
    //tests if negative number has largest magnitude
    public void testLsdRadixSortEdgeCase1() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, -85, 3, -1024};
        int[] sortedArray = new int[] {-1024, -85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }
    @Test(timeout = TIMEOUT)
    //tests if largest number is 0
    public void testLsdRadixSortEdgeCase2() {
        int[] unsortedArray = new int[] {-124, -256, -512, -2, 0, -35, -8};
        int[] sortedArray = new int[] {-512, -256, -124, -35, -8, -2, 0};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    //tests if largest number is negative
    public void testLsdRadixSortEdgeCase3() {
        int[] unsortedArray = new int[] {-124, -256, -512, -2, -5987, -35, -8};
        int[] sortedArray = new int[] {-5987, -512, -256, -124, -35, -8, -2};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    //tests using very large and small numbers
    public void testLsdRadixSortEdgeCase4() {
        int[] unsortedArray = new int[] {Integer.MAX_VALUE, -124, -256, -512, -2, 0, -35, -8, Integer.MIN_VALUE};
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -512, -256, -124, -35, -8, -2, 0, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    //tests using very small numbers only
    public void testLsdRadixSortEdgeCase5() {
        int[] unsortedArray = new int[] {-124, -256, -512, -2, 10, -35, -8, Integer.MIN_VALUE};
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -512, -256, -124, -35, -8, -2, 10};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    /**
     * In-place Quick Sort Tests
     */

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNull1() {
        Sorting.quickSort(tas, null, new Random(234));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNull2() {
        Sorting.quickSort(null, comp, new Random(234));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNull3() {
        Sorting.quickSort(null, null, new Random(234));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNull4() {
        Sorting.quickSort(tas, comp, null);
    }

    @Test(timeout = TIMEOUT)
    //test instability in QuickSort
    public void testQuickSort1() {
        Sorting.quickSort(ints, stableComp, new Random(234));
        assertArrayEquals(intsSortedOut, ints);
        assertTrue("Number of comparisons: " + stableComp.getCount(),
                stableComp.getCount() <= 33 && stableComp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test reverse sorted list on QuickSort
    public void testQuickSort2() {
        Sorting.quickSort(taReversed, comp, new Random(234));
        assertArrayEquals(tasByName, taReversed);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 33 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    //test already sorted list on QuickSort
    public void testQuickSort3() {
        Sorting.quickSort(taSorted, comp, new Random(234));
        assertArrayEquals(tasByName, taSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 24 && comp.getCount() != 0);
    }


    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private String name;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         */
        public TeachingAssistant(String name) {
            this.name = name;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getName() {
            return name;
        }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus<TeachingAssistant> getNameComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                                   TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
                }
            };
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof TeachingAssistant
                    && ((TeachingAssistant) other).name.equals(this.name);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }




    /**
            * Class for testing proper stable sorting.
     */
    private static class NewIntegers {
        private String newInt;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         */
        public NewIntegers(String name) {
            this.newInt = name;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getNewInt() {
            return newInt;
        }

        public int size() { return newInt.length(); }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus2<NewIntegers> getIntComparator() {
            return new ComparatorPlus2<NewIntegers>() {
                @Override
                public int compare(NewIntegers num1,
                                   NewIntegers num2) {
                    incrementCount();
                    String newNum1 = new String();
                    String newNum2 = new String();
                    for (int i = 0; i < num1.size(); i++) {
                        if (num1.getNewInt().charAt(i) < 97) {
                            newNum1 += num1.getNewInt().substring(i, i + 1);
                        } else {
                            break;
                        }
                    }
                    for (int i = 0; i < num2.size(); i++) {
                        if (num2.getNewInt().charAt(i) < 97) {
                            newNum2 += num2.getNewInt().substring(i, i + 1);
                        } else {
                            break;
                        }
                    }
                    int intNewNum1 = Integer.parseInt(newNum1);
                    int intNewNum2 = Integer.parseInt(newNum2);
                    return intNewNum1 - intNewNum2;
                }
            };
        }

        @Override
        public String toString() {
            return "Integer: " + newInt;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof NewIntegers
                    && ((NewIntegers) other).newInt.equals(this.newInt);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus2<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}