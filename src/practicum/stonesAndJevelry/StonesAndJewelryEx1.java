package practicum.stonesAndJevelry;

public class StonesAndJewelryEx1 {
    public static void main(String[] args) {
        String J = "qwertyuiop";
        String S = "awdrgyji";

        char[] jChar = J.toCharArray();
        int count = 0;
        for (char ch: jChar) {
            if (S.indexOf(ch) >= 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
