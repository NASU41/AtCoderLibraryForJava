/**
 * @verified https://atcoder.jp/contests/practice2/submissions/16647102
 */
class TwoSAT {
    private final int n;
    private final InternalSCC scc;
    private final boolean[] answer;

    private boolean hasCalledSatisfiable = false;
    private boolean existsAnswer = false;

    public TwoSAT(int n) {
        this.n = n;
        this.scc = new InternalSCC(2 * n);
        this.answer = new boolean[n];
    }

    public void addClause(int x, boolean f, int y, boolean g) {
        rangeCheck(x);
        rangeCheck(y);
        scc.addEdge(x << 1 | (f ? 0 : 1), y << 1 | (g ? 1 : 0));
        scc.addEdge(y << 1 | (g ? 0 : 1), x << 1 | (f ? 1 : 0));
    }

    public void addImplication(int x, boolean f, int y, boolean g) {
        addClause(x, !f, y, g);
    }

    public void addNand(int x, boolean f, int y, boolean g) {
        addClause(x, !f, y, !g);
    }
    public void set(int x, boolean f){
        addClause(x, f, x, f);
    }


    public boolean satisfiable() {
        hasCalledSatisfiable = true;
        int[] ids = scc.ids();
        for (int i = 0; i < n; i++) {
            if (ids[i << 1 | 0] == ids[i << 1 | 1]) return existsAnswer = false;
            answer[i] = ids[i << 1 | 0] < ids[i << 1 | 1];
        }
        return existsAnswer = true;
    }

    public boolean[] answer() {
        if (!hasCalledSatisfiable) {
            throw new UnsupportedOperationException(
                "Call TwoSAT#satisfiable at least once before TwoSAT#answer."
            );
        }
        if (existsAnswer) return answer;
        return null;
    }

    private void rangeCheck(int x) {
        if (x < 0 || x >= n) {
            throw new IndexOutOfBoundsException(
                String.format("Index %d out of bounds for length %d", x, n)
            );
        }
    }

    private static final class EdgeList {
        long[] a;
        int ptr = 0;
        EdgeList(int cap) {a = new long[cap];}
        void add(int upper, int lower) {
            if (ptr == a.length) grow();
            a[ptr++] = (long) upper << 32 | lower;
        }
        void grow() {
            long[] b = new long[a.length << 1];
            System.arraycopy(a, 0, b, 0, a.length);
            a = b;
        }
    }

    private static final class InternalSCC {
        final int n;
        int m;
        final EdgeList unorderedEdges;
        final int[] start;
        InternalSCC(int n) {
            this.n = n;
            this.unorderedEdges = new EdgeList(n);
            this.start = new int[n + 1];
        }
        void addEdge(int from, int to) {
            unorderedEdges.add(from, to);
            start[from + 1]++;
            this.m++;
        }
        static final long mask = 0xffff_ffffl;
        int[] ids() {
            for (int i = 1; i <= n; i++) {
                start[i] += start[i - 1];
            }
            int[] orderedEdges = new int[m];
            int[] count = new int[n + 1];
            System.arraycopy(start, 0, count, 0, n + 1);
            for (int i = 0; i < m; i++) {
                long e = unorderedEdges.a[i];
                orderedEdges[count[(int) (e >>> 32)]++] = (int) (e & mask);
            }
            int nowOrd = 0;
            int groupNum = 0;
            int k = 0;
            int[] par = new int[n];
            int[] vis = new int[n];
            int[] low = new int[n];
            int[] ord = new int[n];
            java.util.Arrays.fill(ord, -1);
            int[] ids = new int[n];
            long[] stack = new long[n];
            int ptr = 0;
            
            for (int i = 0; i < n; i++) {
                if (ord[i] >= 0) continue;
                par[i] = -1;
                stack[ptr++] = i;
                while (ptr > 0) {
                    long p = stack[--ptr];
                    int u = (int) (p & mask);
                    int j = (int) (p >>> 32);
                    if (j == 0) {
                        low[u] = ord[u] = nowOrd++;
                        vis[k++] = u;
                    }
                    if (start[u] + j < count[u]) {
                        int to = orderedEdges[start[u] + j];
                        stack[ptr++] += 1l << 32;
                        if (ord[to] == -1) {
                            stack[ptr++] = to;
                            par[to] = u;
                        } else {
                            low[u] = Math.min(low[u], ord[to]);
                        }
                    } else {
                        while (j --> 0) {
                            int to = orderedEdges[start[u] + j];
                            if (par[to] == u) low[u] = Math.min(low[u], low[to]);
                        }
                        if (low[u] == ord[u]) {
                            while (true) {
                                int v = vis[--k];
                                ord[v] = n;
                                ids[v] = groupNum;
                                if (v == u) break;
                            }
                            groupNum++;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                ids[i] = groupNum - 1 - ids[i];
            }
            return ids;
        }
    }
}
