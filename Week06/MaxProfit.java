/**
 * 121 股票最大和 =》见第二题的转化
 */
class MaxProfit {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = -Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(temp, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, prices[i]);
        }
        return dp_i_0;
    }
}