package edu.grinnell.csc207.lootgenerator;

public class BST<T extends Comparable<? super T>, U> {

    public static class Pair<T, U> {

        public T key;
        public U val;

        public Pair(T key, U val) {
            this.key = key;
            this.val = val;
        }
    }

    private static class Node<T extends Comparable<? super T>, U> {

        public Pair<T, U> pair;
        public Node<T, U> left;
        public Node<T, U> right;

        private Node(T key, U val, Node<T, U> left, Node<T, U> right) {
            this.pair = new Pair(key, val);
            this.left = left;
            this.right = right;
        }

        public Node(T key, U val) {
            this(key, val, null, null);
        }
    }

    private Node<T, U> root;

    public BST() {
        root = null;
    }

    private int sizeH(Node<T, U> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + sizeH(root.left) + sizeH(root.right);
        }
    }

    public int size() {
        return sizeH(root);
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

    public void insert(T key, U val) {
        root = insertH(key, val, root);
    }

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
