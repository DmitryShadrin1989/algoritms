package practicum.interview.old;

import java.util.ArrayList;
import java.util.List;

public class ZigzagIterator {

    public static void main(String[] args) {
        List<Integer> v1 = List.of(1,2);
        List<Integer> v2 = List.of(3,4,5,6);
        ZigzagIteratorIml iterator = new ZigzagIteratorIml(v1, v2);
        System.out.print("|");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "|");
        }
    }

    public static class ZigzagIteratorIml {

        private List<List<Integer>> vectors;

        private int currentVectorIdx;

        private int currentValueIdx;

        private int maxVectorSize;

        public ZigzagIteratorIml(List<Integer> v1, List<Integer> v2) {
            vectors = new ArrayList<>();
            vectors.add(v1);
            vectors.add(v2);
            currentVectorIdx = -1;
            for (int i = 0; i < vectors.size(); i++) {
                if (currentVectorIdx == -1 && vectors.get(i) != null && !vectors.get(i).isEmpty()) {
                    currentVectorIdx = i;
                }
                maxVectorSize = Math.max(maxVectorSize, vectors.get(i).size());
            }
            currentValueIdx = 0;
        }

        public boolean hasNext() {
            return currentValueIdx + 1 < maxVectorSize;
        }

        public int next() {


            return 0;
        }
    }
}
