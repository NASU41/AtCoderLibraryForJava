import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Solver practice2_e = new practice2_e();
    static Solver GRL_6_B = new GRL_6_B();

    public static void main(String[] args) {
        // practice2_e.run();
        GRL_6_B.run();
    }
}

abstract class Solver implements Runnable { public abstract void run(); }

/**
 * @problem
 * AtCoder Library Practice Contest E - MinCostFlow
 * {@see https://atcoder.jp/contests/practice2/tasks/practice2_e}
 * @submission
 * {@see https://atcoder.jp/contests/practice2/submissions/20811599}
 */
class practice2_e extends Solver {
    static class Answer {
        final long ans;
        final char[][] g;
        Answer(long ans, char[][] g) {
            this.ans = ans;
            this.g = g;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(ans);
            for (char[] r : g) {
                sb.append('\n').append(r);
            }
            return sb.toString();
        }
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        long[][] a = new long[n][n];
        for (long[] r : a) Arrays.setAll(r, j -> Long.parseLong(sc.next()));
        sc.close();
        System.out.println(solve(n, k, a));
    }

    static final long INF = 1l << 50;

    public Answer solve(int n, int k, long[][] a) {
        MinCostFlow mcf = new MinCostFlow(n + n + 2);
        int s = n + n , t = s + 1;
        mcf.addEdge(s, t, n * k, INF);
        
        for(int i = 0; i < n; i++) {
            mcf.addEdge(s, i, k, 0);
            mcf.addEdge(i + n, t, k, 0);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mcf.addEdge(i, j + n, 1, INF - a[i][j]);
            }
        }
        
        MinCostFlow.FlowAndCost res = mcf.minCostFlow(s, t, n * k);
        long ans = INF * res.flow - res.cost;
        MinCostFlow.WeightedCapEdge[] es = mcf.getEdges();
        char[][] g = new char[n][n];
        for (char[] r : g) Arrays.fill(r, '.');
        for (MinCostFlow.WeightedCapEdge e : es) {
            if (e.flow == 1 && e.from != s && e.to != t) {
                g[e.from][e.to - n] = 'X';
            }
        }
        return new Answer(ans, g);
    }
}

/**
 * @problem
 * GRL_6 B - Minimum Cost Flow
 * {@see https://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=GRL_6_B}
 * @submission
 * {@see https://judge.u-aizu.ac.jp/onlinejudge/review.jsp?rid=5281698#1}
 */
class GRL_6_B extends Solver {
    public int solve(int n, int m, int f, int[][] edges) {
        MinCostFlow mcf = new MinCostFlow(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int c = edge[2];
            int d = edge[3];
            mcf.addEdge(u, v, c, d);
        }
        MinCostFlow.FlowAndCost fc = mcf.minCostFlow(0, n - 1, f);
        return (int) (fc.flow == f ? fc.cost : -1);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int v = Integer.parseInt(sc.next());
        int e = Integer.parseInt(sc.next());
        int f = Integer.parseInt(sc.next());
        int[][] edges = new int[e][4];
        for (int[] edge : edges) {
            Arrays.setAll(edge, i -> Integer.parseInt(sc.next()));
        }
        sc.close();
        System.out.println(solve(v, e, f, edges));
    }
}
