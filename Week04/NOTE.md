```java
class Solution {
    public int findDisorder(int nums[]) throws Exception {
        if (nums.length < 2) {
            throw new Exception("error");
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left < right) {
            if (nums[mid] == 0 && nums[mid] > nums[mid + 1]) {
                return nums[mid];
            }
            if (nums[mid] == nums.length && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[left] < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        throw new Exception("error");
    }
}
```


### 总结：
- 1.贪心算法，只适用特定场景，有些切入点有点奇怪，需要自己理解。
- 2.牛顿迭代法还是看不懂数学公式，还是套代码模板把。
- 3.关于BFS和DFS还是需要多联系。
- 4.二分查找有时候有边界值和数组越界的问题，需要在代码里面多注意
