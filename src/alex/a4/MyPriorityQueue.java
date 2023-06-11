package alex.a4;

import java.util.*;

public class MyPriorityQueue<T> implements IPriorityQueue<T> {
    private final List<T> queueItems;
    private final CompareWith<T> compareWith;

    public MyPriorityQueue(CompareWith<T> cc) {
        queueItems = new ArrayList<>();
        compareWith = cc;
    }

    @Override
    public void add(T item) {
        queueItems.add(item);
        sortPriority();
    }

    @Override
    public void addAll(List<T> items) {
        Collections.addAll(queueItems);
        sortPriority();
    }

    @Override
    public T getMinimum() {
        if (size() > 0)
            return queueItems.get(size() - 1);
        else {
            return null;
        }
    }

    @Override
    public void removeMinimum() {
        if (size() > 0) {
            queueItems.remove(size() - 1);
        }
    }

    @Override
    public int size() {
        return queueItems.size();
    }

    @Override
    public Iterator<T> iterator() {
        ArrayList<T> copyQueueItems = new ArrayList<>(queueItems);
        Collections.reverse(copyQueueItems);
        return copyQueueItems.iterator();
    }

    @Override
    public Iterator<T> revIterator() {
        ArrayList<T> copyQueueItems = new ArrayList<>(queueItems);
        return copyQueueItems.iterator();
    }

    public void sortPriority() {
        if (queueItems.size() > 1) {
            queueItems.sort(new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return compareWith.lessThan(o1, o2) ? -1 : 1;
                }
            });
        }
    }
}
