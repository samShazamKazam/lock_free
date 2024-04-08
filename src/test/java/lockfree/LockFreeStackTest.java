package lockfree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockFreeStackTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void push() {
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0 ; i < 20 ; i++)  stack.push(i);
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 50 ; i < 70 ; i++)  stack.push(i);
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            for (int i = 70 ; i < 90 ; i++)  stack.push(i);
        });
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(60, stack.size());
        while (!stack.isEmpty()) {
            System.out.format("%d - ", stack.pop());
        }
    }

}