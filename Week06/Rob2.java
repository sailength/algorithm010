import java.util.Arrays;

/**
 * dp[i]=max(dp[i-2]+nums[i],dp[i-1])
 * 适当改写一些，只需要计算两个区间的最大值就行了
 * 1->n-1
 * 0->n-2
 */
class Rob2 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robSingle(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                robSingle(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    public int robSingle(int[] nums) {
        int a = 0, b = 0, tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = b;
            b = Math.max(a + nums[i], b);
            a = temp;
        }
        return b;
    }
}