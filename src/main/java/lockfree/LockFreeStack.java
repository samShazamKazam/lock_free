package lockfree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {
    public int size() {
        return size.get();
    }

    private AtomicInteger size = new AtomicInteger(0);

    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> oldHead;
        do {
            oldHead = head.get();
            newNode.next = oldHead;
        } while (!head.compareAndSet(oldHead, newNode));
        size.incrementAndGet();
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) {
                return null; // Stack is empty
            }
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        size.decrementAndGet();
        return oldHead.value;
    }

    public boolean isEmpty() {
        return head.get() == null;
    }
}

