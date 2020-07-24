/**
 * 爬楼梯
 * fn=fn-1+fn-2
 * 滑动数组
 */
class ClimbStairs {
    public int climbStairs(int n) {
        int p = 0, q = 1, r = 0;
        for (int i = 1; i <= n; i++) {
            r = p + q;
            p = q;
            q = r;

        }
        return r;
    }
}