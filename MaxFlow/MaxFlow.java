/**
 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_d
 */
class MaxFlow {
    public class CapEdge {
        private final int from, to;
        private long cap;
        private final int rev;
        CapEdge(int from, int to, long cap, int rev) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
        public int getFrom()  {return from;}
        public int getTo()    {return to;}
        public long getCap()  {return cap;}
        public long getFlow() {return g[to][rev].cap;}
    }

    private static final long INF = Long.MAX_VALUE;

    private final int n;
    private int m;
    private final java.util.ArrayList<CapEdge> edges;
    private final int[] count;
    private final CapEdge[][] g;

    public MaxFlow(int n) {
        this.n = n;
        this.edges = new java.util.ArrayList<>();
        this.count = new int[n];
        this.g = new CapEdge[n][];
    }

    public int addEdge(int from, int to, long cap) {
        rangeCheck(from, 0, n);
        rangeCheck(to, 0, n);
        nonNegativeCheck(cap, "Capacity");
        CapEdge e = new CapEdge(from, to, cap, count[to]);
        count[from]++; count[to]++;
        edges.add(e);
        return m++;
    }

    public CapEdge getEdge(int i) {
        rangeCheck(i, 0, m);
        return edges.get(i);
    }

    public java.util.ArrayList<CapEdge> getEdges() {
        return edges;
    }

    public void changeEdge(int i, long newCap, long newFlow) {
        rangeCheck(i, 0, m);
        nonNegativeCheck(newCap, "Capacity");
        if (newFlow > newCap) {
            throw new IllegalArgumentException(
                String.format("Flow %d is greater than capacity %d.", newCap, newFlow)
            );
        }
        CapEdge e = edges.get(i);
        CapEdge er = g[e.to][e.rev];
        e.cap = newCap - newFlow;
        er.cap = newFlow;
    }

    private void buildGraph() {
        for (int i = 0; i < n; i++) {
            g[i] = new CapEdge[count[i]];
        }
        int[] idx = new int[n];
        for (CapEdge e : edges) {
            g[e.to][idx[e.to]++] = new CapEdge(e.to, e.from, 0, idx[e.from]);
            g[e.from][idx[e.from]++] = e;
        }
    }

    public long maxFlow(int s, int t) {
        return flow(s, t, INF);
    }

    public long flow(int s, int t, long flowLimit) {
        rangeCheck(s, 0, n);
        rangeCheck(t, 0, n);
        buildGraph();
        long flow = 0;
        int[] level = new int[n];
        int[] que = new int[n];
        int[] iter = new int[n];
        while (true) {
            java.util.Arrays.fill(level, -1);
            dinicBFS(s, t, level, que);
            if (level[t] < 0) return flow;
            java.util.Arrays.fill(iter, 0);
            while (true) {
                long d = dinicDFS(t, s, flowLimit - flow, iter, level);
                if (d <= 0) break;
                flow += d;
                if (flow == flowLimit) return flow;
            }
        }
    }

    private void dinicBFS(int s, int t, int[] level, int[] que) {
        int hd = 0, tl = 0;
        que[tl++] = s;
        level[s] = 0;
        while (tl > hd) {
            int u = que[hd++];
            for (CapEdge e : g[u]) {
                int v = e.to;
                if (e.cap <= 0 || level[v] >= 0) continue;
                level[v] = level[u] + 1;
                if (v == t) return;
                que[tl++] = v;
            }
        }
    }

    private long dinicDFS(int cur, int s, long f, int[] iter, int[] level) {
        if (cur == s) return f;
        long res = 0;
        while (iter[cur] < count[cur]) {
            CapEdge er = g[cur][iter[cur]++];
            int u = er.to;
            CapEdge e = g[u][er.rev];
            if (level[u] >= level[cur] || e.cap <= 0) continue;
            long d = dinicDFS(u, s, Math.min(f - res, e.cap), iter, level);
            if (d <= 0) continue;
            e.cap -= d;
            er.cap += d;
            res += d;
            if (res == f) break;
        }
        return res;
    }

    public long fordFulkersonMaxFlow(int s, int t) {
        return fordFulkersonFlow(s, t, INF);
    }

    public long fordFulkersonFlow(int s, int t, long flowLimit) {
        rangeCheck(s, 0, n);
        rangeCheck(t, 0, n);
        buildGraph();
        boolean[] used = new boolean[n];
        long flow = 0;
        while (true) {
            java.util.Arrays.fill(used, false);
            long f = fordFulkersonDFS(s, t, flowLimit - flow, used);
            if (f <= 0) return flow;
            flow += f;
        }
    }

    private long fordFulkersonDFS(int cur, int t, long f, boolean[] used) {
        if (cur == t) return f;
        used[cur] = true;
        for (CapEdge e : g[cur]) {
            if (used[e.to] || e.cap <= 0) continue;
            long d = fordFulkersonDFS(e.to, t, Math.min(f, e.cap), used);
            if (d <= 0) continue;
            e.cap -= d;
            g[e.to][e.rev].cap += d;
            return d;
        }
        return 0;
    }

    public boolean[] minCut(int s) {
        rangeCheck(s, 0, n);
        boolean[] reachable = new boolean[n];
        int[] stack = new int[n];
        int ptr = 0;
        stack[ptr++] = s;
        reachable[s] = true;
        while (ptr > 0) {
            int u = stack[--ptr];
            for (CapEdge e : g[u]) {
                int v = e.to;
                if (reachable[v] || e.cap <= 0) continue;
                reachable[v] = true;
                stack[ptr++] = v;
            }
        }
        return reachable;
    }

    private void rangeCheck(int i, int minInclusive, int maxExclusive) {
        if (i < 0 || i >= maxExclusive) {
            throw new IndexOutOfBoundsException(
                String.format("Index %d out of bounds for length %d", i, maxExclusive)
            );
        }
    }

    private void nonNegativeCheck(long cap, String attribute) {
        if (cap < 0) {
            throw new IllegalArgumentException(
                String.format("%s %d is negative.", attribute, cap)
            );
        }
    }
}
