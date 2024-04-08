package lockfree;

public class Node<T> {
    final T value;
    volatile Node<T> next;

    Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}

