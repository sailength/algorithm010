
/**
 * dp[i]=max(dp[i-2]+nums[i],dp[i-1])
 */
class Rob {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int a = 0, b = 0, tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = b;
            b = Math.max(a + nums[i], b);
            a = temp;
        }
        return b;
    }
}