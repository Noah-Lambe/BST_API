package com.noahl.bst.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node {
    private int data;
    private Node left;
    private Node right;

    public Node() {}

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
    public int getData() { return data; }
    public void setData(int data) { this.data = data; }
    public Node getLeft() { return left; }
    public void setLeft(Node left) { this.left = left; }
    public Node getRight() { return right; }
    public void setRight(Node right) { this.right = right; }
}
