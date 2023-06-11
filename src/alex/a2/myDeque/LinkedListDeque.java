package alex.a2.myDeque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    Node<T> head;
    Node<T> tail;
    private int size;

    public LinkedListDeque() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        if (!other.isEmpty()) {
            for (T currentValue : other) {
                this.addLast(currentValue);
            }
        }
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<T>();
        newNode.data = item;
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.last = newNode;
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>();
        newNode.data = item;
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.last = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("LinkedListDeque: ");
        if (!isEmpty()) {
            Node<T> currentNode = head;
            while (currentNode != null) {
                builder.append(" ").append(currentNode.data);
                currentNode = currentNode.next;
            }
        }
        return builder.toString();
    }

    public void printDeque() {
        if (!isEmpty()) {
            StringBuilder builder = new StringBuilder("LinkedListDeque: ");
            Node<T> currentNode = head;
            while (currentNode != null) {
                builder.append(", data-")
                        .append(currentNode.data)
                        .append(", ref-").append(currentNode);
                currentNode = currentNode.next;
            }
            System.out.println(builder);
        }
    }

    public T removeFirst() {
        T result = null;
        if (!isEmpty()) {
            Node<T> removeNode = head;
            result = removeNode.data;
            if (this.size>1) {
                head = removeNode.next;
                head.last = null;
                removeNode.next = null;
            } else {
                head = null;
                tail = null;
            }
            size--;
        }
        return result;
    }

    public T removeLast() {
        T result = null;
        if (!isEmpty()) {
            Node<T> removeNode = tail;
            result = removeNode.data;
            if (this.size>1) {
                tail = removeNode.last;
                tail.next = null;
                removeNode.last = null;
            } else {
                head = null;
                tail = null;
            }
            size--;
        }
        return result;
    }

    public T get(int index) {
        T result = null;
        if (!isEmpty() || index < this.size) {
            int count = 0;
            Node<T> currentNode = head;
            while (count != index) {
                currentNode = currentNode.next;
                count++;
            }
            result = currentNode.data;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> last;

        public Node() {
            this.data = null;
            this.next = null;
            this.last = null;
        }

        public Node(T otherData) {
            this.data = otherData;
            this.next = null;
            this.last = null;
        }
    }
}
