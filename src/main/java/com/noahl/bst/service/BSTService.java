package com.noahl.bst.service;

import com.noahl.bst.model.Node;
import com.noahl.bst.model.SearchResponse;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BSTService {

    private Node root;

    public Node insert(int data) {
        root = insertRecursive(root, data);
        return root;
    }

    private Node insertRecursive(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.getData()) {
            node.setLeft(insertRecursive(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(insertRecursive(node.getRight(), data));
        }

        return node;
    }

    public SearchResponse search(int data) {
        List<Integer> path = new ArrayList<>();
        boolean found = searchRecursive(root, data, path);
        String message = found ?
                "Value " + data + " found in " + path.size() + " steps!" :
                "Value " + data + " not found after checking " + path.size() + " nodes.";

        return new SearchResponse(found, path, path.size(), message);
    }

    private boolean searchRecursive(Node node, int data, List<Integer> path) {
        if (node == null) {
            return false;
        }

        path.add(node.getData());

        if (node.getData() == data) {
            return true;
        }

        if (data < node.getData()) {
            return searchRecursive(node.getLeft(), data, path);
        } else {
            return searchRecursive(node.getRight(), data, path);
        }
    }

    public Node delete(int data) {
        root = deleteRecursive(root, data);
        return root;
    }

    private Node deleteRecursive(Node node, int data) {
        if (node == null) {
            return node;
        }

        if (data < node.getData()) {
            node.setLeft(deleteRecursive(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(deleteRecursive(node.getRight(), data));
        } else {
            // Node to delete found
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                // Node has two children
                Node successor = findMin(node.getRight());
                node.setData(successor.getData());
                node.setRight(deleteRecursive(node.getRight(), successor.getData()));
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node node, List<Integer> result) {
        if (node != null) {
            inorderRecursive(node.getLeft(), result);
            result.add(node.getData());
            inorderRecursive(node.getRight(), result);
        }
    }

    public List<Integer> preorderTraversal() {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    private void preorderRecursive(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.getData());
            preorderRecursive(node.getLeft(), result);
            preorderRecursive(node.getRight(), result);
        }
    }

    public List<Integer> postorderTraversal() {
        List<Integer> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void postorderRecursive(Node node, List<Integer> result) {
        if (node != null) {
            postorderRecursive(node.getLeft(), result);
            postorderRecursive(node.getRight(), result);
            result.add(node.getData());
        }
    }

    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node node) {
        if (node == null) return -1;
        return Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight())) + 1;
    }

    public Integer getMin() {
        if (root == null) return null;
        Node node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    public Integer getMax() {
        if (root == null) return null;
        Node node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getData();
    }

    public int getSize() {
        return calculateSize(root);
    }

    private int calculateSize(Node node) {
        if (node == null) return 0;
        return calculateSize(node.getLeft()) + calculateSize(node.getRight()) + 1;
    }

    public Node getRoot() {
        return root;
    }

    public void clear() {
        root = null;
    }

    public void buildSampleTree() {
        clear();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 65, 75, 85};
        for (int value : values) {
            insert(value);
        }
    }
}
