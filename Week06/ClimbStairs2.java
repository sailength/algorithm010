/**
 * 爬楼梯(走三步的情况)
 * fn=fn-1+fn-2+fn-3
 * 滑动数组
 */
class ClimbStairs2 {
    public int climbStairs(int n) {
        int a = 0, b = 1, c = 2, r = 0;
        for (int i = 2; i <= n; i++) {
            r = a + b + c;
            a = b;
            b = c;
            c = r;
        }
        return r;
    }
}