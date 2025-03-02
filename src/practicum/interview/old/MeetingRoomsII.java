package practicum.interview.old;

/*
1)
Input: intervals = {{0,30},{5,10},{15,20}}
Output: 2

2)
Input: intervals = {{7,10},{2,4}}
Output: 1

Описание решения:
1)Сортируем все интервалы по времени начала.
2) Используем кучу (min-heap) для хранения времени окончания встреч. Когда встреча начинается, проверяем,
можем ли использовать комнату, то есть, если минимальное время окончания в куче меньше или равно времени начала новой встречи,
то комната освобождается. В любом случае, добавляем встречу в кучу.
3) Размер кучи в итоге и будет минимальным количеством комнат.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        if (intervals.length == 1) {
            return 1;
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (pq.peek() <= intervals[i][0]) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();
        System.out.println(meetingRoomsII.minMeetingRooms(intervals));
    }
}
