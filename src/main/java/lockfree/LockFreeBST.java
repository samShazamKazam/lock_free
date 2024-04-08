package lockfree;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class LockFreeBST<T extends Comparable<T>> {
    public class BSTNode<T> {
        final T value;
        AtomicReference<BSTNode<T>> left;
        AtomicReference<BSTNode<T>> right;

        public BSTNode(T value) {
            this.value = value;
            this.left = new AtomicReference<>(null);
            this.right = new AtomicReference<>(null);
        }
    }

    AtomicReference<BSTNode<T>> root = new AtomicReference<>(null);

    public void add(T value) {
        BSTNode<T> newNode = new BSTNode<>(value);

        if (!root.compareAndSet(null, newNode)) {
            helper(root.get(), newNode);
        }
    }

    private void helper(BSTNode<T> rootNode, BSTNode<T> newNode) {
        if (newNode.value.compareTo(rootNode.value) < 0) {
            if (!rootNode.left.compareAndSet(null, newNode)) {
                helper(rootNode.left.get(), newNode);
            }
        } else if (newNode.value.compareTo(rootNode.value) > 0) {
            if (!rootNode.right.compareAndSet(null, newNode)) {
                helper(rootNode.right.get(), newNode);
            }
        } else { // if node value already exists, don't insert it

        }
    }

    public void walk(Consumer<? super T> action) {
        walk(action, root.get());
    }

    private void walk(Consumer<? super T> action, BSTNode<T> node) {
        if (node == null || node.value == null) return;

        walk(action, node.left.get());
        action.accept(node.value);
        walk(action, node.right.get());
    }
}
