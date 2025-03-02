package practicum.interview.old;

/*
1)
input: seats = [1,0,0,0,1,0,1]
output: 2

2)
input: seats = [1,0,0,0,0,1,0,1]
output: 2

3)
input: seats = [1,0,0,0,0,0,1,0,1]
output: 3

4)
input: seats = [0,0,0,0,0,1,0,1]
output: 5

 */

public class MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int maxDistance = 1;
        int countZeroes = 0;
        boolean openStart = seats[0] == 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                ++countZeroes;
                if (openStart || i == seats.length - 1) {
                    maxDistance = Math.max(maxDistance, countZeroes);
                } else {
                    maxDistance = Math.max(maxDistance, countZeroes / 2 + countZeroes % 2);
                }
                continue;
            }
            openStart = false;
            countZeroes = 0;
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        int[] seats = {1,0,0,0,0,1,0,1};
        MaximizeDistanceToClosestPerson maximizeDistanceToClosestPerson = new MaximizeDistanceToClosestPerson();
        System.out.println(maximizeDistanceToClosestPerson.maxDistToClosest(seats));
    }
}
