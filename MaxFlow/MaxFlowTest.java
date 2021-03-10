import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * From: https://github.com/atcoder/ac-library/blob/1ca9100261b8c27cf62acccc3618c5e8375bf57b/test/unittest/maxflow_test.cpp
 */
public class MaxFlowTest {
    @Test
    public void simple() {
        MaxFlow g = new MaxFlow(4);
        assertEquals(0, g.addEdge(0, 1, 1));
        assertEquals(1, g.addEdge(0, 2, 1));
        assertEquals(2, g.addEdge(1, 3, 1));
        assertEquals(3, g.addEdge(2, 3, 1));
        assertEquals(4, g.addEdge(1, 2, 1));
        assertEquals(2, g.maxFlow(0, 3));

        MaxFlow.CapEdge e;
        e = new MaxFlow.CapEdge(0, 1, 1, 1);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(0, 2, 1, 1);
        assertEquals(e, g.getEdge(1));
        e = new MaxFlow.CapEdge(1, 3, 1, 1);
        assertEquals(e, g.getEdge(2));
        e = new MaxFlow.CapEdge(2, 3, 1, 1);
        assertEquals(e, g.getEdge(3));
        e = new MaxFlow.CapEdge(1, 2, 1, 0);
        assertEquals(e, g.getEdge(4));

        assertTrue(Arrays.equals(new boolean[]{true, false, false, false}, g.minCut(0)));
    }

    @Test
    public void notSimple() {
        MaxFlow g = new MaxFlow(2);
        assertEquals(0, g.addEdge(0, 1, 1));
        assertEquals(1, g.addEdge(0, 1, 2));
        assertEquals(2, g.addEdge(0, 1, 3));
        assertEquals(3, g.addEdge(0, 1, 4));
        assertEquals(4, g.addEdge(0, 1, 5));
        assertEquals(5, g.addEdge(0, 0, 6));
        assertEquals(6, g.addEdge(1, 1, 7));
        assertEquals(15, g.maxFlow(0, 1));
    
        MaxFlow.CapEdge e;
        e = new MaxFlow.CapEdge(0, 1, 1, 1);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(0, 1, 2, 2);
        assertEquals(e, g.getEdge(1));
        e = new MaxFlow.CapEdge(0, 1, 3, 3);
        assertEquals(e, g.getEdge(2));
        e = new MaxFlow.CapEdge(0, 1, 4, 4);
        assertEquals(e, g.getEdge(3));
        e = new MaxFlow.CapEdge(0, 1, 5, 5);
        assertEquals(e, g.getEdge(4));
    
        assertTrue(Arrays.equals(new boolean[]{true, false}, g.minCut(0)));
    }

    @Test
    public void cut() {
        MaxFlow g = new MaxFlow(3);
        assertEquals(0, g.addEdge(0, 1, 2));
        assertEquals(1, g.addEdge(1, 2, 1));
        assertEquals(1, g.maxFlow(0, 2));

        MaxFlow.CapEdge e;
        e = new MaxFlow.CapEdge(0, 1, 2, 1);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(1, 2, 1, 1);
        assertEquals(e, g.getEdge(1));

        assertTrue(Arrays.equals(new boolean[]{true, true, false}, g.minCut(0)));
    }

    @Test
    public void twise() {
        MaxFlow.CapEdge e;

        MaxFlow g = new MaxFlow(3);
        assertEquals(0, g.addEdge(0, 1, 1));
        assertEquals(1, g.addEdge(0, 2, 1));
        assertEquals(2, g.addEdge(1, 2, 1));
        
        assertEquals(2, g.maxFlow(0, 2));
    
        e = new MaxFlow.CapEdge(0, 1, 1, 1);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(0, 2, 1, 1);
        assertEquals(e, g.getEdge(1));
        e = new MaxFlow.CapEdge(1, 2, 1, 1);
        assertEquals(e, g.getEdge(2));
    
        g.changeEdge(0, 100, 10);
        e = new MaxFlow.CapEdge(0, 1, 100, 10);
        assertEquals(e, g.getEdge(0));
    
        assertEquals(0, g.maxFlow(0, 2));
        assertEquals(90, g.maxFlow(0, 1));
    
        e = new MaxFlow.CapEdge(0, 1, 100, 100);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(0, 2, 1, 1);
        assertEquals(e, g.getEdge(1));
        e = new MaxFlow.CapEdge(1, 2, 1, 1);
        assertEquals(e, g.getEdge(2));
    
        assertEquals(2, g.maxFlow(2, 0));
    
        e = new MaxFlow.CapEdge(0, 1, 100, 99);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(0, 2, 1, 0);
        assertEquals(e, g.getEdge(1));
        e = new MaxFlow.CapEdge(1, 2, 1, 0);
        assertEquals(e, g.getEdge(2));
    }

    @Test
    public void bound() {
        MaxFlow.CapEdge e;

        long INF = MaxFlow.INF;
        MaxFlow g = new MaxFlow(3);
        assertEquals(0, g.addEdge(0, 1, INF));
        assertEquals(1, g.addEdge(1, 0, INF));
        assertEquals(2, g.addEdge(0, 2, INF));

        assertEquals(INF, g.maxFlow(0, 2));

        e = new MaxFlow.CapEdge(0, 1, INF, 0);
        assertEquals(e, g.getEdge(0));
        e = new MaxFlow.CapEdge(1, 0, INF, 0);
        assertEquals(e, g.getEdge(1));
        e = new MaxFlow.CapEdge(0, 2, INF, INF);
        assertEquals(e, g.getEdge(2));
    }

    @Test
    public void selfLoop() {
        MaxFlow g = new MaxFlow(3);
        assertEquals(0, g.addEdge(0, 0, 100));

        MaxFlow.CapEdge e = new MaxFlow.CapEdge(0, 0, 100, 0);
        assertEquals(e, g.getEdge(0));
    }
}