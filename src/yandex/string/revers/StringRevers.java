package yandex.string.revers;

public class StringRevers {
    public static void main(String[] args) {
        String s1 = "Dimas";

        char[] chars =  s1.toCharArray();

        for (int i = 0, j = chars.length-1; i<= chars.length/2 && j>=chars.length/2; i++, j--) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }

        System.out.println(new String(chars));
    }
}
