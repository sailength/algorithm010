import java.util.ArrayList;
import java.util.List;

/** 78*/
class Subsets {


    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, new ArrayList(),nums.length, 0);
        return result;
    }

    public void dfs(int[] nums, List<Integer> array, int length, int level) {
        if (array.size() == length) {
            result.add(array);
            return;
        }
        dfs(nums, array, length, level + 1);
        array.add(nums[level]);
        dfs(nums, array, length, level + 1);
        array.remove(length - 1);
    }
}