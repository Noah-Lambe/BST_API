package com.noahl.bst.model;

import java.util.List;

public class SearchResponse {
    private boolean found;
    private List<Integer> path;
    private int steps;
    private String message;

    public SearchResponse() {}

    public SearchResponse(boolean found, List<Integer> path, int steps, String message) {
        this.found = found;
        this.path = path;
        this.steps = steps;
        this.message = message;
    }

    // Getters and Setters
    public boolean isFound() { return found; }
    public void setFound(boolean found) { this.found = found; }
    public List<Integer> getPath() { return path; }
    public void setPath(List<Integer> path) { this.path = path; }
    public int getSteps() { return steps; }
    public void setSteps(int steps) { this.steps = steps; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
