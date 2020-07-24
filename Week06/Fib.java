/**
 * 509 斐波那契数
 */

class Fib {
    /** 最佳空间时间dp解*/
    public int fib(int n) {
        int sum = 0, a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

}

