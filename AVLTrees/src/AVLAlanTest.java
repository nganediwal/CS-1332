import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AVLAlanTest {
    private static final int TIMEOUT = 200;
    private AVL<Integer> tree;

    @Before
    public void setup() {
        tree = new AVL<>();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor1A() {
        ArrayList<Integer> arr = null;
        tree = new AVL<>(arr);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor1B() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(null);
        tree = new AVL<>(arr);
    }

    @Test (timeout = TIMEOUT)
    public void testConstructor1C() {
        ArrayList<Integer> arr = new ArrayList<>();
        tree = new AVL<>(arr);
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test (timeout = TIMEOUT)
    public void testConstructor2A() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);

        tree = new AVL<>(arr);
        assertEquals(1, tree.size());
        assertNotNull(tree.getRoot());
        AVLNode<Integer> r = tree.getRoot();
        assertEquals(Integer.valueOf(1), r.getData());
        assertEquals(0, r.getHeight());
        assertEquals(0, r.getBalanceFactor());
        assertNull(r.getLeft());
        assertNull(r.getRight());
    }

    @Test (timeout = TIMEOUT)
    public void testConstructor2B() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(0);

        tree = new AVL<>(arr);
        assertEquals(2, tree.size());
        assertNotNull(tree.getRoot());
        AVLNode<Integer> r = tree.getRoot();
        assertEquals(Integer.valueOf(1), r.getData());
        assertEquals(1, r.getHeight());
        assertEquals(1, r.getBalanceFactor());
        assertNull(r.getRight());

        AVLNode<Integer> l = r.getLeft();
        assertEquals(Integer.valueOf(0), l.getData());
        assertEquals(0, l.getHeight());
        assertEquals(0, l.getBalanceFactor());
        assertNull(l.getLeft());
        assertNull(l.getRight());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAdd1A() {
        tree.add(null);
    }

    @Test
    public void testAdd2A() {
        tree.add(1);
        assertNotNull(tree.getRoot());
        assertEquals(1, tree.size());
        assertEquals(Integer.valueOf(1), tree.getRoot().getData());
    }

    @Test
    public void testAdd2B() {
        tree.add(0);
        tree.add(1);

        assertEquals(Integer.valueOf(0), tree.getRoot().getData());
        assertEquals(Integer.valueOf(1), tree.getRoot().getRight().getData());
        tree.add(2);

        assertEquals(Integer.valueOf(1), tree.getRoot().getData());
        assertEquals(Integer.valueOf(0), tree.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(2), tree.getRoot().getRight().getData());
    }

    @Test
    public void testAdd2C() {
        tree.add(0);
        tree.add(-1);
        tree.add(-2);

        assertEquals(Integer.valueOf(-1), tree.getRoot().getData());
        assertEquals(Integer.valueOf(-2), tree.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(0), tree.getRoot().getRight().getData());
    }

    @Test
    public void testAdd2D() {
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(10);
        tree.add(3);

        assertEquals(Integer.valueOf(1), tree.getRoot().getData());
        assertEquals(Integer.valueOf(0), tree.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(3), tree.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(2), tree.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(10), tree.getRoot().getRight().getRight().getData());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemove1A() {
        tree.add(1);
        tree.remove(null);
    }

    @Test (expected = NoSuchElementException.class)
    public void testRemove1B() {
        tree.remove(1);
    }

    @Test
    public void testRemove2A() {
        tree.add(1);
        assertEquals(Integer.valueOf(1), tree.remove(1));
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test
    public void testRemove2B() {
        tree.add(3);
        tree.add(0);
        tree.add(10);
        tree.add(2);
        tree.add(9);
        tree.add(11);
        tree.add(8);

        assertEquals(Integer.valueOf(10), tree.remove(10));
        assertEquals(2, tree.height());
        assertEquals(0, tree.getRoot().getBalanceFactor());
        assertEquals((Integer) 3, tree.getRoot().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight().getData());
        assertEquals(1, tree.getRoot().getRight().getHeight());
        assertEquals(0, tree.getRoot().getRight().getBalanceFactor());
        assertEquals((Integer) 8, tree.getRoot().getRight().getLeft().getData());
        assertEquals(0, tree.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, tree.getRoot().getRight().getLeft().getBalanceFactor());
        assertEquals((Integer) 11, tree.getRoot().getRight().getRight().getData());
        assertEquals(0, tree.getRoot().getRight().getRight().getHeight());
        assertEquals(0, tree.getRoot().getRight().getRight().getBalanceFactor());

        assertEquals((Integer) 3, tree.remove(3));
        assertEquals((Integer) 2, tree.getRoot().getData());
        assertEquals(2, tree.getRoot().getHeight());
        assertEquals(-1, tree.getRoot().getBalanceFactor());

        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals(0, tree.getRoot().getLeft().getHeight());
        assertEquals(0, tree.getRoot().getLeft().getBalanceFactor());

        assertEquals((Integer) 9, tree.getRoot().getRight().getData());
        assertEquals(1, tree.getRoot().getRight().getHeight());
        assertEquals(0, tree.getRoot().getRight().getBalanceFactor());
    }

    @Test
    public void testRemove3A() {
        ArrayList<Integer> arr = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            arr.add(i);
        }
        //System.out.println(arr);
        for (int j = 0; j < 50; j++) {
            Collections.shuffle(arr);
            tree = new AVL<>(arr);

            for (int i = 0; i < 100; i++) {
                assertEquals(arr.get(i), tree.remove(arr.get(i)));
                assertEquals(99 - i, tree.size());
            }
        }
    }

    @Test (expected = NoSuchElementException.class)
    public void testGet1A() {
        tree.get(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGet1B() {
        tree.add(0);
        tree.get(null);
    }

    @Test (expected = NoSuchElementException.class)
    public void testGet2A() {
        tree.add(0);
        tree.get(1);
    }

    @Test
    public void testGet2B() {
        tree.add(0);
        //test that values are equal
        assertEquals(Integer.valueOf(0), tree.get(0));

        //test that references are NOT equal
        Integer i = new Integer(0);
        assertFalse(i == tree.get(0));
    }

    @Test
    public void testGet3A() {
        for (int i = 0; i < 100; i++) {
            tree.add(i);
            for (int j = 0; j <= i; j++) {
                Integer temp = tree.get(j);
                assertEquals(Integer.valueOf(j), temp);
            }

            boolean exc = false;
            try {
                tree.get(i + 1);
            } catch (NoSuchElementException e) {
                exc = true;
            }
            assertTrue(exc);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testContains1A() {
        tree.contains(null);
    }

    @Test
    public void testContains2A() {
        tree.add(0);
        assertTrue(tree.contains(0));
        assertFalse(tree.contains(1));
    }

    @Test
    public void testContains2B() {
        for (int i = 0; i < 100; i++) {
            tree.add(i);
            for (int j = 0; j <= i; j++) {
                assertTrue(tree.contains(j));
            }
            for (int j = i + 1; j < 101; j++) {
                assertFalse(tree.contains(j));
            }
        }
    }

    @Test
    public void testHeight1A() {
        assertEquals(-1, tree.height());
    }

    @Test
    public void testHeight1B() {
        tree.add(0);
        assertEquals(0, tree.height());
    }

    @Test
    public void testHeight1C() {
        tree.add(0);
        tree.add(1);
        assertEquals(1, tree.height());
        tree.add(2);
        assertEquals(1, tree.height());
        tree.add(3);
        assertEquals(2, tree.height());

        tree.clear();
        assertEquals(-1, tree.height());
    }

    @Test
    public void testDeepest1A() {
        assertEquals(0, tree.deepestBranches().size());
    }

    @Test
    public void testDeepest1B() {
        tree.add(1);
        assertEquals(1, tree.deepestBranches().size());
    }

    @Test
    public void testDeepest1C() {
        tree.add(1);
        tree.add(0);
        tree.add(2);
        assertEquals(3, tree.deepestBranches().size());
    }

    @Test
    public void testClear1A() {
        tree.clear();
        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
    }

    @Test
    public void testClear1B() {
        tree.add(1);
        tree.clear();
        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
    }

    @Test
    public void testClear1C() {
        tree.add(1);
        tree.clear();
        tree.clear();
        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
    }

    @Test
    public void testClear1D() {
        tree.add(1);
        tree.add(0);
        tree.add(1);
        tree.clear();
        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
    }
}
