package practicum.interview.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InsertDeleteGetRandomO1 {

    public static void main(String[] args) {

    }

    public static class RandomizedSet {

        private final HashMap<Integer, Integer> map;

        private final ArrayList<Integer> list;

        private final Random random;

        public RandomizedSet() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
            this.random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int removeIdx = map.get(val);
            int lastIdx = list.size() - 1;
            list.set(removeIdx, list.get(lastIdx));
            map.put(list.get(lastIdx), removeIdx);
            list.remove(lastIdx);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
