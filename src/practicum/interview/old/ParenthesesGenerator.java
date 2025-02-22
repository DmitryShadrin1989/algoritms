package practicum.interview.old;

import java.util.ArrayList;
import java.util.List;

/*
1) n=2
Output:
(())
()()

2) n=3
Output:
((()))
(()())
(())()
()(())
()()()

Debug:
n=3
1. ((()))

(())()


 */

public class ParenthesesGenerator {

    public static void main(String[] args) {
        int n = 3;
        ParenthesesGenerator generator = new ParenthesesGenerator();
        List<String> result = generator.generateParenthesis(n);
        for (String s : result) {
            System.out.println(s);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String currString, int currOpen, int currClose, int n) {
        if (currString.length() == n*2) {
            result.add(currString);
            return;
        }

        if (currOpen < n) {
            String nextString = currString + "(";
            int nextOpen = currOpen + 1;
            generate(result, nextString, nextOpen, currClose, n);
        }

        if (currClose < currOpen) {
            String nextString = currString + ")";
            int nextClose = currClose + 1;
            generate(result, nextString, currOpen, nextClose, n);
        }
    }
}
