package org.algorithm.bit;

/**
 * <h3>wsd-project</h3>
 * <p>使用 bitmap 匹配用户标签</p>
 *
 * @author : 王松迪
 * 2024-03-12 10:14
 **/
public class BitMapOfLabel {

    /**
     * 每一个 word 是一个 long 类型元素，对应一个 64位二进制数据。
     */
    private final long[] words;

    /**
     * bitmap 位数大小
     */
    private final int size;

    public BitMapOfLabel(int size) {
        this.size = size;
        words = new long[getWordIndex(size -1) + 1];
        System.out.println(words.length);
    }


    private int getWordIndex(int i) {
        //右移 6 位 相当于 ÷2÷2÷2÷2÷2÷2 = ÷64;long 类型需要 / 64，避免计算出超出 long 型的位移限制；比如 1 << 128 = 1 << 128 % 64
        return i >> 6;
    }

    public boolean getBit(int bitIndex) {
        if(bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过bitMap 有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        long word = words[wordIndex];
        long l = 1L << bitIndex;
        long l1 = word & l;
        System.out.println("get word=" + Long.toBinaryString(word) + ",l=" + Long.toBinaryString(l) + ",l1=" + Long.toBinaryString(l1));
        return l1 != 0;
    }

    public void setBit(int bitIndex) {
        if(bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过bitMap 有效范围");
        }

        int wordIndex = getWordIndex(bitIndex);
        long word = words[wordIndex];
        long l = 1L << bitIndex;
        long l1 = words[wordIndex] | l;
        words[wordIndex] = l1;

        System.out.println("set word=" + Long.toBinaryString(word) + ",l=" + Long.toBinaryString(l) + ",l1=" + Long.toBinaryString(l1));
    }


    public static void main(String[] args) {
        BitMapOfLabel bitMap = new BitMapOfLabel(129);
        bitMap.setBit(126);
        bitMap.setBit(75);

        System.out.println(bitMap.getBit(75));



    }
}
