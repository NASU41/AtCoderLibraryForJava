/**
 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_g
 */
class SCC {

    static class Edge {
        int from, to;
        public Edge(int from, int to) {
            this.from = from; this.to = to;
        }
    }

    final int n;
    int m;
    final java.util.ArrayList<Edge> unorderedEdges;
    final int[] start;
    final int[] ids;
    boolean hasBuilt = false;

    public SCC(int n) {
        this.n = n;
        this.unorderedEdges = new java.util.ArrayList<>();
        this.start = new int[n + 1];
        this.ids = new int[n];
    }

    public void addEdge(int from, int to) {
        rangeCheck(from);
        rangeCheck(to);
        unorderedEdges.add(new Edge(from, to));
        start[from + 1]++;
        this.m++;
    }

    public int id(int i) {
        if (!hasBuilt) {
            throw new UnsupportedOperationException(
                "Graph hasn't been built."
            );
        }
        rangeCheck(i);
        return ids[i];
    }
    
    public int[][] build() {
        for (int i = 1; i <= n; i++) {
            start[i] += start[i - 1];
        }
        Edge[] orderedEdges = new Edge[m];
        int[] count = new int[n + 1];
        System.arraycopy(start, 0, count, 0, n + 1);
        for (Edge e : unorderedEdges) {
            orderedEdges[count[e.from]++] = e;
        }
        int nowOrd = 0;
        int groupNum = 0;
        int k = 0;
        // parent
        int[] par = new int[n];
        int[] vis = new int[n];
        int[] low = new int[n];
        int[] ord = new int[n];
        java.util.Arrays.fill(ord, -1);
        // u = lower32(stack[i]) : visiting vertex
        // j = upper32(stack[i]) : jth child
        long[] stack = new long[n];
        // size of stack
        int ptr = 0;
        // non-recursional DFS
        for (int i = 0; i < n; i++) {
            if (ord[i] >= 0) continue;
            par[i] = -1;
            // vertex i, 0th child.
            stack[ptr++] = 0l << 32 | i;
            // stack is not empty
            while (ptr > 0) {
                // last element
                long p = stack[--ptr];
                // vertex
                int u = (int) (p & 0xffff_ffffl);
                // jth child
                int j = (int) (p >>> 32);
                if (j == 0) { // first visit
                    low[u] = ord[u] = nowOrd++;
                    vis[k++] = u;
                }
                if (start[u] + j < count[u]) { // there are more children
                    // jth child
                    int to = orderedEdges[start[u] + j].to;
                    // incr children counter
                    stack[ptr++] += 1l << 32;
                    if (ord[to] == -1) { // new vertex
                        stack[ptr++] = 0l << 32 | to;
                        par[to] = u;
                    } else { // backward edge
                        low[u] = Math.min(low[u], ord[to]);
                    }
                } else { // no more children (leaving)
                    while (j --> 0) {
                        int to = orderedEdges[start[u] + j].to;
                        // update lowlink
                        if (par[to] == u) low[u] = Math.min(low[u], low[to]);
                    }
                    if (low[u] == ord[u]) { // root of a component
                        while (true) { // gathering verticies
                            int v = vis[--k];
                            ord[v] = n;
                            ids[v] = groupNum;
                            if (v == u) break;
                        }
                        groupNum++; // incr the number of components
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            ids[i] = groupNum - 1 - ids[i];
        }
        
        int[] counts = new int[groupNum];
        for (int x : ids) counts[x]++;
        int[][] groups = new int[groupNum][];
        for (int i = 0; i < groupNum; i++) {
            groups[i] = new int[counts[i]];
        }
        for (int i = 0; i < n; i++) {
            int cmp = ids[i];
            groups[cmp][--counts[cmp]] = i;
        }
        hasBuilt = true;
        return groups;
    }

    private void rangeCheck(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException(
                String.format("Index %d out of bounds for length %d", i, n)
            );
        }
    }
}