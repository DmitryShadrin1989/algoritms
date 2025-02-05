package practicum.strings.revers;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringReversStack {
    public static void main(String[] args) {
        String s1 = "Dmitry very good programmer!";
        Deque<Character> stack = new ArrayDeque<>();
        for (int i=0; i<s1.length(); i++) {
            stack.add(s1.charAt(i));
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pollLast());
        }
        System.out.println(stringBuilder);
    }
}
