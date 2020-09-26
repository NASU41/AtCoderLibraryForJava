package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class NaiveSolution {
    static final int TEST_CASE_NUM = Gen.TESTCASE_NUM;
    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASE_NUM; i++) {
            String inputFileName = String.format("LazySegTree/test/in/testcase_%d", i);
            String outputFileName = String.format("LazySegTree/test/answer/answer_%d", i);
            try (Scanner sc = new Scanner(new File(inputFileName))) {
                try (PrintWriter pw = new PrintWriter(new File(outputFileName))) {
                    solve(sc, pw);
                    sc.close();
                    pw.flush();
                    pw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    static final long INF = 1l << 60;

    public static void solve(Scanner sc, PrintWriter pw) {
        final int N = Integer.parseInt(sc.next());
        final int Q = Integer.parseInt(sc.next());
        final long[] A = new long[N];
        Arrays.setAll(A, i -> Integer.parseInt(sc.next()));
        for (int i = 0; i < Q; i++) {
            int queryType = Integer.parseInt(sc.next());
            if (queryType == 0) {
                int p = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());
                A[p] = v;
            } else if (queryType == 1) {
                int p = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());
                A[p] += v;
            } else if (queryType == 2) {
                int l = Integer.parseInt(sc.next());
                int r = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());
                for (int j = l; j < r; j++) {
                    A[j] += v;
                }
            } else if (queryType == 3) {
                int p = Integer.parseInt(sc.next());
                pw.println(A[p]);
            } else if (queryType == 4) {
                int l = Integer.parseInt(sc.next());
                int r = Integer.parseInt(sc.next());
                long min = INF;
                for (int j = l; j < r; j++) {
                    min = Math.min(min, A[j]);
                }
                pw.println(min == INF ? "INF" : min);
            } else if (queryType == 5) {
                long min = INF;
                for (int j = 0; j < N; j++) {
                    min = Math.min(min, A[j]);
                }
                pw.println(min);
            } else if (queryType == 6) {
                int l = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());
                for (int j = l; j <= N; j++) {
                    if (j == N || A[j] <= v) {
                        pw.println(j);
                        break;
                    }
                }
            } else if (queryType == 7) {
                int r = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());
                for (int j = r; j >= 0; j--) {
                    if (j == 0 || A[j - 1] <= v) {
                        pw.println(j);
                        break;
                    }
                }
            } else {
                throw new AssertionError();
            }
        }
    }
}
