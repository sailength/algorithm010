class FindDisorder {
    public static int findDisorder(int nums[]) throws Exception {
        if (nums.length < 2) {
            throw new Exception("error");
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left < right) {

            if (mid == 0 && nums[mid] > nums[mid + 1]) {
                return nums[mid];
            }
            if (mid == nums.length && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[left] < nums[mid]) {
                left = mid;
                mid = (left + right) / 2;
            } else {
                right = mid;
                mid = (left + right) / 2;
            }
        }
        return nums[mid];

    }

    public static void main(String[] args) throws Exception {
        int arr[] = {4, 5, 6, 7, 8, 9, 1, 2, 3};
        System.out.println(findDisorder(arr));
    }
}