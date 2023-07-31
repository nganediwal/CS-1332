import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.List;

/**
 * Your implementation of an AVL.
 *
 * @author Neel Ganediwal
 * @version 1.0
 * @userid nganediwal3 (i.e. gburdell3)
 * @GTID 903582662 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("this data is invalid, please change an inpur"
            );
        } else {
            for (T item: data) {
                if (item == null) {
                    throw new IllegalArgumentException("this element in the collection is null");
                }
                add(item);
            }
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("no data passed in, invalid input");
        }
        root = addHelp(data, root);
    }

    /**
     * a recursive helper method for add method to take in node
     *
     * @throws IllegalArgumentException if the data is null
     * @param node the current node to be traced
     * @param data the data to be added
     * @return a node which is the parent node of current node
     */
    private AVLNode<T> addHelp(T data, AVLNode<T> node) {
        if (node != null) {
            if (data == null) {
                throw new IllegalArgumentException("this data is invalid, please pass in a different value");
            } else {
                if (data.compareTo(node.getData()) > 0) {
                    node.setRight(addHelp(data, node.getRight()));
                    if (balanceFactor(node) == -2) {
                        if (data.compareTo(node.getRight().getData()) > 0) {
                            node = rotateLeft(node);
                        } else {
                            node = rotateRightLeft(node);
                        }
                    }
                } else if (data.compareTo(node.getData()) < 0) {
                    node.setLeft(addHelp(data, node.getLeft()));
                    if (balanceFactor(node) == 2) {
                        if (data.compareTo(node.getLeft().getData()) < 0) {
                            node = rotateRight(node);
                        } else {
                            node = rotateLeftRight(node);
                        }
                    }
                }
            }
            node.setHeight(height(node));
            node.setBalanceFactor(balanceFactor(node));
        } else {
            node = new AVLNode<>(data);
            size = size + 1;
        }
        return node;
    }

    /**
     * a method to calculate the balance factor
     *
     * @param node the current node to be calclate BF
     * @return the balance factor of a node
     */

    private int balanceFactor(AVLNode<T> node) {
        int left;
        int right;
        if (node.getLeft() == null) {
            left = -1;
        } else {
            left = node.getLeft().getHeight();
        }
        if (node.getRight() == null) {
            right = -1;
        } else {
            right = node.getRight().getHeight();
        }
        return left - right;
    }

    /**
     * a method to calculate height
     *
     * @param node the current node to be calclate Height
     * @return the height of a node
     */

    private int height(AVLNode<T> node) {
        int left;
        int right;
        if (node == null) {
            return -1;
        }
        if (node.getLeft() == null) {
            left = -1;
        } else {
            left = node.getLeft().getHeight();
        }
        if (node.getRight() == null) {
            right = -1;
        } else {
            right = node.getRight().getHeight();
        }
        return Math.max(left, right) + 1;
    }

    /**
     * a method to rotate a node and its child to the left
     *
     * @param rot the node that needs to rotate
     * @return the node that is originally the child of a
     */

    private AVLNode<T> rotateLeft(AVLNode<T> rot) {
        AVLNode<T> b = rot.getRight();
        rot.setRight(b.getLeft());
        b.setLeft(rot);
        rot.setHeight(height(rot));
        b.setHeight(height(b));
        rot.setBalanceFactor(balanceFactor(rot));
        b.setBalanceFactor(balanceFactor(b));
        return b;
    }

    /**
     * a method to rotate a node and its child to the right
     *
     * @param rot the node that needs to rotate
     * @return the node that is originally the child of a
     */

    private AVLNode<T> rotateRight(AVLNode<T> rot) {
        AVLNode<T> b = rot.getLeft();
        rot.setLeft(b.getRight());
        b.setRight(rot);
        rot.setHeight(height(rot));
        b.setHeight(height(b));
        rot.setBalanceFactor(balanceFactor(rot));
        b.setBalanceFactor(balanceFactor(b));
        return b;
    }

    /**
     * a method to rotate a node and its child to the right than to the left
     *
     * @param rot the node that needs to rotate
     * @return the node that is originally the child of a
     */
    private AVLNode<T> rotateRightLeft(AVLNode<T> rot) {
        rot.setRight(rotateRight(rot.getRight()));
        return rotateLeft(rot);
    }

    /**
     * a method to rotate a node and its child to the left then to the right
     *
     * @param rot the node that needs to rotate
     * @return the node that is originally the child of a
     */
    private AVLNode<T> rotateLeftRight(AVLNode<T> rot) {
        rot.setLeft(rotateLeft(rot.getLeft()));
        return rotateRight(rot);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     *    replace the data, NOT successor. As a reminder, rotations can occur
     *    after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        AVLNode<T> dummyNode = new AVLNode<>(null);
        if (data == null) {
            throw new IllegalArgumentException(
                    "this data is invalid, please choose another one"
            );
        } else {
            root = removeHelp(data, root, dummyNode, null);
        }
        if (dummyNode.getData() == null) {
            throw new NoSuchElementException("there is no such element found in BST");
        } else {
            size--;
        }
        return dummyNode.getData();
    }

    /**
     * the recursive helper method of remove.
     *
     * @throws NoSuchElementException when there is no such data in the tree
     * @param data the data to be searched
     * @param node the current node to look into
     * @param dummyNode the node to be returned because it will be removed
     * @param predecessor the successor node set up in order to keep the
     *                  dummyNode unchanged when deleting the successor.
     * @return a bst node which will be look into next
     */
    private AVLNode<T> removeHelp(T data, AVLNode<T> node, AVLNode<T> dummyNode, AVLNode<T> predecessor) {
        if (node == null) {
            throw new NoSuchElementException("we can not find this data in BST!!"
            );
        } else {
            if (data.compareTo(node.getData()) > 0) {
                node.setRight(removeHelp(data, node.getRight(), dummyNode, predecessor));
            } else if (data.compareTo(node.getData()) < 0) {
                node.setLeft(removeHelp(data, node.getLeft(), dummyNode, predecessor));
            } else if (node.getData().equals(data)) {
                if (predecessor == null) {
                    dummyNode.setData(node.getData());
                }
                if (node.getLeft() == null) {
                    return node.getRight();
                } else if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    node.setData(predecessorHelper(node.getLeft()));
                    predecessor = new AVLNode<>(null);
                    predecessor.setData(node.getData());
                    node.setLeft(removeHelp(node.getData(), node.getLeft(), dummyNode, predecessor));
                }
            }
        }
        node.setHeight(height(node));
        node.setBalanceFactor(balanceFactor(node));

        if (balanceFactor(node) == -2) {
            if (balanceFactor(node.getRight()) <= 0) {
                node = rotateLeft(node);
            } else {
                node = rotateRightLeft(node);
            }

        } else if (balanceFactor(node) == 2) {
            if (balanceFactor(node.getLeft()) >= 0) {
                node = rotateRight(node);
            } else {
                node = rotateLeftRight(node);
            }
        }
        return node;
    }

    /**
     * A helper method to help find the predecessor
     * @param node the node that needs to be check from
     * @return a node that will need to look into next
     */
    private T predecessorHelper(AVLNode<T> node) {
        if (node.getRight() != null) {
            return predecessorHelper(node.getRight());
        } else {
            return node.getData();
        }
    }
    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("no data passed in, please pass in a value"
            );
        } else {
            T out = getHelper(data, root);
            if (out == null) {
                throw new NoSuchElementException("there is no such element in tree"
                );
            }
            return out;
        }
    }
    /**
     * A recursive helper method for get
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to get
     * @param node the node that will start to check from
     * @return the data that will be get
     */
    private T getHelper(T data, AVLNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException("this data is null, please change one"
            );
        }
        if (node == null) {
            return null;
        } else {
            if (node.getData().compareTo(data) == 0) {
                return node.getData();
            } else if (node.getData().compareTo(data) > 0) {
                return getHelper(data, node.getLeft());
            } else {
                return getHelper(data, node.getRight());
            }
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("this data is invalid, please choose another one"
            );
        } else {
            return containsHelper(data, root);
        }
    }

    /**
     * a helper method for the contains method
     *
     * @param data the data that we will check if contains
     * @param node the node we are currently looking into
     * @return a boolean which will indicate whether bst contains the data
     */
    private boolean containsHelper(T data, AVLNode<T> node) {
        if (node == null) {
            return false;
        } else {
            T currData = node.getData();
            if (currData.equals(data)) {
                return true;
            } else if (currData.compareTo(data) < 0) {
                return containsHelper(data, node.getRight());
            } else {
                return containsHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * Your list should not contain duplicate data, and the data of a branch
     * should be listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> list = new ArrayList<>();
        if (root == null) {
            return list;
        } else {
            recursiveDeepestBranches(list, root);
            return list;
        }

    }

    /**
     * the recursive helper method of returning a list with the deepest branches.
     *
     * @param list the list to be added to
     * @param root the node passed in to traverse from
     *
     */
    private void recursiveDeepestBranches(List<T> list, AVLNode<T> root) {
        if (root.getLeft() == null && root.getRight() == null) {
            list.add(root.getData());
        } else {
            if (root.getBalanceFactor() > 0) {
                list.add(root.getData());
                recursiveDeepestBranches(list, root.getLeft());
            } else if (root.getBalanceFactor() < 0) {
                list.add(root.getData());
                recursiveDeepestBranches(list, root.getRight());
            } else {
                list.add(root.getData());
                recursiveDeepestBranches(list, root.getLeft());
                recursiveDeepestBranches(list, root.getRight());
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}