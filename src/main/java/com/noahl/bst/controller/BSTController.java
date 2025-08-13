package com.noahl.bst.controller;

import com.noahl.bst.model.*;
import com.noahl.bst.service.BSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bst")
@CrossOrigin(origins = "*")
public class BSTController {

    @Autowired
    private BSTService bstService;

    @PostMapping("/insert/{value}")
    public ResponseEntity<TreeResponse> insert(@PathVariable int value) {
        try {
            Node tree = bstService.insert(value);
            TreeResponse response = createTreeResponse(tree, "Successfully inserted " + value, true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            TreeResponse response = new TreeResponse(null, "Error inserting " + value + ": " + e.getMessage(), false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestBody SearchRequest request) {
        try {
            SearchResponse response = bstService.search(request.getValue());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            SearchResponse response = new SearchResponse(false, null, 0, "Error searching: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete/{value}")
    public ResponseEntity<TreeResponse> delete(@PathVariable int value) {
        try {
            Node tree = bstService.delete(value);
            TreeResponse response = createTreeResponse(tree, "Successfully deleted " + value, true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            TreeResponse response = new TreeResponse(null, "Error deleting " + value + ": " + e.getMessage(), false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/tree")
    public ResponseEntity<TreeResponse> getTree() {
        Node tree = bstService.getRoot();
        TreeResponse response = createTreeResponse(tree, "Tree retrieved successfully", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/traversal/{type}")
    public ResponseEntity<TreeResponse> getTraversal(@PathVariable String type) {
        try {
            Node tree = bstService.getRoot();
            TreeResponse response = createTreeResponse(tree, "Traversal completed", true);

            switch (type.toLowerCase()) {
                case "inorder":
                    response.setTraversal(bstService.inorderTraversal());
                    break;
                case "preorder":
                    response.setTraversal(bstService.preorderTraversal());
                    break;
                case "postorder":
                    response.setTraversal(bstService.postorderTraversal());
                    break;
                default:
                    response.setMessage("Invalid traversal type. Use: inorder, preorder, or postorder");
                    response.setSuccess(false);
                    return ResponseEntity.badRequest().body(response);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            TreeResponse response = new TreeResponse(null, "Error during traversal: " + e.getMessage(), false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/clear")
    public ResponseEntity<TreeResponse> clear() {
        bstService.clear();
        TreeResponse response = new TreeResponse(null, "Tree cleared successfully", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sample")
    public ResponseEntity<TreeResponse> buildSampleTree() {
        bstService.buildSampleTree();
        Node tree = bstService.getRoot();
        TreeResponse response = createTreeResponse(tree, "Sample tree built successfully", true);
        return ResponseEntity.ok(response);
    }

    private TreeResponse createTreeResponse(Node tree, String message, boolean success) {
        TreeResponse response = new TreeResponse(tree, message, success);
        response.setHeight(bstService.getHeight());
        response.setMin(bstService.getMin());
        response.setMax(bstService.getMax());
        response.setSize(bstService.getSize());
        return response;
    }
}
