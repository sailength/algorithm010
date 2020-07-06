
/**
 * 50
 */
class MyPow {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1.0 / x;
        }
        return fastPow(x, N);


    }

    public double fastPow(double x, long n) {
        if (n == 0)
            return 1.0;
        double half = fastPow(x, n / 2);
        return n % 2 == 1 ? half * half * x : half * half;
    }


}