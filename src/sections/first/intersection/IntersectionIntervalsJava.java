package sections.first.intersection;

/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.

1)
user1 [(8, 12), (17, 22)]
user2 [(5, 11), (14, 18), (20, 23), (42, 55)]
   => [(8, 11), (17, 18), (20, 22)]

user1 [(9, 15), (18, 21)]
user2 [(10, 14), (21, 22)]
   => [(10, 14)]

2)
user1 [(0, 5), (8, 12), (15, 20)]
user2 [(4, 11), (13, 17), (18, 20), (21, 23)]
   => [(4, 5), (8, 11), (15, 17), (18, 20)]
*/

import java.util.ArrayList;
import java.util.List;

public class IntersectionIntervalsJava {

    public static void main(String[] args) {
        List<Interval> user1 = List.of(new Interval(8, 12), new Interval(17, 22));
        List<Interval> user2 = List.of(new Interval(5, 11), new Interval(14, 18), new Interval(20, 23), new Interval(42, 55));
        System.out.println(findIntersection(user1, user2));
    }

    record Interval(int from, int to) {
        @Override
        public String toString() {
            return "(%d, %d)".formatted(from, to);
        }
    }

    private static List<Interval> findIntersection(List<Interval> user1, List<Interval> user2) {
        int i = 0;
        int j = 0;
        List<Interval> result = new ArrayList<>();
        while(i < user1.size() && j < user2.size()) {
            int start = Math.max(user1.get(i).from, user2.get(j).from);
            int end = Math.min(user1.get(i).to, user2.get(j).to);
            if (start < end) {
                result.add(new Interval(start, end));
            }
            if (user1.get(i).to < user2.get(j).to) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
