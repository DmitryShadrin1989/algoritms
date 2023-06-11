package alex.a2.myDeque;

public class ArrayDequeTest {

    public static void addRemoveTest() {
        ArrayDeque<Integer> ard1 = new ArrayDeque<>();

        ard1.addLast(10);
        ard1.addLast(11);
        ard1.addLast(12);
        ard1.addLast(13);
        ard1.addLast(14);
        ard1.addLast(15);

//        ard1.addFirst(16);
//        ard1.addFirst(17);
//        ard1.addFirst(18);
//        ard1.addFirst(19);
//        ard1.addFirst(20);
//        ard1.addFirst(21);
//        ard1.addFirst(22);
//        ard1.addFirst(23);

        System.out.println(ard1.removeLast());
        System.out.println(ard1.removeFirst());

        int a = 0;
    }

    public static void main(String[] args) {
        addRemoveTest();
    }
}
