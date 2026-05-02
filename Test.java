import java.util.Random;

public class Test {
    public static void main(String[] args) {

        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(11);
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(rand.nextInt(100000)), i);
        }

        table.printBuckets();
    }
}