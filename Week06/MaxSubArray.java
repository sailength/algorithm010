
/**
 * 最长子序列和 53
 */
class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int prev = nums[i - 1] > 0 ? nums[i - 1] : 0;
            nums[i] += prev;
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}