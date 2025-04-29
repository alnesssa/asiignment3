import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<Map.Entry<K, V>> {
    private class Node {
        K key;
        V val;
        Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;
    private int size;

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        return x;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<>() {
            Stack<Node> stack = new Stack<>();
            Node curr = root;

            {
                pushLeft(curr);
            }

            private void pushLeft(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public Map.Entry<K, V> next() {
                Node node = stack.pop();
                pushLeft(node.right);
                return new AbstractMap.SimpleEntry<>(node.key, node.val);
            }
        };
    }
}
