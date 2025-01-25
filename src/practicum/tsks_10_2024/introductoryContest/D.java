package practicum.tsks_10_2024.introductoryContest;

public class D {

    public static void main(String[] args) {
        Long l1 = 1L;
        Long l2 = 1L;
        Long l3 = 1l;

        System.out.println(l1.equals(l2));
        System.out.println(l1 == (l2 + l3 - l1));
    }
}
