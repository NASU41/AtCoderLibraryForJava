import java.util.Arrays;
import java.util.Scanner;

/**
 * @problem
 * AtCoder Library Practice Contest D - Maxflow
 * {@link https://atcoder.jp/contests/practice2/tasks/practice2_d}
 * @submission
 * {@link https://atcoder.jp/contests/practice2/submissions/20808482}
 */
public class Main {
    static class Answer {
        final int c;
        final char[][] g;
        Answer(int c, char[][] g) { this.c = c; this.g = g; }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(c);
            for (char[] row : g) {
                sb.append('\n').append(row);
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        char[][] g = new char[n][];
        Arrays.setAll(g, i -> sc.next().toCharArray());
        sc.close();
        System.out.println(solve(n, m, g));
    }

    public static Answer solve(int n, int m, char[][] g) {
        MaxFlow mf = new MaxFlow(n * m + 2);
        int s = n * m;
        int t = s + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == '#') continue;
                if (((i ^ j) & 1) == 0) {
                    mf.addEdge(s, i * m + j, 1);
                } else {
                    mf.addEdge(i * m + j, t, 1);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i & 1; j < m; j += 2) {
                if (g[i][j] == '#') continue;
                if (j - 1 >= 0 && g[i][j - 1] == '.') {
                    mf.addEdge(i * m + j, i * m + (j - 1), 1);
                }
                if (j + 1 < m && g[i][j + 1] == '.') {
                    mf.addEdge(i * m + j, i * m + (j + 1), 1);
                }
                if (i - 1 >= 0 && g[i - 1][j] == '.') {
                    mf.addEdge(i * m + j, (i - 1) * m + j, 1);
                }
                if (i + 1 < n && g[i + 1][j] == '.') {
                    mf.addEdge(i * m + j, (i + 1) * m + j, 1);
                }
            }
        }
        int ans = (int) mf.maxFlow(s, t);
        int cnt = 0;
        for (var e : mf.getEdges()) {
            if (e.flow == 0) continue;
            int u = e.from;
            int v = e.to;
            if (u == s || v == t) continue;
            int ui = u / m, uj = u % m;
            int vi = v / m, vj = v % m;
            if (g[ui][uj] != '.' || g[vi][vj] != '.') {
                throw new AssertionError();
            }
            if (ui == vi) {
                if (uj + 1 == vj) {
                    g[ui][uj] = '>';
                    g[vi][vj] = '<';
                } else if (uj == vj + 1) {
                    g[ui][uj] = '<';
                    g[vi][vj] = '>';
                } else {
                    throw new AssertionError();
                }
            } else if (uj == vj) {
                if (ui + 1 == vi) {
                    g[ui][uj] = 'v';
                    g[vi][vj] = '^';
                } else if (ui == vi + 1) {
                    g[ui][uj] = '^';
                    g[vi][vj] = 'v';
                } else {
                    throw new AssertionError();
                }
            } else {
                throw new AssertionError();
            }
            cnt++;
        }
        if (ans != cnt) throw new AssertionError();
        return new Answer(ans, g);
    }
}

