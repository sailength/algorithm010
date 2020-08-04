/**
 * 123 股票最大和 ，含1回合cd
 */
class MaxProfit3 {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int dp_i_0 = 0, dp_pre_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }
}