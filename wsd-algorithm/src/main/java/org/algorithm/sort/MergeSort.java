package org.algorithm.sort;

import java.util.*;

/**
 * <h3>wsd-project</h3>
 * <p>归并排序</p>
 *
 * @author : 王松迪
 * 2024-03-27 16:13
 **/
public class MergeSort {

    public static void sort(int[] arr) {


        doSort(arr, 0, arr.length - 1, 0, null);

        tierList.sort(Comparator.comparing(Tier::getTier));

        for (Tier tier : tierList) {
            System.out.println(tier.msg);
        }


    }

    private static void doSort(int[] arr, int begin, int end, int tier, String lr) {
        if(begin < end ) {
            tier++;
            int mid = ((end - begin) >>> 1) + begin;
            doSort(arr, begin, mid, tier, null == lr ? "左" : lr);
            doSort(arr, mid + 1, end, tier, null == lr ? "右" : lr);
            merge(arr, begin, mid, end, tier, lr);
        }
    }


    private static List<Tier> tierList = new ArrayList<>();

    private static void merge(int[] arr, int begin, int mid, int end, int tier, String lr) {

        if("左".equals(lr)){
            tierList.add(new Tier(tier, String.format("第%d层, 归并排序, %s分区， 区间[%d, %d], 区间[%d, %d]", tier, lr, begin, mid, mid + 1, end)));
        }

        int[] mergeArr = new int[end - begin + 1];
        int lp = begin;
        int rp = mid + 1;
        int mp = 0;
        while(lp <= mid && rp <= end) {

            int mv;
            if(arr[lp] < arr[rp]){
                mv = arr[lp++];
            } else {
                mv = arr[rp++];
            }
            mergeArr[mp++] = mv;
        }

        while(lp <= mid) {
            mergeArr[mp++] = arr[lp++];
        }

        while(rp <= end) {
            mergeArr[mp++] = arr[rp++];
        }

        System.arraycopy(mergeArr, 0, arr, begin, mergeArr.length);
    }


    private static class Tier  {

        private Integer tier;

        private String msg;


        public Tier(int tier, String msg) {
            this.tier = tier;
            this.msg = msg;
        }

        public Integer getTier() {
            return tier;
        }

        public void setTier(Integer tier) {
            this.tier = tier;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static void main(String[] args) {
        int[] arr = new Random(100).ints(0, 100).distinct().limit(100).toArray();
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
