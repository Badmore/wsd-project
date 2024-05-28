package org.algorithm.pointer;

/**
 * <h3>wsd-project</h3>
 * <p>使用快慢针原地对排序后的数组中的重复元素去重</p>
 *
 * @author : 王松迪
 * 2024-05-28 09:13
 **/
public class DuplicatesNumber {

    public static void main(String[] args) {

        int[] arr = {1, 3, 3, 3, 4, 5};
        for (int i = 0; i < removeDuplicates(arr); i++) {
            System.out.print(i + ", ");
        }
    }

    public static int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        while(fast < nums.length) {
            if(nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

}
