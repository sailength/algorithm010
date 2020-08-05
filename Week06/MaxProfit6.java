/**
 * k=any 188交易股票
 */
class MaxProfit6 {
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        if (k > length / 2) {
            return maxProfitInf(prices, length);
        } else {
            return maxProfitAny(prices, k, length);
        }
    }

    public int maxProfitAny(int[] prices, int k, int length) {
        int dp[][] = new int[k+1][2];
        for (int i = 0; i < length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    /** 处理基础值*/
                    dp[j][0] = 0;
                    dp[j][1] = -prices[i];
                    continue;
                }

                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];
    }

    /**
     * 使用最终O(1)的空间解决问题
     */
    public int maxProfitInf(int[] prices, int length) {
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