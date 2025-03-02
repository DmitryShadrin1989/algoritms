package practicum.interview.old;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounterUseQueue {

    private final Queue<Integer> pings;

    public RecentCounterUseQueue() {
        this.pings = new LinkedList<>();
    }

    public int ping(int t) {
        pings.offer(t);
        int deepVal = t - 3000;
        if (deepVal < 0) {
            return pings.size();
        }
        while (pings.peek() < deepVal) {
            pings.poll();
        }
        return pings.size();
    }

    public static void main(String[] args) {
        RecentCounterUseQueue recentCounter = new RecentCounterUseQueue();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }
}
