import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K,V>.Entry> {

    private class Node {
        K key;
        V val;
        Node left, right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    private Node root;
    private int size = 0;

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node curr = root;

        while (true) {
            int cmp = key.compareTo(curr.key);

            if (cmp < 0) {
                if (curr.left == null) {
                    curr.left = new Node(key, val);
                    size++;
                    return;
                }
                curr = curr.left;
            } else if (cmp > 0) {
                if (curr.right == null) {
                    curr.right = new Node(key, val);
                    size++;
                    return;
                }
                curr = curr.right;
            } else {
                curr.val = val;
                return;
            }
        }
    }

    public V get(K key) {
        Node curr = root;

        while (curr != null) {
            int cmp = key.compareTo(curr.key);

            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.val;
        }
        return null;
    }

    public Iterator<Entry> iterator() {
        return new Iterator<>() {
            Stack<Node> stack = new Stack<>();
            Node curr = root;

            public boolean hasNext() {
                return curr != null || !stack.isEmpty();
            }

            public Entry next() {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }

                Node node = stack.pop();
                curr = node.right;

                return new Entry(node.key, node.val);
            }
        };
    }
}