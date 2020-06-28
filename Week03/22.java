import java.util.ArrayList;
import java.util.List;

class Solution {

    private List<String> list = new ArrayList();

    public List<String> generateParenthesis(int n) {
        rec(0, 0, n, "");
        return list;
    }

    public void rec(int left, int right, int n, String s) {
        if (left == n && right == n) {
            list.add(s);
        }
        if (left > right) {
            rec(left, right+1, n, s + ")");
        }
        if (left < n) {
            rec(left+1, right, n, s + "(");
        }
    }


}