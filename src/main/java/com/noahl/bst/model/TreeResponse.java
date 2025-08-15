package com.noahl.bst.model;

import java.util.List;

public class TreeResponse {
    private Node tree;
    private List<Integer> traversal;
    private String message;
    private boolean success;
    private int height;
    private Integer min;
    private Integer max;
    private int size;

    public TreeResponse() {}

    public TreeResponse(Node tree, String message, boolean success) {
        this.tree = tree;
        this.message = message;
        this.success = success;
    }

    public Node getTree() { return tree; }
    public void setTree(Node tree) { this.tree = tree; }
    public List<Integer> getTraversal() { return traversal; }
    public void setTraversal(List<Integer> traversal) { this.traversal = traversal; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public Integer getMin() { return min; }
    public void setMin(Integer min) { this.min = min; }
    public Integer getMax() { return max; }
    public void setMax(Integer max) { this.max = max; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}
