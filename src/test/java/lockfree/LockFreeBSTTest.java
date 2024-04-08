package lockfree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockFreeBSTTest {

    @Test
    void add() {
        LockFreeBST<Integer> tree = new LockFreeBST<>();
        for (int i : new int[]{5,3,4,2,6,8,7,9}) {
            tree.add(i);
        }
        tree.walk(v -> System.out.format("%d - ", v));
    }

    @Test
    void add_multithreaded() {
        LockFreeBST<Integer> tree = new LockFreeBST<>();
        Thread t1 = new Thread(() -> {
            for (int i = 40 ; i < 80 ; i=i+3)  tree.add(i);
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 40 ; i > 0 ; i=i-3)  tree.add(i);
        });
        t2.start();

        tree.walk(v -> System.out.format("%d - ", v));
    }
}