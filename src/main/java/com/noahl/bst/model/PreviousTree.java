package com.noahl.bst.model;

import java.time.Instant;
import java.util.List;

public class PreviousTree {
    private Long id;
    private Instant createdAt;
    private List<Integer> numbers;
    private Node tree;

    public PreviousTree() {}

    public PreviousTree(Long id, Instant createdAt, List<Integer> numbers, Node tree) {
        this.id = id; this.createdAt = createdAt; this.numbers = numbers; this.tree = tree;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public List<Integer> getNumbers() { return numbers; }
    public void setNumbers(List<Integer> numbers) { this.numbers = numbers; }

    public Node getTree() { return tree; }
    public void setTree(Node tree) { this.tree = tree; }
}
