import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Convolution example and test.
 */
public class ConvolutionTest {
    public static void main(String[] args) {
        Random gen = new Random();
        int[] primes = {
                1053818881,
                1051721729,
                1045430273,
                1012924417,
                1007681537,
                1004535809,
                998244353,
                985661441,
                976224257,
                975175681,
                1000000000 + 7
        };

        for (int i = 0; i < 1000; i++) {
            int mod = primes[gen.nextInt(primes.length)];
            int n = gen.nextInt(500) + 1;
            int m = gen.nextInt(500) + 1;
            long[] a = new long[n];
            long[] b = new long[m];

            for (int j = 0; j < n; j++) {
                a[j] = gen.nextInt(1000000000);
            }
            for (int j = 0; j < m; j++) {
                b[j] = gen.nextInt(1000000000);
            }
            ModIntFactory factory = new ModIntFactory(mod);
            List<ModIntFactory.ModInt> va = Arrays.stream(a).mapToObj(factory::create).collect(Collectors.toList());
            List<ModIntFactory.ModInt> vb = Arrays.stream(b).mapToObj(factory::create).collect(Collectors.toList());
            long[] expected = Convolution.convolutionNaive(a, b, mod);
            long[] actual = Convolution.convolution(va, vb).stream().mapToLong(ModIntFactory.ModInt::value).toArray();
            assertArrayEquals(expected, actual);
        }
    }

    private static void assertArrayEquals(long[] expected, long[] actual) {
        if (expected.length != actual.length) throw new RuntimeException();
        int n = expected.length;
        for (int i = 0; i < n; i++) {
            if (expected[i] != actual[i]) {
                throw new RuntimeException();
            }
        }
    }
}
