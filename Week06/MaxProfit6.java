/**
 * k=any 188交易股票 暂时超时
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
        int dp[][][] = new int[length ][k+1][2];
        for (int i = 0; i <= length; i++) {
            for (int j = k; j >= 1; k--) {
                if (i == 0) {
                    /** 处理基础值*/
                    dp[i][j][0] = 0;
                    dp[i][j][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[length-1][k][0];
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