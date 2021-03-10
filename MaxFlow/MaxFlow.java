class MaxFlow {
    private static final class InternalCapEdge {
        final int to;
        final int rev;
        long cap;
        InternalCapEdge(int to, int rev, long cap) { this.to = to; this.rev = rev; this.cap = cap; }
    }
    public static final class CapEdge {
        public final int from, to;
        public final long cap, flow;
        CapEdge(int from, int to, long cap, long flow) { this.from = from; this.to = to; this.cap = cap; this.flow = flow; }
        @Override
        public boolean equals(Object o) {
            if (o instanceof CapEdge) {
                CapEdge e = (CapEdge) o;
                return from == e.from && to == e.to && cap == e.cap && flow == e.flow;
            }
            return false;
        }
    }
    private static final class IntPair {
        final int first, second;
        IntPair(int first, int second) { this.first = first; this.second = second; }
    }

    static final long INF = Long.MAX_VALUE;

    private final int n;
    private final java.util.ArrayList<IntPair> pos;
    private final java.util.ArrayList<InternalCapEdge>[] g;

    @SuppressWarnings("unchecked")
    public MaxFlow(int n) {
        this.n = n;
        this.pos = new java.util.ArrayList<>();
        this.g = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) {
            this.g[i] = new java.util.ArrayList<>();
        }
    }

    public int addEdge(int from, int to, long cap) {
        rangeCheck(from, 0, n);
        rangeCheck(to, 0, n);
        nonNegativeCheck(cap, "Capacity");
        int m = pos.size();
        pos.add(new IntPair(from, g[from].size()));
        int fromId = g[from].size();
        int toId = g[to].size();
        if (from == to) toId++;
        g[from].add(new InternalCapEdge(to, toId, cap));
        g[to].add(new InternalCapEdge(from, fromId, 0L));
        return m;
    }

    private InternalCapEdge getInternalEdge(int i) {
        return g[pos.get(i).first].get(pos.get(i).second);
    }

    private InternalCapEdge getInternalEdgeReversed(InternalCapEdge e) {
        return g[e.to].get(e.rev);
    }

    public CapEdge getEdge(int i) {
        int m = pos.size();
        rangeCheck(i, 0, m);
        InternalCapEdge e = getInternalEdge(i);
        InternalCapEdge re = getInternalEdgeReversed(e);
        return new CapEdge(re.to, e.to, e.cap + re.cap, re.cap);
    }

    public CapEdge[] getEdges() {
        CapEdge[] res = new CapEdge[pos.size()];
        java.util.Arrays.setAll(res, this::getEdge);
        return res;
    }

    public void changeEdge(int i, long newCap, long newFlow) {
        int m = pos.size();
        rangeCheck(i, 0, m);
        nonNegativeCheck(newCap, "Capacity");
        if (newFlow > newCap) {
            throw new IllegalArgumentException(
                String.format("Flow %d is greater than the capacity %d.", newCap, newFlow)
            );
        }
        InternalCapEdge e = getInternalEdge(i);
        InternalCapEdge re = getInternalEdgeReversed(e);
        e.cap = newCap - newFlow;
        re.cap = newFlow;
    }

    public long maxFlow(int s, int t) {
        return flow(s, t, INF);
    }

    public long flow(int s, int t, long flowLimit) {
        rangeCheck(s, 0, n);
        rangeCheck(t, 0, n);
        long flow = 0L;
        int[] level = new int[n];
        int[] que = new int[n];
        int[] iter = new int[n];
        while (flow < flowLimit) {
            bfs(s, t, level, que);
            if (level[t] < 0) break;
            java.util.Arrays.fill(iter, 0);
            while (flow < flowLimit) {
                long d = dfs(t, s, flowLimit - flow, iter, level);
                if (d == 0) break;
                flow += d;
            }
        }
        return flow;
    }

    private void bfs(int s, int t, int[] level, int[] que) {
        java.util.Arrays.fill(level, -1);
        int hd = 0, tl = 0;
        que[tl++] = s;
        level[s] = 0;
        while (hd < tl) {
            int u = que[hd++];
            for (InternalCapEdge e : g[u]) {
                int v = e.to;
                if (e.cap == 0 || level[v] >= 0) continue;
                level[v] = level[u] + 1;
                if (v == t) return;
                que[tl++] = v;
            }
        }
    }

    private long dfs(int cur, int s, long flowLimit, int[] iter, int[] level) {
        if (cur == s) return flowLimit;
        long res = 0;
        int curLevel = level[cur];
        for (int itMax = g[cur].size(); iter[cur] < itMax; iter[cur]++) {
            int i = iter[cur];
            InternalCapEdge e = g[cur].get(i);
            InternalCapEdge re = getInternalEdgeReversed(e);
            if (curLevel <= level[e.to] || re.cap == 0) continue;
            long d = dfs(e.to, s, Math.min(flowLimit - res, re.cap), iter, level);
            if (d <= 0) continue;
            e.cap += d;
            re.cap -= d;
            res += d;
            if (res == flowLimit) break;
        }
        return res;
    }

    public boolean[] minCut(int s) {
        rangeCheck(s, 0, n);
        boolean[] visited = new boolean[n];
        int[] stack = new int[n];
        int ptr = 0;
        stack[ptr++] = s;
        visited[s] = true;
        while (ptr > 0) {
            int u = stack[--ptr];
            for (InternalCapEdge e : g[u]) {
                int v = e.to;
                if (e.cap > 0 && !visited[v]) {
                    visited[v] = true;
                    stack[ptr++] = v;
                }
            }
        }
        return visited;
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
