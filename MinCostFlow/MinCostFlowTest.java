import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

public class MinCostFlowTest {
    @Test
    public void simple() {
        MinCostFlow g = new MinCostFlow(4);
        g.addEdge(0, 1, 1, 1);
        g.addEdge(0, 2, 1, 1);
        g.addEdge(1, 3, 1, 1);
        g.addEdge(2, 3, 1, 1);
        g.addEdge(1, 2, 1, 1);
        LinkedList<MinCostFlow.FlowAndCost> expect = new LinkedList<>();
        expect.add(new MinCostFlow.FlowAndCost(0, 0));
        expect.add(new MinCostFlow.FlowAndCost(2, 4));
        assertEquals(expect, g.minCostSlope(0, 3, 10));
        MinCostFlow.WeightedCapEdge e;
    
        e = new MinCostFlow.WeightedCapEdge(0, 1, 1, 1, 1);
        assertEquals(e, g.getEdge(0));
        e = new MinCostFlow.WeightedCapEdge(0, 2, 1, 1, 1);
        assertEquals(e, g.getEdge(1));
        e = new MinCostFlow.WeightedCapEdge(1, 3, 1, 1, 1);
        assertEquals(e, g.getEdge(2));
        e = new MinCostFlow.WeightedCapEdge(2, 3, 1, 1, 1);
        assertEquals(e, g.getEdge(3));
        e = new MinCostFlow.WeightedCapEdge(1, 2, 1, 0, 1);
        assertEquals(e, g.getEdge(4));
    }
    
    @Test
    public void usage() {
        {
            MinCostFlow g = new MinCostFlow(2);
            g.addEdge(0, 1, 1, 2);
            assertEquals(new MinCostFlow.FlowAndCost(1, 2), g.minCostMaxFlow(0, 1));
        }
        {
            MinCostFlow g = new MinCostFlow(2);
            g.addEdge(0, 1, 1, 2);
            LinkedList<MinCostFlow.FlowAndCost> expect = new LinkedList<>();
            expect.add(new MinCostFlow.FlowAndCost(0, 0));
            expect.add(new MinCostFlow.FlowAndCost(1, 2));
            assertEquals(expect, g.minCostSlope(0, 1));
        }
    }

    @Test
    public void outOfRange() {
        MinCostFlow g = new MinCostFlow(10);
        try {
            g.minCostSlope(-1, 3);
            throw new AssertionError();
        } catch (Exception e) {}
        try {
            g.minCostSlope(3, 3);
            throw new AssertionError();
        } catch (Exception e) {}
    }

    @Test
    public void selfLoop() {
        MinCostFlow g = new MinCostFlow(3);
        assertEquals(0, g.addEdge(0, 0, 100, 123));
    
        MinCostFlow.WeightedCapEdge e = new MinCostFlow.WeightedCapEdge(0, 0, 100, 0, 123);
        assertEquals(e, g.getEdge(0));
    }
}
