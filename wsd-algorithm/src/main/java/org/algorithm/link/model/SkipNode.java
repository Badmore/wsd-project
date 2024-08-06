package org.algorithm.link.model;

public class SkipNode<T extends Comparable<T>>{

    public T value;
    public SkipNode<T> left;
    public SkipNode<T> right;
    public SkipNode<T> up;
    public SkipNode<T> down;

    public SkipNode(T value) {
        this.value = value;
    }

}
