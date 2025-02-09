package practicum.interview.old;

import java.util.List;

/*
1)
input:
v1 = List.of(1,2);
v2 = List.of(3,4,5,6);
output:
v3 = {1,3,2,4,5,6}

 */

public class ZigzagIterator {

    public static void main(String[] args) {
        List<Integer> v1 = List.of(1, 2, 3);
        List<Integer> v2 = List.of(3, 4);
        ZigzagIteratorIml iterator = new ZigzagIteratorIml(v1, v2);
        System.out.print("|");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "|");
        }
    }


    public static class ZigzagIteratorIml {

        private final List<List<Integer>> vectors;

        private int vectorIdx;

        private int valueIdx;

        private int maxSize;

        public ZigzagIteratorIml(List<Integer> v1, List<Integer> v2) {
            this.vectors = List.of(v1, v2);
            for (List<Integer> vector : vectors) {
                this.maxSize = Math.max(maxSize, vector.size());
            }
            this.vectorIdx = -1;
            valueIdx = 0;
        }

        public boolean hasNext() {
            return valueIdx + 1 < maxSize;
        }

        public int next() {
            if (vectorIdx + 1 < vectors.size()) {
                ++vectorIdx;
            } else {
                vectorIdx = 0;
                ++valueIdx;
            }
            List<Integer> currVector = vectors.get(vectorIdx);
            while (valueIdx > currVector.size() - 1) {
                if (vectorIdx + 1 < vectors.size()) {
                    ++vectorIdx;
                } else {
                    vectorIdx = 0;
                    ++valueIdx;
                }
                currVector = vectors.get(vectorIdx);
            }
            return currVector.get(valueIdx);
        }
    }
}
