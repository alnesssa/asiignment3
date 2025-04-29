public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }
    HashNode<K, V>[] chainArray;
    int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
    }
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }
    public int getBucketSize(int index) {
        int count = 0;
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
    public int size() {
        return size;
    }
}