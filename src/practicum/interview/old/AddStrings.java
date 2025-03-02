package practicum.interview.old;

public class AddStrings {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        boolean isOneInMind = false;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j>=0) {
            int n1 = i >= 0 ? (num1.charAt(i) - '0') : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = n1 + n2;
            if (isOneInMind) {
                sum += 1;
                isOneInMind = false;
            }
            if (sum >= 10) {
                sum -= 10;
                isOneInMind = true;
            }
            sb.append(sum);
            i--;
            j--;
        }
        if (isOneInMind) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "123";

        AddStrings addStrings = new AddStrings();
        System.out.println(addStrings.addStrings(num1, num2));
    }
}
