import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;




/**
 * This is a basic set of unit tests for Sorting.
 *
 *
 * I have stolen code from the TA's and added some of my own tests.
 * These tests don't make use of the comparators at all so I can't guarantee your code's efficiency/adaptability.
 * Aarsh's tests have some great efficiency/adaptability testing so definitely check his code out.
 *
 * I modified the TeachingAssistant class to allow for stability testing.
 * Stability testing should ensure that you're not swapping around duplicate elements.
 * [ ... 6a, 6b ... ] should remain [ ... 6a, 6b ... ] when sorted (except for QuickSort)
 *
 *
 * If you find any errors within the tests, please let me know on piazza, groupMe, LinkedIn, or carrier pigeon.
 *
 * Thank you for testing your code using my tests (◕ ‿ ◕)
 *
 *
 *
 * @author Akash Vemulapalli
 * @version 1.0
 */
public class SortingAkashTests {

    private static final int TIMEOUT = 200;
    private TeachingAssistant[] tas;
    private TeachingAssistant[] tasByName;
    private ComparatorPlus<TeachingAssistant> comp;

    private TeachingAssistant[] people;
    private TeachingAssistant[] peopleSorted;
    private TeachingAssistant[] peopleSorted2;
    private Comparator<TeachingAssistant> compNums;

    private TeachingAssistant[] professors;
    private TeachingAssistant[] professorsSorted;
    private TeachingAssistant[] professorsSorted2;
    private Comparator<TeachingAssistant> compProfessors;



