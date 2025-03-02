package practicum.interview.old;

import java.util.ArrayList;
import java.util.List;

public class RecentCounter {

    private final List<Integer> pings;

    public RecentCounter() {
        this.pings = new ArrayList<>();
    }

    public int ping(int t) {
        pings.add(t);
        int deepVal = t - 3000;
        if (deepVal < 0) {
            return pings.size();
        }
        for (int i = 0; i < pings.size(); i++) {
            if (pings.get(i) >= deepVal) {
                return pings.size() - i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }
}
