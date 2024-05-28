package org.algorithm.pointer;

/**
 * <h3>wsd-project</h3>
 * <p>使用快慢针移除未排序数组中指定数字</p>
 *
 * @author : 王松迪
 * 2024-05-28 10:21
 **/
public class RemoveAssignNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,2,3,4,3};
        int length = removeAssign(arr, 3);
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i]);
        }

    }

    public static int removeAssign(int[] arr, int num) {

        int slow = 0, fast = 0;
        while(fast < arr.length) {
            if(arr[fast] != num) {
                arr[slow++] = arr[fast];
            }
            fast++;
        }
        return slow;
    }
}
