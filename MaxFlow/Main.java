import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Solver practice2_d = new practice2_d();
    static Solver arc074_d = new arc074_d();
    static Solver abc193_f = new abc193_f();

    public static void main(String[] args) {
        // practice2_d.run();
        // arc074_d.run();
        abc193_f.run();
    }
}

abstract class Solver implements Runnable { public abstract void run(); }

/**
 * @problem
 * AtCoder Library Practice Contest D - Maxflow
 * {@see https://atcoder.jp/contests/practice2/tasks/practice2_d}
 * @submission
 * {@see https://atcoder.jp/contests/practice2/submissions/20808482}
 */
class practice2_d extends Solver {
    public Answer solve(int n, int m, char[][] g) {
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

    public void run() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        char[][] g = new char[n][];
        Arrays.setAll(g, i -> sc.next().toCharArray());
        sc.close();
        System.out.println(solve(n, m, g));
    }
}

/**
 * @problem
 * AtCoder Regular Contest 074 F - Lotus Leaves
 * {@see https://atcoder.jp/contests/arc074/tasks/arc074_d}
 * @submission
 * {@see https://atcoder.jp/contests/arc074/submissions/20808863}
 */
class arc074_d extends Solver {
    static final int INF = 1 << 20;
    public int solve(int h, int w, char[][] g) {
        int si = -1, sj = -1, ti = -1, tj = -1;
        boolean[][] grid = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (g[i][j] == 'S') {
                    si = i; sj = j;
                    grid[i][j] = true;
                } else if (g[i][j] == 'T') {
                    ti = i; tj = j;
                    grid[i][j] = true;
                } else if (g[i][j] == 'o') {
                    grid[i][j] = true;
                }
            }
        }
        int s = h + w;
        int t = s + 1;
        MaxFlow mf = new MaxFlow(h + w + 2);
        mf.addEdge(s     , si    , INF);
        mf.addEdge(s     , sj + h, INF);
        mf.addEdge(ti    , t     , INF);
        mf.addEdge(tj + h, t     , INF);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j]) {
                    mf.addEdge(i, j + h, 1);
                    mf.addEdge(j + h, i, 1);
                }
            }
        }
        int flow = (int) mf.maxFlow(s, t);
        return flow >= INF ? -1 : flow;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int h = Integer.parseInt(sc.next());
        int w = Integer.parseInt(sc.next());
        char[][] g = new char[h][];
        Arrays.setAll(g, i -> sc.next().toCharArray());
        sc.close();
        System.out.println(solve(h, w, g));
    }
}

/**
 * @problem
 * AtCoder Beginner Contest 193 F - Zebraness
 * {@see https://atcoder.jp/contests/abc193/tasks/abc193_f}
 * @submission
 * {@see https://atcoder.jp/contests/abc193/submissions/20808965}
 */
class abc193_f extends Solver {
    static final int[] dx4 = {1, 0, -1, 0};
    static final int[] dy4 = {0, 1, 0, -1};

    public int solve(int n, char[][] c) {
        int[][] id = new int[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == '?') {
                    id[i][j] = cnt++;
                }
            }
        }
 
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < n - 1) {
                    if (c[i][j] == 'B' && c[i + 1][j] == 'W' || c[i][j] == 'W' && c[i + 1][j] == 'B') {
                        ans++;
                    }
                }
                if (j < n - 1) {
                    if (c[i][j] == 'B' && c[i][j + 1] == 'W' || c[i][j] == 'W' && c[i][j + 1] == 'B') {
                        ans++;
                    }
                }
            }
        }
        MaxFlow mf = new MaxFlow(cnt + 2);
        int s = cnt, t = cnt + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == '?') {
                    int b = 0, w = 0;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx4[d];
                        int nj = j + dy4[d];
                        if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;
                        if (c[ni][nj] == 'B') {
                            b++;
                        } else if (c[ni][nj] == 'W') {
                            w++;
                        } else if (d < 2) {
                            mf.addEdge(id[i][j], id[ni][nj], 1);
                            mf.addEdge(id[ni][nj], id[i][j], 1);
                            ans++;
                        }
                    }
                    mf.addEdge(id[i][j], t, (i + j) % 2 == 1 ? b : w);
                    mf.addEdge(s, id[i][j], (i + j) % 2 == 1 ? w : b);
                    ans += b + w;
                }
            }
        }
        return (int) (ans - mf.maxFlow(s, t));
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        char[][] c = new char[n][];
        Arrays.setAll(c, i -> sc.next().toCharArray());
        sc.close();
        System.out.println(solve(n, c));
    }
}
