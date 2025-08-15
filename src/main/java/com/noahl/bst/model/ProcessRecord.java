package com.noahl.bst.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "process_records")
public class ProcessRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store as native MySQL JSON
    @Column(name = "input_numbers_json", columnDefinition = "JSON", nullable = false)
    private String inputNumbersJson;

    @Column(name = "tree_json", columnDefinition = "JSON", nullable = false)
    private String treeJson;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public String getInputNumbersJson() { return inputNumbersJson; }
    public void setInputNumbersJson(String inputNumbersJson) { this.inputNumbersJson = inputNumbersJson; }
    public String getTreeJson() { return treeJson; }
    public void setTreeJson(String treeJson) { this.treeJson = treeJson; }
    public Instant getCreatedAt() { return createdAt; }
}
