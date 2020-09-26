package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    static final int TESTCASE_NUM = 100;

    static final Random rnd = new Random(System.nanoTime());

    static final int N_MIN = 1;
    static final int N_MAX = 5000;
    static final int Q_MIN = 1;
    static final int Q_MAX = 10000;
    static final int A_MIN = -1000000000;
    static final int A_MAX = 1000000000;
    static final int V_MIN = -1000000000;
    static final int V_MAX = 1000000000;
    static final int QUERY_MIN = 0;
    static final int QUERY_MAX = 7;

    public static void main(String[] args) {
        for (int i = 0; i < TESTCASE_NUM; i++) {
            String fileName = String.format("LazySegTree/test/in/testcase_%d", i);
            try (PrintWriter out = new PrintWriter(new File(fileName))) {
                gen(out);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void gen(PrintWriter out) {
        final int N = rndIntClosedRange(N_MIN, N_MAX);
        final int Q = rndIntClosedRange(Q_MIN, Q_MAX);
        final int P_MIN = 0, P_MAX = N - 1;
        final int L_MIN = 0, L_MAX = N;
        final int R_MIN = 0, R_MAX = N;
        out.print(N); out.print(' '); out.print(Q); out.print('\n');
        final int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = rndIntClosedRange(A_MIN, A_MAX);
            out.print(A[i]);
            if (i < N - 1) {
                out.print(' ');
            } else {
                out.print('\n');
            }
        }
        for (int i = 0; i < Q; i++) {
            int type = rndIntClosedRange(QUERY_MIN, QUERY_MAX);
            out.print(type);
            if (type == 0) { // set
                int p = rndIntClosedRange(P_MIN, P_MAX);
                int v = rndIntClosedRange(V_MIN, V_MAX);
                out.print(' ');out.print(p);out.print(' ');out.print(v);out.print('\n');
            } else if (type == 1) { // apply
                int p = rndIntClosedRange(P_MIN, P_MAX);
                int v = rndIntClosedRange(V_MIN, V_MAX);
                out.print(' ');out.print(p);out.print(' ');out.print(v);out.print('\n');
            } else if (type == 2) { // apply
                int l = rndIntClosedRange(L_MIN, L_MAX);
                int r = rndIntClosedRange(R_MIN, R_MAX);
                int v = rndIntClosedRange(V_MIN, V_MAX);
                if (l > r) {
                    int tmp = l; l = r; r = tmp;
                }
                out.print(' ');out.print(l);out.print(' ');out.print(r);out.print(' ');out.print(v);out.print('\n');
            } else if (type == 3) { // get
                int p = rndIntClosedRange(P_MIN, P_MAX);
                out.print(' '); out.print(p);out.print('\n');
            } else if (type == 4) { // prod
                int l = rndIntClosedRange(L_MIN, L_MAX);
                int r = rndIntClosedRange(R_MIN, R_MAX);
                if (l > r) {
                    int tmp = l; l = r; r = tmp;
                }
                out.print(' ');out.print(l);out.print(' ');out.print(r);out.print('\n');
            } else if (type == 5) { // prodAll
                out.print('\n');
            } else if (type == 6) { // maxRight
                int l = rndIntClosedRange(L_MIN, L_MAX);
                int v = rndIntClosedRange(V_MIN, V_MAX);
                out.print(' ');out.print(l);out.print(' ');out.print(v);out.print('\n');
            } else if (type == 7) { // minLeft
                int r = rndIntClosedRange(R_MIN, R_MAX);
                int v = rndIntClosedRange(V_MIN, V_MAX);
                out.print(' ');out.print(r);out.print(' ');out.print(v);out.print('\n');
            } else {
                throw new AssertionError();
            }
        }
    }

    public static int rndIntClosedRange(int l, int r) {
        return rnd.nextInt(r - l + 1) + l;
    }
}
