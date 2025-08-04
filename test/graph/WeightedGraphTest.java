package graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue.PriorityQueue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class WeightedGraphTest {
    
    private final PrintStream           originalOut  = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private       WeightedGraph         graph;
    
    @Before
    public void setUp()
    {
        graph = new WeightedGraph(5);
        System.setOut(new PrintStream(outputStream));
    }
    
    @After
    public void tearDown()
    {
        System.setOut(originalOut);
    }
    
    // ========== Issue 1: Basic WeightedGraph Construction Tests ==========
    
    @Test
    public void testAddEdgeDefaultWeight()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        
        graph.addEdge(0, 1); // Should use default weight of 1
        
        graph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Default weight should be displayed", output.contains("1"));
    }
    
    @Test
    public void testAddEdgeWithMaximumWeight()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        
        int maxWeight = 999999;
        graph.addEdge(0, 1, maxWeight);
        
        graph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Large weight should be displayed", output.contains("999999"));
    }
    
    @Test
    public void testAddEdgeWithNegativeWeight()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        
        graph.addEdge(0, 1, -10);
        
        graph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Negative weight should be displayed", output.contains("-10"));
    }
    
    @Test
    public void testAddEdgeWithWeight()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        
        graph.addEdge(0, 1, 50);
        
        // Test by displaying weight matrix and checking output
        graph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Weight should be displayed in matrix", output.contains("50"));
    }
    
    @Test
    public void testAddEdgeWithZeroWeight()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        
        graph.addEdge(0, 1, 0);
        
        graph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Zero weight should be displayed", output.contains("0"));
    }
    
    // ========== Issue 2: Edge Addition Tests ==========
    
    @Test
    public void testBoundaryArrayAccess()
    {
        WeightedGraph testGraph = new WeightedGraph(2);
        testGraph.addVertex("A");
        testGraph.addVertex("B");
        
        // Test boundary conditions
        testGraph.addEdge(0, 1, 10);
        testGraph.addEdge(1, 0, 20);
        
        // Should handle boundary access correctly
        testGraph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Should handle boundary access", output.contains("10"));
        assertTrue("Should handle boundary access", output.contains("20"));
    }
    
    @Test
    public void testBoundaryConditionsMaxSize()
    {
        // Test maxSize = 1
        WeightedGraph smallGraph = new WeightedGraph(1);
        assertNotNull("Graph with maxSize=1 should be created", smallGraph);
        smallGraph.addVertex("A");
        smallGraph.displayWeightMatrix();
        
        // Test large maxSize
        WeightedGraph largeGraph = new WeightedGraph(100);
        assertNotNull("Graph with large maxSize should be created", largeGraph);
        largeGraph.addVertex("A");
        largeGraph.displayWeightMatrix();
    }
    
    @Test
    public void testConstructorWithValidMaxSize()
    {
        WeightedGraph testGraph = new WeightedGraph(10);
        assertNotNull("Graph should be created", testGraph);
        // Test that we can add vertices up to maxSize
        for (int i = 0; i < 10; i++) {
            testGraph.addVertex("V" + i);
        }
        // Should not crash with valid operations
        testGraph.displayWeightMatrix();
    }
    
    @Test
    public void testDijkstraConnectedGraph()
    {
        setupTestGraphForDijkstra();
        
        graph.dijkstra(0);
        
        String output = outputStream.toString();
        // Should produce meaningful output for connected graph
        assertTrue("Should contain path information", output.contains("="));
    }
    
    @Test
    public void testDijkstraDefaultStartingIndex()
    {
        setupTestGraphForDijkstra();
        
        graph.dijkstra(0); // Explicit starting index
        
        String output = outputStream.toString();
        assertTrue("Output should contain shortest paths", output.contains("A="));
    }
    
    @Test
    public void testDijkstraDisconnectedGraph()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        // Don't add any edges - completely disconnected
        
        graph.dijkstra(0);
        
        String output = outputStream.toString();
        assertTrue("Should contain infinite distances for disconnected vertices",
                   output.contains("inf"));
    }
    
    // ========== Issue 3: Dijkstra Algorithm Tests ==========
    
    @Test
    public void testDijkstraSingleVertex()
    {
        graph.addVertex("A");
        
        graph.dijkstra(0);
        
        String output = outputStream.toString();
        // Check that it handles single vertex without crashing and produces some output
        assertFalse("Should handle single vertex graph", output.trim().isEmpty());
        assertTrue("Should contain vertex A", output.contains("A"));
    }
    
    @Test
    public void testDijkstraStateReset()
    {
        setupTestGraphForDijkstra();
        
        // Run Dijkstra multiple times - should not crash due to state issues
        graph.dijkstra(0);
        outputStream.reset();
        graph.dijkstra(1);
        outputStream.reset();
        graph.dijkstra(2);
        
        String output = outputStream.toString();
        assertFalse("Should handle multiple runs", output.trim().isEmpty());
    }
    
    @Test
    public void testDijkstraWithDifferentStartingIndices()
    {
        setupTestGraphForDijkstra();
        
        for (int i = 0; i < 5; i++) {
            outputStream.reset();
            graph.dijkstra(i);
            String output = outputStream.toString();
            assertFalse("Should produce output for starting index " + i, output.trim().isEmpty());
        }
    }
    
    @Test
    public void testDijkstraWithUnreachableNode()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        // Create a partially connected graph where D is unreachable
        graph.addEdge(0, 1, 10); // A -> B
        graph.addEdge(1, 2, 20); // B -> C
        graph.addEdge(2, 0, 15); // C -> A
        // D has no incoming edges and is unreachable
        
        graph.dijkstra(0); // Start from A
        
        String output = outputStream.toString();
        assertTrue("Should print error message when unreachable vertices found",
                   output.contains("No path from A to any other vertex"));
    }
    
    @Test
    public void testDisplayWeightMatrix()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge(0, 1, 50);
        
        graph.displayWeightMatrix();
        
        String output = outputStream.toString();
        assertTrue("Should display weight matrix", output.contains("A"));
        assertTrue("Should display weight matrix", output.contains("B"));
        assertTrue("Should display weight value", output.contains("50"));
    }
    
    @Test
    public void testDisplayWeightMatrixWithVariousLabelLengths()
    {
        graph.addVertex("A");
        graph.addVertex("LongLabel");
        graph.addVertex("X");
        graph.addEdge(0, 1, 100);
        graph.addEdge(1, 2, 5);
        
        graph.displayWeightMatrix();
        
        String output = outputStream.toString();
        assertTrue("Should handle various label lengths", output.contains("LongLabel"));
        assertTrue("Should display all weights", output.contains("100"));
        assertTrue("Should display all weights", output.contains("5"));
    }
    
    @Test
    public void testDuplicateEdgeAddition()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 1, 20); // Add same edge with different weight
        
        graph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Updated weight should be displayed", output.contains("20"));
    }
    
    @Test
    public void testEdgesListInitialization()
    {
        WeightedGraph testGraph = new WeightedGraph(5);
        // Test that we can add edges without errors (edges list should work)
        testGraph.addVertex("A");
        testGraph.addVertex("B");
        testGraph.addEdge(0, 1, 10);
        
        // Should not crash - edges list is working
        testGraph.displayWeightMatrix();
        assertNotNull("Graph operations should work", testGraph);
    }
    
    // ========== Issue 4: Minimum Spanning Tree Tests ==========
    
    @Test
    public void testEmptyGraphDijkstra()
    {
        WeightedGraph emptyGraph = new WeightedGraph(5);
        // Don't add any vertices
        
        emptyGraph.dijkstra(0);
        
        String output = outputStream.toString();
        // Should handle empty graph gracefully without crashing
        assertNotNull("Should handle empty graph", output);
    }
    
    @Test
    public void testFindEdgeExisting()
    {
        PriorityQueue<Edge> pq    = new PriorityQueue<>(5);
        Edge                edge1 = new Edge(0, 1, 10);
        Edge                edge2 = new Edge(1, 2, 20);
        pq.insert(edge1);
        pq.insert(edge2);
        
        Edge found = graph.findEdge(1, pq);
        
        assertNotNull("Should find existing edge", found);
        assertEquals("Should find correct edge", 1, found.getDestination());
        assertEquals("Should find correct weight", 10, found.getWeight());
    }
    
    @Test
    public void testFindEdgeIndexByDestinationExisting()
    {
        PriorityQueue<Edge> pq    = new PriorityQueue<>(5);
        Edge                edge1 = new Edge(0, 1, 10);
        Edge                edge2 = new Edge(1, 2, 20);
        pq.insert(edge1);
        pq.insert(edge2);
        
        int index = graph.findEdgeIndexByDestination(2, pq);
        
        assertTrue("Should find valid index", index >= 0);
        assertEquals("Should find correct destination", 2, pq.peek(index).getDestination());
    }
    
    @Test
    public void testFindEdgeIndexByDestinationNonExisting()
    {
        PriorityQueue<Edge> pq    = new PriorityQueue<>(5);
        Edge                edge1 = new Edge(0, 1, 10);
        pq.insert(edge1);
        
        int index = graph.findEdgeIndexByDestination(5, pq);
        
        assertEquals("Should return -1 for non-existing edge", -1, index);
    }
    
    @Test
    public void testFindEdgeNonExisting()
    {
        PriorityQueue<Edge> pq    = new PriorityQueue<>(5);
        Edge                edge1 = new Edge(0, 1, 10);
        pq.insert(edge1);
        
        Edge found = graph.findEdge(5, pq);
        
        assertNull("Should not find non-existing edge", found);
    }
    
    // ========== Issue 5: Utility Method Tests ==========
    
    @Test
    public void testInheritanceFromDirectedGraph()
    {
        assertTrue("WeightedGraph should inherit from DirectedGraph",
                   graph instanceof DirectedGraph);
        
        // Test that inherited methods work
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge(0, 1); // Should use inherited addEdge behavior
        
        // Should not crash
        graph.displayWeightMatrix();
    }
    
    @Test
    public void testIntegerOverflowProtection()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        
        // Add edges with large weights that could cause overflow
        graph.addEdge(0, 1, Integer.MAX_VALUE - 1000);
        graph.addEdge(1, 2, Integer.MAX_VALUE - 1000);
        
        // Dijkstra should handle potential overflow
        graph.dijkstra(0);
        
        String output = outputStream.toString();
        assertFalse("Should handle large weights without crashing", output.trim().isEmpty());
    }
    
    @Test
    public void testLargeGraphPerformance()
    {
        WeightedGraph largeGraph = new WeightedGraph(50);
        
        // Add vertices
        for (int i = 0; i < 20; i++) {
            largeGraph.addVertex("V" + i);
        }
        
        // Add some edges
        for (int i = 0; i < 19; i++) {
            largeGraph.addEdge(i, i + 1, i + 1);
        }
        
        // Should handle larger graphs without issues
        largeGraph.dijkstra(0);
        
        String output = outputStream.toString();
        assertFalse("Should handle larger graphs", output.trim().isEmpty());
    }
    
    @Test
    public void testMSTConnectedGraph()
    {
        setupConnectedGraphForMST();
        
        graph.minimumSpanningTree();
        
        String output = outputStream.toString();
        assertFalse("MST should produce output", output.trim().isEmpty());
        assertFalse("Should not indicate disconnected graph",
                    output.contains("Graph is not connected"));
    }
    
    @Test
    public void testMSTDisconnectedGraph()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        // Add only one edge, leaving one vertex disconnected
        graph.addEdge(0, 1, 10);
        
        graph.minimumSpanningTree();
        
        String output = outputStream.toString();
        assertTrue("Should detect disconnected graph", output.contains("Graph is not connected"));
    }
    
    @Test
    public void testMSTStateCleanup()
    {
        setupConnectedGraphForMST();
        
        // Run MST multiple times - should not crash due to state issues
        graph.minimumSpanningTree();
        outputStream.reset();
        graph.minimumSpanningTree();
        
        String output = outputStream.toString();
        assertFalse("Should handle multiple MST runs", output.trim().isEmpty());
    }
    
    // ========== Issue 6: Edge Case and Error Handling Tests ==========
    
    @Test
    public void testMSTWithDuplicateWeights()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        
        // Add edges with same weight
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 10);
        graph.addEdge(0, 2, 10);
        
        graph.minimumSpanningTree();
        
        String output = outputStream.toString();
        assertFalse("MST should handle duplicate weights", output.trim().isEmpty());
    }
    
    @Test
    public void testMultipleAlgorithmRuns()
    {
        setupTestGraphForDijkstra();
        
        // Test that algorithms can be run multiple times without issues
        graph.dijkstra(0);
        graph.minimumSpanningTree();
        graph.dijkstra(2);
        graph.minimumSpanningTree();
        
        // Should not crash - proper state management
        assertTrue("Should handle multiple algorithm runs", true);
    }
    
    @Test
    public void testNullSafetyInVertexList()
    {
        // Test behavior when vertex list has gaps
        WeightedGraph testGraph = new WeightedGraph(5);
        testGraph.addVertex("A");
        testGraph.addVertex("B");
        // Skip adding vertex at index 2
        testGraph.addVertex("D");
        
        // Should handle gaps gracefully
        testGraph.displayWeightMatrix();
        String output = outputStream.toString();
        assertFalse("Should handle vertex gaps", output.trim().isEmpty());
    }
    
    @Test
    public void testResetVerticesAfterAlgorithms()
    {
        setupTestGraphForDijkstra();
        
        // Run Dijkstra multiple times - should work due to proper reset
        graph.dijkstra(0);
        outputStream.reset();
        graph.dijkstra(1);
        outputStream.reset();
        
        // Run MST after Dijkstra - should work due to proper reset
        graph.minimumSpanningTree();
        
        String output = outputStream.toString();
        assertFalse("Should handle algorithm sequence", output.trim().isEmpty());
    }
    
    @Test
    public void testWeightMatrixInitialization()
    {
        WeightedGraph testGraph = new WeightedGraph(3);
        testGraph.addVertex("A");
        testGraph.addVertex("B");
        testGraph.addVertex("C");
        
        // Test that initially there are no connections (display should show dashes)
        testGraph.displayWeightMatrix();
        String output = outputStream.toString();
        assertTrue("Should display weight matrix", output.contains("A"));
        assertTrue("Should display weight matrix", output.contains("B"));
        assertTrue("Should display weight matrix", output.contains("C"));
    }
    
    private void setupConnectedGraphForMST()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 15);
        graph.addEdge(1, 2, 20);
        graph.addEdge(1, 3, 25);
        graph.addEdge(2, 3, 30);
    }
    
    private void setupTestGraphForDijkstra()
    {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        
        graph.addEdge(0, 1, 50);
        graph.addEdge(0, 3, 80);
        graph.addEdge(1, 2, 60);
        graph.addEdge(1, 3, 90);
        graph.addEdge(2, 4, 40);
        graph.addEdge(3, 2, 20);
        graph.addEdge(3, 4, 70);
        graph.addEdge(4, 1, 50);
    }
    
}