    @Before
    public void setUp() {
        /*
            Unsorted Names:
                index 0: Alvin
                index 1: Marie
                index 2: Reece
                index 3: Sidhesh
                index 4: Isaac
                index 5: Destini
                index 6: Yash
                index 7: Saket
                index 8: John
                index 9: Hannah
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

        people = new TeachingAssistant[6];

        people[0] = new TeachingAssistant("Jose");
        people[1] = new TeachingAssistant("Aadit", "a");
        people[2] = new TeachingAssistant("Aadit", "b");
        people[3] = new TeachingAssistant("Jeff");
        people[4] = new TeachingAssistant("Kevin");
        people[5] = new TeachingAssistant("Akash");

        peopleSorted = new TeachingAssistant[6];
        peopleSorted[0] = people[1];
        peopleSorted[1] = people[2];
        peopleSorted[2] = people[5];
        peopleSorted[3] = people[3];
        peopleSorted[4] = people[0];
        peopleSorted[5] = people[4];

        peopleSorted2 = new TeachingAssistant[6];
        peopleSorted2[0] = new TeachingAssistant("Aadit");
        peopleSorted2[1] = new TeachingAssistant("Aadit");
        peopleSorted2[2] = people[5];
        peopleSorted2[3] = people[3];
        peopleSorted2[4] = people[0];
        peopleSorted2[5] = people[4];


        professors = new TeachingAssistant[13];

        professors[0] = new TeachingAssistant("Mark Moss", "CS1332");
        professors[1] = new TeachingAssistant("Mark Moss", "CS4400");
        professors[2] = new TeachingAssistant("Mark Moss", "CS2340");
        professors[3] = new TeachingAssistant("Mark Moss", "CS2110");
        professors[4] = new TeachingAssistant("Richard Landry");
        professors[5] = new TeachingAssistant("Monica Sweat");
        professors[6] = new TeachingAssistant("Charles Isbell");
        professors[7] = new TeachingAssistant("Ashok Goel");
        professors[8] = new TeachingAssistant("Aibek Musaev");
        professors[9] = new TeachingAssistant("Dan Forsyth");
        professors[10] = new TeachingAssistant("Caleb Southern");
        professors[11] = new TeachingAssistant("Tom Conte");
        professors[12] = new TeachingAssistant("Merrick Furst");

        /*
            Sorted Names:
                index 0: Aibek Musaev
                index 1: Ashok Goel
                index 2: Caleb Southern
                index 3: Charles Isbell
                index 4: Dan Forsyth
                index 5: Mark Moss CS 1332
                index 6: Mark Moss CS 4400
                index 7: Mark Moss CS 2340
                index 8: Mark Moss CS 2110
                index 9: Merrick Furst
                index 10: Monica Sweat
                index 11: Richard Landry
                index 12: Tom Conte
         */

        professorsSorted = new TeachingAssistant[13];
        professorsSorted[0] = professors[8];
        professorsSorted[1] = professors[7];
        professorsSorted[2] = professors[10];
        professorsSorted[3] = professors[6];
        professorsSorted[4] = professors[9];
        professorsSorted[5] = professors[0];
        professorsSorted[6] = professors[1];
        professorsSorted[7] = professors[2];
        professorsSorted[8] = professors[3];
        professorsSorted[9] = professors[12];
        professorsSorted[10] = professors[5];
        professorsSorted[11] = professors[4];
        professorsSorted[12] = professors[11];

        professorsSorted2 = new TeachingAssistant[13];
        professorsSorted2[0] = professors[8];
        professorsSorted2[1] = professors[7];
        professorsSorted2[2] = professors[10];
        professorsSorted2[3] = professors[6];
        professorsSorted2[4] = professors[9];
        professorsSorted2[5] = new TeachingAssistant("Mark Moss");
        professorsSorted2[6] = new TeachingAssistant("Mark Moss");
        professorsSorted2[7] = new TeachingAssistant("Mark Moss");
        professorsSorted2[8] = new TeachingAssistant("Mark Moss");
        professorsSorted2[9] = professors[12];
        professorsSorted2[10] = professors[5];
        professorsSorted2[11] = professors[4];
        professorsSorted2[12] = professors[11];


        compProfessors = TeachingAssistant.getNameComparator();
        compNums = TeachingAssistant.getNameComparator();
        comp = TeachingAssistant.getNameComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort2() {
        Sorting.insertionSort(people, comp);
        assertArrayEquals(peopleSorted, people);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort3() {
        Sorting.insertionSort(professors, comp);
        assertArrayEquals(professorsSorted, professors);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        Sorting.cocktailSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 33 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort2() {
        Sorting.cocktailSort(people, comp);
        assertArrayEquals(peopleSorted, people);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort3() {
        Sorting.cocktailSort(professors, comp);
        assertArrayEquals(professorsSorted, professors);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 24 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort2() {
        Sorting.mergeSort(people, comp);
        assertArrayEquals(peopleSorted, people);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort3() {
        Sorting.mergeSort(professors, comp);
        assertArrayEquals(professorsSorted, professors);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, -85, 3};
        int[] sortedArray = new int[] {-85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort2() {
        int[] unsortedArray = new int[] {54, 54, 58, 84, 20, 122, -85, 3};
        int[] sortedArray = new int[] {-85, 3, 20, 54, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort3() {
        int[] unsortedArray = new int[] {54, 54, 54, 54, 54, 54, -54, 54};
        int[] sortedArray = new int[] {-54, 54, 54, 54, 54, 54, 54, 54};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort4() {
        int[] unsortedArray = new int[] {-1006, -50, -999, 99, 54, 42, 14, 77};
        int[] sortedArray = new int[] {-1006, -999, -50, 14, 42, 54, 77, 99};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort5() {
        int[] unsortedArray = new int[] {-42, -77, -777, -5, -10, -39, 0, -2};
        int[] sortedArray = new int[] {-777, -77, -42, -39, -10, -5, -2, 0};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort6() {
        int[] unsortedArray = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, -2, 50, Integer.MAX_VALUE, 42, 0, 7};
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -2, 0, 7, 42, 50, Integer.MAX_VALUE, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        Sorting.quickSort(tas, comp, new Random(234));
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort2() {

        Sorting.quickSort(people, comp, new Random(277));
        assertArrayEquals(peopleSorted2, peopleSorted);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort3() {
        Sorting.quickSort(professors, comp, new Random(500));
        assertArrayEquals(professorsSorted2, professorsSorted);
    }

    //Now for all the null value tests

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testInsertionSortNullInput1() {
        Sorting.insertionSort(null, comp);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testInsertionSortNullInput2() {
        Sorting.insertionSort(null, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testInsertionSortNullInput3() {
        Sorting.insertionSort(tas, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testCocktailSortNullInput1() {
        Sorting.cocktailSort(null, comp);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testCocktailSortNullInput2() {
        Sorting.cocktailSort(null, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testCocktailSortNullInput3() {
        Sorting.cocktailSort(tas, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testMergeSortNullInput1() {
        Sorting.mergeSort(null, comp);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testMergeSortNullInput2() {
        Sorting.mergeSort(null, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testMergeSortNullInput3() {
        Sorting.mergeSort(tas, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testRadixSortNullInput() {
        Sorting.lsdRadixSort(null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNullInput1() {
        Sorting.quickSort(null, comp, new Random(1460));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNullInput2() {
        Sorting.quickSort(tas, null, new Random(1460));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testQuickSortNullInput3() {
        Sorting.quickSort(tas, comp, null);
    }



    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private String name;
        private String version;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         */
        public TeachingAssistant(String name) {
            this.name = name;
        }


        /**
         * Create a teaching assistant to check for stability
         * @param name Name of teaching assistant
         * @param version A way to check for stability
         */
        public TeachingAssistant(String name, String version) {
            this.name = name;
            this.version = version;
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
            if (this.version == null || ((TeachingAssistant) other).version == null) {
                return other instanceof TeachingAssistant
                        && ((TeachingAssistant) other).name.equals(this.name);
            }
            return other instanceof TeachingAssistant
                    && ((TeachingAssistant) other).name.equals(this.name)
                    && ((TeachingAssistant) other).version.equals(this.version);


            /*
            if (other instanceof TeachingAssistant
                    && ((TeachingAssistant) other).name.equals(this.name)
                    && ((TeachingAssistant) other).version.equals(this.version)) {
                return true;
            } else {
                return false;
            }*/

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
}