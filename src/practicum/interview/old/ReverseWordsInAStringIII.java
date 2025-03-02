package practicum.interview.old;

/*
Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Example 2:

Input: s = "Mr Ding"
Output: "rM gniD"

 */

public class ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String str : strings) {
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            for(int i = str.length() - 1; i >= 0; i--) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public String reverseWords2(String s) {
        StringBuilder l= new StringBuilder();
        for(String i: s.split(" ")){
            StringBuilder str= new StringBuilder(i);
            str.reverse();
            l.append(" ");
            l.append(str);
        }
        l.deleteCharAt(0);
        return l.toString();
    }
}
