package leetcode.easy.array;

/*
1) prices = {7,1,5,3,6,4} -> 7
2) prices = {1,2,3,4,5} -> 4
3) prices = {7,6,4,3,1} -> 0

 */

public class BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int maxProfit = 0;
        int bought = prices[0];
        int prev = prices[0];
        for (int i=1; i < prices.length; i++) {
            if (prev > prices[i]) {
                maxProfit += prices[i-1] - bought;
                bought = prices[i];
            }
            prev = prices[i];

            if (i == prices.length - 1) {
                maxProfit += prev - bought;
            }
        }
        return maxProfit;
    }
}
