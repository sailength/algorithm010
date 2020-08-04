/**
 * 122 股票最大和II 。可以无限交易
 */
class MaxProfit2 {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int dp[][] = new int[length + 1][2];
        dp[1][0] = 0;
        dp[1][1] = -prices[0];
        for (int i = 2; i <= length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }
        return dp[length][0];
    }


    /**
     * 使用滑动数组解决问题
     */
    public int maxProfit2(int[] prices) {
        int length = prices.length;
        int dp[][] = new int[length + 1][2];
        dp[0][1] = -Integer.MAX_VALUE;
        for (int i = 1; i <= length; i++) {
            int temp = dp[i - 1][0];
            dp[i][0] = Math.max(temp, dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], temp - prices[i - 1]);
        }
        return dp[length][0];
    }


    /**
     * 使用最终O(1)的空间解决问题
     */
    public int maxProfit3(int[] prices) {
        int length = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = -Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(temp, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

}