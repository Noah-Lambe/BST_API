package com.noahl.bst.service;

import com.noahl.bst.model.SearchResponse;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BSTService")
class BSTServiceTest {

    private BSTService service;

    @BeforeEach
    void setUp() {
        service = new BSTService();
    }

    private void buildSample() {
        service.buildSampleTree();
    }

    @Nested
    @DisplayName("insert()")
    class InsertTests {

        @Test
        @DisplayName("inserts values and maintains BST property (inorder sorted)")
        void insertsValuesInorderSorted() {
            buildSample();
            List<Integer> expectedInorder = Arrays.asList(10, 20, 25, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85);
            assertIterableEquals(expectedInorder, service.inorderTraversal());
            assertEquals(14, service.getSize());
            assertEquals(50, service.getRoot().getData());
        }

        @Test
        @DisplayName("ignores duplicates (size unchanged)")
        void ignoresDuplicates() {
            buildSample();
            int before = service.getSize();
            service.insert(50);
            service.insert(40);
            service.insert(85);
            assertEquals(before, service.getSize(), "Duplicate inserts should not change size");
            // Still valid
            List<Integer> expectedInorder = Arrays.asList(10, 20, 25, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85);
            assertIterableEquals(expectedInorder, service.inorderTraversal());
        }
    }

    @Nested
    @DisplayName("search()")
    class SearchTests {

        @Test
        @DisplayName("finds an existing value and returns correct path/steps")
        void findsExisting() {
            buildSample();
            SearchResponse resp = service.search(65);
            assertTrue(resp.isFound());
            assertEquals(Arrays.asList(50, 70, 60, 65), resp.getPath(), "Path should follow BST decisions");
            assertEquals(4, resp.getSteps());
            assertTrue(resp.getMessage().contains("found"));
        }

        @Test
        @DisplayName("returns not found and path to last checked node")
        void notFound() {
            buildSample();
            SearchResponse resp = service.search(42);
            assertFalse(resp.isFound());
            assertEquals(Arrays.asList(50, 30, 40, 45), resp.getPath(), "Should end at last non-null node");
            assertEquals(4, resp.getSteps());
            assertTrue(resp.getMessage().contains("not found"));
        }
    }

    @Nested
    @DisplayName("delete()")
    class DeleteTests {

        @Test
        @DisplayName("deletes a leaf node")
        void deleteLeaf() {
            buildSample();
            int before = service.getSize();
            service.delete(10);
            assertEquals(before - 1, service.getSize());
            assertFalse(service.inorderTraversal().contains(10));
        }

        @Test
        @DisplayName("deletes a node with one child")
        void deleteOneChild() {
            buildSample();
            // In sample tree, 60 has a single right child (65)
            int before = service.getSize();
            service.delete(60);
            assertEquals(before - 1, service.getSize());
            List<Integer> inorder = service.inorderTraversal();
            assertFalse(inorder.contains(60));
            assertTrue(inorder.contains(65), "Child should be reattached");
        }

        @Test
        @DisplayName("deletes a node with two children (uses inorder successor)")
        void deleteTwoChildren() {
            buildSample();
            // 70 has two children; successor should be 75
            int before = service.getSize();
            service.delete(70);
            assertEquals(before - 1, service.getSize());
            List<Integer> inorder = service.inorderTraversal();
            assertFalse(inorder.contains(70));
            assertTrue(inorder.contains(75));
            // Still sorted
            List<Integer> expectedSorted = inorder.stream().sorted().toList();
            assertIterableEquals(expectedSorted, inorder, "BST should remain valid");
        }
    }

    @Nested
    @DisplayName("traversals")
    class TraversalTests {

        @Test
        @DisplayName("inorder traversal is sorted")
        void inorderSorted() {
            buildSample();
            List<Integer> inorder = service.inorderTraversal();
            List<Integer> expected = inorder.stream().sorted().toList();
            assertIterableEquals(expected, inorder);
        }

        @Test
        @DisplayName("preorder traversal matches expected for sample tree")
        void preorderMatches() {
            buildSample();
            List<Integer> expected = Arrays.asList(50, 30, 20, 10, 25, 40, 35, 45, 70, 60, 65, 80, 75, 85);
            assertIterableEquals(expected, service.preorderTraversal());
        }

        @Test
        @DisplayName("postorder traversal matches expected for sample tree")
        void postorderMatches() {
            buildSample();
            List<Integer> expected = Arrays.asList(10, 25, 20, 35, 45, 40, 30, 65, 60, 75, 85, 80, 70, 50);
            assertIterableEquals(expected, service.postorderTraversal());
        }
    }

    @Nested
    @DisplayName("metadata/getters")
    class MetaTests {

        @Test
        @DisplayName("height is -1 for empty tree")
        void heightEmpty() {
            assertEquals(-1, service.getHeight());
        }

        @Test
        @DisplayName("height is correct for sample tree")
        void heightSample() {
            buildSample();
            assertEquals(3, service.getHeight(), "Longest path has 3 edges");
        }

        @Test
        @DisplayName("min/max return null on empty, correct values otherwise")
        void minMax() {
            assertNull(service.getMin());
            assertNull(service.getMax());
            buildSample();
            assertEquals(10, service.getMin());
            assertEquals(85, service.getMax());
        }

        @Test
        @DisplayName("size tracks number of nodes")
        void sizeTracks() {
            assertEquals(0, service.getSize());
            service.insert(2);
            service.insert(1);
            service.insert(3);
            assertEquals(3, service.getSize());
            service.insert(3); // duplicate, ignored
            assertEquals(3, service.getSize());
        }

        @Test
        @DisplayName("clear() empties the tree")
        void clearEmpties() {
            buildSample();
            assertTrue(service.getSize() > 0);
            service.clear();
            assertEquals(0, service.getSize());
            assertTrue(service.inorderTraversal().isEmpty());
            assertEquals(-1, service.getHeight());
            assertNull(service.getMin());
            assertNull(service.getMax());
            assertNull(service.getRoot());
        }
    }

    @Nested
    @DisplayName("buildSampleTree()")
    class BuildSampleTests {
        @Test
        @DisplayName("creates expected tree with correct traversals and size")
        void buildsExpectedSample() {
            buildSample();

            assertEquals(14, service.getSize());
            assertEquals(50, service.getRoot().getData());

            assertIterableEquals(
                    Arrays.asList(10, 20, 25, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85),
                    service.inorderTraversal()
            );
            assertIterableEquals(
                    Arrays.asList(50, 30, 20, 10, 25, 40, 35, 45, 70, 60, 65, 80, 75, 85),
                    service.preorderTraversal()
            );
            assertIterableEquals(
                    Arrays.asList(10, 25, 20, 35, 45, 40, 30, 65, 60, 75, 85, 80, 70, 50),
                    service.postorderTraversal()
            );
            assertEquals(3, service.getHeight());
        }
    }
}
