package org.algorithm.search;

/**
 * <h3>wsd-project</h3>
 * <p>数组的二分查找</p>
 *
 * @author : 王松迪
 * 2024-04-29 08:40
 **/
public class BinarySearchArray {

    private int[] arr;

    public BinarySearchArray(int[] arr) {
        this.arr = arr;
    }

    public int binarySearch(int target) {

        int start = 0;
        int end = arr.length - 1;
        int mid;
        while(start <= end) {
            mid = start + ((end - start) / 2);
            if(arr[mid] == target) {
                return mid;
            }

            if(arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        BinarySearchArray binarySearchArray = new BinarySearchArray(arr);
        int index = binarySearchArray.binarySearch(13);
        System.out.println(index);
    }
}
