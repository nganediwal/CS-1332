import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is an updated set of unit tests for AVL based on the original TA version.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs, Tanay Sonthalia
 * @version 2.0
 */
public class AVLStudentt {

    private static final int TIMEOUT = 200;
    private AVL<Integer> tree;

    @Before
    public void setup() {
        tree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        /*
              1
             / \
            0   2
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(0);
        toAdd.add(2);
        tree = new AVL<>(toAdd);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testConstructorWithNullList() {
        List<Integer> toAdd = null;

        tree = new AVL<>(toAdd);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testConstructorWithNullDataInList() {
        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(4);
        toAdd.add(null);
        toAdd.add(3);

        tree = new AVL<>(toAdd);
    }

    @Test(timeout = TIMEOUT)
    public void testAddNoRotate() {
        /*

              1
             / \
            0   2
         */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightRotate() {
        // Right rotate
        /*
                2
               /
              1
             /
            0

            ->

              1
             / \
            0   2
         */

        tree.add(2);
        tree.add(1);
        tree.add(0);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRotate() {
        // Left rotate
        /*
            0
             \
              1
               \
                2

            ->

              1
             / \
            0   2
         */

        tree.add(0);
        tree.add(1);
        tree.add(2);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotate() {
        // Right left rotate
        /*
            0
             \
              2
             /
            1

            ->

              1
             / \
            0   2
         */

        tree.add(0);
        tree.add(2);
        tree.add(1);

        assertEquals(3, tree.size());

        assertEquals(3, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRightRotate() {
        // Left right rotate
        /*
            2
           /
          0
           \
            1

            ->

              1
             / \
            0   2
         */

        tree.add(2);
        tree.add(0);
        tree.add(1);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicate() {
        Integer temp = 1;

        /*
                  3
                /   \
               1     4
              / \
             0   2
            ->
                  3
                /   \
               1     4
              / \
             0   2
         */


        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        tree.add(1);
        assertEquals(5, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());

        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 0, leftLeft.getData());

        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 2, leftRight.getData());
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddWithNullData() {
        tree.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveWithNoChildrenNoRotate() {
        Integer temp = 0;

        /*
                  3
                /   \
               1     4
              / \
             0   2
            ->
                3
              /   \
             1     4
              \
               2
         */


        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(temp);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(0));
        assertEquals(4, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(-1, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 2, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveWithOneChildNoRotate() {
        Integer temp = 1;

        /*
                  3
                /   \
               1     4
                \
                 2
            ->
                3
              /   \
             2     4
         */


        tree.add(3);
        tree.add(temp);
        tree.add(4);
        tree.add(2);
        assertEquals(4, tree.size());

        assertSame(temp, tree.remove(1));
        assertEquals(3, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 2, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveWithTwoChildrenNoRotate() {
        Integer temp = 1;

        /*
                  3
                /   \
               1     4
              / \
             0   2
            ->
                3
              /   \
             0     4
              \
               2
         */


        tree.add(3);
        tree.add(temp);
        tree.add(4);
        tree.add(0);
        tree.add(2);

        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(1));
        assertEquals(4, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(-1, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());

        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 2, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveWithNoChildrenRotate() {
        Integer temp = 4;

        /*
                  3
                /   \
              1      4
             / \
            0   2
            ->
                 1
               /   \
              0     3
                   /
                  2
         */


        tree.add(3);
        tree.add(1);
        tree.add(temp);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(4));
        assertEquals(4, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 3, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(1, right.getBalanceFactor());

        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 2, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveWithTwoChildrenRotate() {
        Integer temp = 5;

        /*
                   3
                /     \
              1        5
             / \     /   \
            0   2   4     8
                         / \
                        6   9
            ->
                   3
                /     \
              1        5
             / \     /   \
            0   2   4     8
                         / \
                        6   9
         */


        tree.add(3);
        tree.add(1);
        tree.add(temp);
        tree.add(0);
        tree.add(2);
        tree.add(4);
        tree.add(8);
        tree.add(6);
        tree.add(9);
        assertEquals(9, tree.size());

        assertSame(temp, tree.remove(5));
        assertEquals(8, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 8, right.getData());
        assertEquals(2, right.getHeight());
        assertEquals(1, right.getBalanceFactor());

        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 0, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());

        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 2, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());

        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 4, rightLeft.getData());
        assertEquals(1, rightLeft.getHeight());
        assertEquals(-1, rightLeft.getBalanceFactor());

        AVLNode<Integer> rightRight = right.getRight();
        assertEquals((Integer) 9, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());

        AVLNode<Integer> rightLeftRight = rightLeft.getRight();
        assertEquals((Integer) 6, rightLeftRight.getData());
        assertEquals(0, rightLeftRight.getHeight());
        assertEquals(0, rightLeftRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootWithNoChildrenNoRotate() {
        /*
            4
            ->
            null
         */
        Integer temp = 4;
        tree.add(temp);

        assertEquals(1, tree.size());

        assertSame(temp, tree.remove(4));
        assertEquals(0, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals(null, root);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootWithOneChildNoRotate() {
        /*
              4
             /
            3
            ->
            3
         */
        Integer temp = 4;
        tree.add(temp);
        tree.add(3);

        assertEquals(2, tree.size());

        assertSame(temp, tree.remove(4));
        assertEquals(1, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootWithTwoChildrenNoRotate() {
        Integer temp = 3;

        /*
                  3
                /   \
              1      4
             / \
            0   2
            ->
                 2
               /   \
              1     4
             /
            0
         */


        tree.add(temp);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(3));
        assertEquals(4, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());

        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());

        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());

        AVLNode<Integer> leftRight = left.getLeft();
        assertEquals((Integer) 0, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testRemoveWithNullData() {
        tree.remove(null);
    }

    @Test(expected = NoSuchElementException.class, timeout = TIMEOUT)
    public void testRemoveWithNonexistentData() {
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        tree.remove(6);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer temp1 = 1;
        Integer temp0 = 0;
        Integer temp2 = 2;
        Integer temp3 = 3;

        /*
               1
             /   \
            0     2
                    \
                     3
         */

        tree.add(temp1);
        tree.add(temp0);
        tree.add(temp2);
        tree.add(temp3);
        assertEquals(4, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(temp0, tree.get(0));
        assertSame(temp1, tree.get(1));
        assertSame(temp2, tree.get(2));
        assertSame(temp3, tree.get(3));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testGetNullData() {
        tree.get(null);
    }

    @Test(expected = NoSuchElementException.class, timeout = TIMEOUT)
    public void testGetNonexistentData() {
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        tree.get(6);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
               1
             /   \
            0     2
                    \
                     3
         */

        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        assertEquals(4, tree.size());

        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testContainsNullData() {
        tree.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                     3
                   /   \
                 1      4
                / \
               0   2
         */

        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);

        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightEmptyTree() {
        assertEquals(-1, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
              1
             / \
            0   2
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(0);
        toAdd.add(2);
        tree = new AVL<>(toAdd);

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testDeepestBranchesWithNullTree() {
        List<Integer> actual = tree.deepestBranches();
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testDeepestBranchesWithSinglePath() {
        /*
                     3
                   /   \
                 1      4
                /
               0
         */

        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);

        List<Integer> actual = tree.deepestBranches();
        List<Integer> expected = Arrays.asList(3, 1, 0);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testDeepestBranchesWithMultiplePaths() {
        /*
                        10
                    /        \
                   5          15
                 /   \      /    \
                2     7    13    20
               / \   / \     \  / \
              1   4 6   8   14 17  25
             /           \          \
            0             9         30
        */
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(2);
        tree.add(7);
        tree.add(13);
        tree.add(20);
        tree.add(1);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(14);
        tree.add(17);
        tree.add(25);
        tree.add(0);
        tree.add(9);
        tree.add(30);

        List<Integer> actual = tree.deepestBranches();
        List<Integer> expected = Arrays.asList(10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }
}