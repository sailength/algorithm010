/**
 * k=2 习题123，最多交易2笔
 */
class MaxProfit5 {
    public int maxProfit(int[] prices, int fee) {
        int length = prices.length;
        int dp_i_1_0 = 0, dp_i_2_0 = 0;
        int dp_i_1_1 = Integer.MIN_VALUE, dp_i_2_1 = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + prices[i]);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - prices[i]);
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + prices[i]);
            dp_i_1_1 = Math.max(dp_i_1_1, -prices[i]);
        }
        return dp_i_2_0;
    }
}