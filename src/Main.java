import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            String name = "N" + id;
            MyTestingClass key = new MyTestingClass(id, name);
            Student student = new Student("Student" + i);
            table.put(key, student);
        }

        for (int i = 0; i < table.M; i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i) + " elements");
        }

        BST<Integer, String> bst = new BST<>();
        bst.put(10, "Ten");
        bst.put(5, "Five");
        bst.put(20, "Twenty");

        for (var entry : bst) {
            System.out.println("key is " + entry.getKey() + " and value is " + entry.getValue());
        }
    }
}
