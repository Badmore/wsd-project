package org.algorithm.search;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * <h3>wsd-project</h3>
 * <p>利用滑动窗口查询最小子串</p>
 *
 * @author : 王松迪
 * 2024-06-04 09:05
 **/
public class SlidingWindow {


    //左右指针
    private int right = 0, left = 0,
            // 窗口起始位置与长度
            start = 0 ,len = Integer.MAX_VALUE,
                // 窗口内有效字符个数
                valid = 0;
    private final HashMap<Character, Integer> window = Maps.newHashMap(), need = Maps.newHashMap();
    private final String input ;
    private final Consumer<Character> providerExtend, providerShrink;

    public SlidingWindow(String input, String target) {

        this.input = input;
        for (char c : target.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        providerExtend = c -> {
            if(need.containsKey(c) && need.get(c).equals(window.get(c))) {
                valid++;
            }
        };

        providerShrink = c -> {
            if(need.containsKey(c) && window.get(c).equals(need.get(c))) {
                valid--;
            }
        };

    }

    public String minSubstring() {
        while (right < input.length()) {
            expend();
            while(needShrink()) {
                adjustLen();
                shrink();
            }
        }

        return len == Integer.MAX_VALUE ? "" : input.substring(start, start + len);
    }


    /**
     * 判断是否需要缩小窗口
     * @return true
     */
    private boolean needShrink() {
        return valid == need.size();
    }

    /**
     * 右指针向右滑动， 扩展窗口
     */
    private void expend() {
        char c = input.charAt(right++);
        window.put(c, window.getOrDefault(c, 0) + 1);
        providerExtend.accept(c);
    }

    /**
     * 左指针向右滑动
     */
    private void shrink() {
        char d = input.charAt(left++);
        providerShrink.accept(d);
        if(window.containsKey(d)) {
            window.put(d, window.get(d) - 1);
        }
    }


    /**
     * 调整窗口大小
     */
    private void adjustLen() {
        if(right - left < len) {
            start = left;
            len = right - left;
        }
    }

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow("ebbdsdfdfghfgasfsdfsdfahjklancf", "ahkl");

        String s = slidingWindow.minSubstring();

        System.out.println("最小子串 " + s);
    }

}
