package edu.grinnell.csc207.lootgenerator;
    
/**
 * A generic Binary Search Tree (BST) implementation that stores key-value pairs.
 * @author yutong
 * @param <T> the comparable type of keys maintained by this BST
 * @param <U> the type of values
 */
public class BST<T extends Comparable<? super T>, U> {
    
    /**
     * A key-value pair structure used by the BST.
     * @param <T> the type of key
     * @param <U> the type of value 
     */
    public static class Pair<T, U> {

        public T key;
        public U val;

        /**
         * construct a new key-value pair
         * @param key the key of the pair
         * @param val the value of the pair
         */
        public Pair(T key, U val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * a node in BST
     * @param <T> the type of key
     * @param <U> the type of value
     */
    private static class Node<T extends Comparable<? super T>, U> {

        public Pair<T, U> pair;
        public Node<T, U> left;
        public Node<T, U> right;
        
        /**
         * Constructs a node with the given key, value, left, and right subtrees.
         * @param key the key
         * @param val the value
         * @param left the left subtree
         * @param right the right subtree
         */
        private Node(T key, U val, Node<T, U> left, Node<T, U> right) {
            this.pair = new Pair(key, val);
            this.left = left;
            this.right = right;
        }

        /**
         * Constructs a leaf node with the given key and value.
         * @param key the key
         * @param val the value
         */
        public Node(T key, U val) {
            this(key, val, null, null);
        }
    }

    private Node<T, U> root;

    /**
     * construct a new empty BST
     */
    public BST() {
        root = null;
    }

    private boolean containsH(T key, Node<T, U> root) {
        if (root == null) {
            return false;
        } else if (key.compareTo(root.pair.key) < 0) {
            return containsH(key, root.left);
        } else if (key.compareTo(root.pair.key) > 0) {
            return containsH(key, root.right);
        } else {
            return true;
        }
    }

    /**
     * Checks whether the BST contains a given key.
     * @param key the key that we are interested in 
     * @return true if BST contains that key 
     */
    public boolean contains(T key) {
        return containsH(key, root);
    }

    private Node<T, U> insertH(T key, U val, Node<T, U> root) {
        if (root == null) {
            return new Node<>(key, val);
        } else if (key.compareTo(root.pair.key) < 0) {
            root.left = insertH(key, val, root.left);
        } else if (key.compareTo(root.pair.key) > 0) {
            root.right = insertH(key, val, root.right);
        }
        return root;
    }
    
    /*s
     * insert a new node to BST
     * @param key the key of the new node
     * @param val the value of the new node
     */
    public void insert(T key, U val) {
        root = insertH(key, val, root);
    }

    /**
     * get the value of the given key in BST
     * @param key the key that we are interested in 
     * @return the value of the given key
     */
    public U get(T key) {
        Node<T, U> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.pair.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.pair.val;
            }
        }
        return null;
    }

}
