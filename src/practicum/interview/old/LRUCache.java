package practicum.interview.old;

/*

1)
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

LRUCache cache = new LRUCache(2);
cache.put(1, 1);
cache.put(2, 2);
System.out.println(cache.get(1));
cache.put(3, 3);
System.out.println(cache.get(2));
cache.put(4, 4);
System.out.println(cache.get(1));
System.out.println(cache.get(3));
System.out.println(cache.get(4));

2)
["LRUCache","put","get"]
[[1],[2,1],[2]]

LRUCache cache = new LRUCache(1);
cache.put(2, 1);
System.out.println(cache.get(2));

3)
["LRUCache","put","put","get","put","put","get"]
[[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]

 */

import java.util.HashMap;

public class LRUCache {

    private final int capacity;

    private final HashMap<Integer, Node> map;

    private Node head;

    private Node tail;

    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.size = 0;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node currentNode = map.get(key);
        if (size > 1) {
            removeNode(currentNode);
            addHeadNode(currentNode);
        }
        return head.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node currentNode = map.get(key);
            removeNode(currentNode);
            currentNode.value = value;
            addHeadNode(currentNode);
            return;
        }
        if (size == capacity) {
            map.remove(tail.key);
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            --size;
        }
        Node newNode = new Node(key, value, null, head);
        map.put(key, newNode);
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    private void addHeadNode(Node node) {
        node.prev = null;
        if (head != null) {
            head.prev = node;
            node.next = head;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private class Node {

        private final int key;

        private int value;

        private Node prev;

        private Node next;

        public Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
    }
}
