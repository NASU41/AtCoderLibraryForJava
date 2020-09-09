/**
 * Convolution.
 *
 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_f
 */
public class Convolution {
    /**
     * Find a primitive root.
     *
     * @param m A prime number.
     * @return Primitive root.
     */
    private static int primitiveRoot(int m) {
        if (m == 2) return 1;
        if (m == 167772161) return 3;
        if (m == 469762049) return 3;
        if (m == 754974721) return 11;
        if (m == 998244353) return 3;

        int[] divs = new int[20];
        divs[0] = 2;
        int cnt = 1;
        int x = (m - 1) / 2;
        while (x % 2 == 0) x /= 2;
        for (int i = 3; (long) (i) * i <= x; i += 2) {
            if (x % i == 0) {
                divs[cnt++] = i;
                while (x % i == 0) {
                    x /= i;
                }
            }
        }
        if (x > 1) {
            divs[cnt++] = x;
        }
        for (int g = 2; ; g++) {
            boolean ok = true;
            for (int i = 0; i < cnt; i++) {
                if (pow(g, (m - 1) / divs[i], m) == 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) return g;
        }
    }

    /**
     * Power.
     *
     * @param x Parameter x.
     * @param n Parameter n.
     * @param m Mod.
     * @return n-th power of x mod m.
     */
    private static long pow(long x, long n, int m) {
        if (m == 1) return 0;
        long r = 1;
        long y = x % m;
        while (n > 0) {
            if ((n & 1) != 0) r = (r * y) % m;
            y = (y * y) % m;
            n >>= 1;
        }
        return r;
    }

    /**
     * Ceil of power 2.
     *
     * @param n Value.
     * @return Ceil of power 2.
     */
    private static int ceil_pow2(int n) {
        int x = 0;
        while ((1L << x) < n) x++;
        return x;
    }

    /**
     * Pre-calculation for NTT.
     *
     * @param mod Mod.
     * @param g   Primitive root of mod.
     * @return Pre-calculation table.
     */
    private static long[] sumE(int mod, int g) {
        long[] sum_e = new long[30];
        long[] es = new long[30];
        long[] ies = new long[30];
        int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
        long e = pow(g, (mod - 1) >> cnt2, mod);
        long ie = pow(e, mod - 2, mod);
        for (int i = cnt2; i >= 2; i--) {
            es[i - 2] = e;
            ies[i - 2] = ie;
            e = e * e % mod;
            ie = ie * ie % mod;
        }
        long now = 1;
        for (int i = 0; i < cnt2 - 2; i++) {
            sum_e[i] = es[i] * now % mod;
            now = now * ies[i] % mod;
        }
        return sum_e;
    }

    /**
     * Pre-calculation for inverse NTT.
     *
     * @param mod Mod.
     * @param g   Primitive root of mod.
     * @return Pre-calculation table.
     */
    private static long[] sumIE(int mod, int g) {
        long[] sum_ie = new long[30];
        long[] es = new long[30];
        long[] ies = new long[30];

        int cnt2 = Integer.numberOfTrailingZeros(mod - 1);
        long e = pow(g, (mod - 1) >> cnt2, mod);
        long ie = pow(e, mod - 2, mod);
        for (int i = cnt2; i >= 2; i--) {
            es[i - 2] = e;
            ies[i - 2] = ie;
            e = e * e % mod;
            ie = ie * ie % mod;
        }
        long now = 1;
        for (int i = 0; i < cnt2 - 2; i++) {
            sum_ie[i] = ies[i] * now % mod;
            now = now * es[i] % mod;
        }
        return sum_ie;
    }

    /**
     * Inverse NTT.
     *
     * @param a     Target array.
     * @param sumIE Pre-calculation table.
     * @param mod   Mod.
     */
    private static void butterflyInv(long[] a, long[] sumIE, int mod) {
        int n = a.length;
        int h = ceil_pow2(n);

        for (int ph = h; ph >= 1; ph--) {
            int w = 1 << (ph - 1), p = 1 << (h - ph);
            long inow = 1;
            for (int s = 0; s < w; s++) {
                int offset = s << (h - ph + 1);
                for (int i = 0; i < p; i++) {
                    long l = a[i + offset];
                    long r = a[i + offset + p];
                    a[i + offset] = (l + r) % mod;
                    a[i + offset + p] = (mod + l - r) * inow % mod;
                }
                int x = Integer.numberOfTrailingZeros(~s);
                inow = inow * sumIE[x] % mod;
            }
        }
    }

    /**
     * Inverse NTT.
     *
     * @param a    Target array.
     * @param sumE Pre-calculation table.
     * @param mod  Mod.
     */
    private static void butterfly(long[] a, long[] sumE, int mod) {
        int n = a.length;
        int h = ceil_pow2(n);

        for (int ph = 1; ph <= h; ph++) {
            int w = 1 << (ph - 1), p = 1 << (h - ph);
            long now = 1;
            for (int s = 0; s < w; s++) {
                int offset = s << (h - ph + 1);
                for (int i = 0; i < p; i++) {
                    long l = a[i + offset];
                    long r = a[i + offset + p] * now % mod;
                    a[i + offset] = (l + r) % mod;
                    a[i + offset + p] = (l - r + mod) % mod;
                }
                int x = Integer.numberOfTrailingZeros(~s);
                now = now * sumE[x] % mod;
            }
        }
    }

    /**
     * Convolution by ModInt.
     *
     * @param a Target array 1.
     * @param b Target array 2.
     * @return Answer.
     */
    public static java.util.List<ModIntFactory.ModInt> convolution(
            java.util.List<ModIntFactory.ModInt> a,
            java.util.List<ModIntFactory.ModInt> b
    ) {
        int mod = a.get(0).mod();
        long[] va = a.stream().mapToLong(ModIntFactory.ModInt::value).toArray();
        long[] vb = b.stream().mapToLong(ModIntFactory.ModInt::value).toArray();
        long[] c = convolution(va, vb, mod);
        ModIntFactory factory = new ModIntFactory(mod);
        return java.util.Arrays.stream(c).mapToObj(factory::create).collect(java.util.stream.Collectors.toList());
    }

    /**
     * Convolution.
     *
     * @param a   Target array 1.
     * @param b   Target array 2.
     * @param mod Mod(>= 3).
     * @return Answer.
     */
    public static long[] convolution(long[] a, long[] b, int mod) {
        int n = a.length;
        int m = b.length;

        int z = 1 << ceil_pow2(n + m - 1);
        {
            long[] na = new long[z];
            long[] nb = new long[z];
            System.arraycopy(a, 0, na, 0, n);
            System.arraycopy(b, 0, nb, 0, m);
            a = na;
            b = nb;
        }

        int g = primitiveRoot(mod);
        long[] sume = sumE(mod, g);
        long[] sumie = sumIE(mod, g);

        butterfly(a, sume, mod);
        butterfly(b, sume, mod);
        for (int i = 0; i < z; i++) {
            a[i] = a[i] * b[i] % mod;
        }
        butterflyInv(a, sumie, mod);
        a = java.util.Arrays.copyOf(a, n + m - 1);

        long iz = pow(z, mod - 2, mod);
        for (int i = 0; i < n + m - 1; i++) a[i] = a[i] * iz % mod;
        return a;
    }

    /**
     * Naive convolution. (Complexity is O(N^2)!!)
     *
     * @param a   Target array 1.
     * @param b   Target array 2.
     * @param mod Mod.
     * @return Answer.
     */
    public static long[] convolutionNaive(long[] a, long[] b, int mod) {
        int n = a.length;
        int m = b.length;
        int k = n + m - 1;
        long[] ret = new long[k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[i + j] += a[i] * b[j] % mod;
                ret[i + j] %= mod;
            }
        }
        return ret;
    }
}
