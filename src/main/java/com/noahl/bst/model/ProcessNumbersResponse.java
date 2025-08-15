package com.noahl.bst.model;

public class ProcessNumbersResponse {
    private Long id;       // DB record id
    private Node tree;     // nested JSON of the BST
    private String message;
    private boolean success;

    public ProcessNumbersResponse() {}
    public ProcessNumbersResponse(Long id, Node tree, String message, boolean success) {
        this.id = id; this.tree = tree; this.message = message; this.success = success;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Node getTree() { return tree; }
    public void setTree(Node tree) { this.tree = tree; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}
