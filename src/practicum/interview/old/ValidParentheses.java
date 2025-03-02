package practicum.interview.old;

/*
1)
Input: s = "(())[]{()}()"
Output: true

2)
Input: s = "(())[]{()}())"
Output: false

3)
Input: s = "(())[]{()}))"
Output: false

 */


import java.util.LinkedList;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        LinkedList<Character> stack = new LinkedList<>();
        char ch = s.charAt(0);
        if (ch == ')' || ch == '}' ||ch == ']') {
            return false;
        }
        stack.push(ch);
        for (int i = 1; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                if (stack.size() > s.length() / 2) {
                    return false;
                }
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            char open = stack.pop();
            if ((open == '(' && ch != ')') || (open == '{' && ch != '}') || (open == '[' && ch != ']')) {
                return false;
            }
        }
        ch = s.charAt(s.length() - 1);
        if (ch == '(' || ch == '{' ||ch == '[') {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        String s = "(())[]{()}()";
        System.out.println(validParentheses.isValid(s));
    }
}
