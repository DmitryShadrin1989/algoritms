package alex.a2.myDeque;

public class ArrayDeque<T> {
    T[] array;
    int indexTail = 0;
    int indexHead = 0;
    int countElements = 0;

    private static final int DEFAULT_MAX_OCCUPANCY = 75;
    private static final int DEFAULT_MIN_OCCUPANCY = 25;

    public ArrayDeque() {
        this.array = (T[])(new Object[8]);
    }

    public void checkOccupancy() {
        int occ = (countElements+1)*100 / array.length;
        if ((occ < DEFAULT_MIN_OCCUPANCY && array.length != 8)
                || occ > DEFAULT_MAX_OCCUPANCY) {
            int newSize = occ < DEFAULT_MIN_OCCUPANCY ? array.length/2 : array.length*2;
            T[] newArray = (T[])(new Object[newSize]);
            for (int i=0; i < array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    public void addFirst(T item) {
        checkOccupancy();
        array[indexTail] = item;
        indexTail = (indexTail-1+array.length)%array.length;
        countElements++;
    }

    public void addLast(T item) {
        indexHead = (indexHead+1+array.length)%array.length;
        array[indexHead] = item;
        countElements++;
    }

    public boolean isEmpty() {
        return countElements == 0;
    }

    public int size() {
        return countElements;
    }

    public T removeFirst() {
        T result = null;
        if (!isEmpty()) {
            result = array[indexHead];
            array[indexHead] = null;
            indexHead = (indexHead-1+ array.length)% array.length;
            countElements--;
        }
        return result;
    }

    public T removeLast() {
        T result = null;
        if (!isEmpty()) {
            indexTail = (indexTail+1+array.length)%array.length;
            result = array[indexTail];
            array[indexTail] = null;
            countElements--;
        }
        return result;
    }
}
