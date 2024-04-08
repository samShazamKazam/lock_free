package lockfree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeQueue<T> {
    AtomicReference<Node<T>> head;
    AtomicReference<Node<T>> tail;
    AtomicInteger size = new AtomicInteger(0);

    public LockFreeQueue() {
        this.head = new AtomicReference<>(null);
        this.tail = new AtomicReference<>(null);
    }

    public void enqueue(T value) {
        Node<T> newTail = new Node<>(value);
        Node<T> oldTail;

        do {
            oldTail = tail.get();
        } while (!tail.compareAndSet(oldTail, newTail));
        if (oldTail != null)    oldTail.next = newTail;
        head.compareAndSet(null, newTail);

        size.incrementAndGet();
    }

    public T dequeue() {
        Node<T> newHead;
        Node<T> oldHead;

        do {
            oldHead = head.get();
            if (oldHead == null) return null;

            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        size.decrementAndGet();
        return oldHead.value;
    }

    public int size() {
        return size.get();
    }
}
