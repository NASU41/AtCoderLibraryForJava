package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    static final int TEST_CASE_NUM = Gen.TESTCASE_NUM;

    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASE_NUM; i++) {
            String answerFileName = String.format("LazySegTree/test/answer/answer_%d", i);
            String outputFileName = String.format("LazySegTree/test/out/out_%d", i);
            try (Scanner scAns = new Scanner(new File(answerFileName))) {
                try (Scanner scSlv = new Scanner(new File(outputFileName))) {
                    test(scAns, scSlv);
                } catch (AssertionError e) {
                    System.err.printf("Error in test %d", i);
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void test(Scanner scAns, Scanner scSlv) {
        int line = 1;
        while (scAns.hasNext() && scSlv.hasNext()) {
            String ans = scAns.next();
            String slv = scSlv.next();
            if (!ans.equals(slv)) {
                throw new AssertionError(
                    String.format("Filed at line %d.\n\texpected: %s\n\tactual: %s", line, ans, slv)
                );
            }
            line++;
        }
    }
}
