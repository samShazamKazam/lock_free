package lockfree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockFreeQueueTest {

    @Test
    void enqueue() {
        LockFreeQueue<Integer> queue = new LockFreeQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());

        assertEquals(0, queue.size());
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(2, queue.size());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    void enqueueMultithreaded() {
        LockFreeQueue<Integer> queue = new LockFreeQueue<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0 ; i < 20 ; i++)  queue.enqueue(i);
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 20 ; i < 40 ; i++)  queue.enqueue(i);;
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            for (int i = 40 ; i < 60 ; i++)  queue.enqueue(i);;
        });
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(60, queue.size());
        while (queue.size() > 0) {
            System.out.format("%d - ", queue.dequeue());
        }
    }
}