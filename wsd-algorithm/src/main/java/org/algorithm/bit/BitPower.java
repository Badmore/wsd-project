package org.algorithm.bit;

/**
 * <h3>wsd-project</h3>
 * <p>整数幂判断</p>
 *
 * @author : 王松迪
 * 2024-03-05 09:19
 **/
public class BitPower {

    public static void explain(){

        //2的整数次幂，转换为二进制，只有首位是 1，其余都为 0
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toBinaryString(32));
        System.out.println(Integer.toBinaryString(64));

        //2的整数次幂 - 1，转换为二进制，全部为 1
        System.out.println(Integer.toBinaryString(2-1));
        System.out.println(Integer.toBinaryString(4-1));
        System.out.println(Integer.toBinaryString(8-1));
        System.out.println(Integer.toBinaryString(16-1));
        System.out.println(Integer.toBinaryString(32-1));
        System.out.println(Integer.toBinaryString(64-1));
    }





    public static boolean isPowerOf2(int num) {
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(num - 1));
        return (num & num - 1) == 0;
    }

    public static void main(String[] args) {
        explain();
        System.out.println(isPowerOf2(32));
    }
}
