import java.util.*;

import java.util.ArrayList;

/**
 * @verified
 * <ul>
 * <li> https://atcoder.jp/contests/m-solutions2019/tasks/m_solutions2019_c : (M = 1000000007)
 * <li> https://atcoder.jp/contests/abc172/tasks/abc172_e : (M = 1000000007)
 * <li> https://atcoder.jp/contests/abc129/tasks/abc129_f : (2 <= M <= 1000000000)
 * <li> https://atcoder.jp/contests/arc050/tasks/arc050_c : (2 <= M <= 1000000000)
 * <li> https://atcoder.jp/contests/arc012/tasks/arc012_4 : (1 <= M <= 1000000007)
 * <li> https://atcoder.jp/contests/abc042/tasks/arc058_b : (M = 1000000007, combination ver.)
 * </ul>
 */
class ModIntFactory {
    private final ModArithmetic ma;
    private final int mod;

    private final boolean usesMontgomery;
    private final ModArithmetic.ModArithmeticMontgomery maMontgomery;

    private ArrayList<Integer> factorial;

    public ModIntFactory(int mod) {
        this.ma = ModArithmetic.of(mod);
        this.usesMontgomery = ma instanceof ModArithmetic.ModArithmeticMontgomery;
        this.maMontgomery = usesMontgomery ? (ModArithmetic.ModArithmeticMontgomery) ma : null;
        this.mod = mod;

        this.factorial = new ArrayList<>();
    }

    public ModInt create(long value) {
        if ((value %= mod) < 0) value += mod;
        if (usesMontgomery) {
            return new ModInt(maMontgomery.generate(value));
        } else {
            return new ModInt((int) value);
        }
    }

    private void prepareFactorial(int max){
        factorial.ensureCapacity(max+1);
        if(factorial.size()==0) factorial.add(1);
        if (usesMontgomery) {
            for(int i=factorial.size(); i<=max; i++){
                factorial.add(ma.mul(factorial.get(i-1), maMontgomery.generate(i)));
            }
        } else {
            for(int i=factorial.size(); i<=max; i++){
                factorial.add(ma.mul(factorial.get(i-1), i));
            }
        }
    }

    public ModInt factorial(int i){
        prepareFactorial(i);
        return create(factorial.get(i));
    }

    public ModInt permutation(int n, int r){
        if(n < 0 || r < 0 || n < r) return create(0);
        prepareFactorial(n);
        return create(ma.div(factorial.get(n), factorial.get(r)));
    }
    public ModInt combination(int n, int r){
        if(n < 0 || r < 0 || n < r) return create(0);
        prepareFactorial(n);
        return create(ma.div(factorial.get(n), ma.mul(factorial.get(r),factorial.get(n-r))));
    }

    public int getMod() {
        return mod;
    }

    public class ModInt {
        private int value;
        private ModInt(int value) {
            this.value = value;
        }
        public int mod() {
            return mod;
        }
        public int value() {
            if (usesMontgomery) {
                return maMontgomery.reduce(value);
            }
            return value;
        }
        public ModInt add(ModInt mi) {
            return new ModInt(ma.add(value, mi.value));
        }
        public ModInt add(ModInt mi1, ModInt mi2) {
            return new ModInt(ma.add(value, mi1.value)).addAsg(mi2);
        }
        public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3) {
            return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3);
        }
        public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3).addAsg(mi4);
        }
        public ModInt add(ModInt mi1, ModInt... mis) {
            ModInt mi = add(mi1);
            for (ModInt m : mis) mi.addAsg(m);
            return mi;
        }
        public ModInt add(long mi) {
            return new ModInt(ma.add(value, ma.remainder(mi)));
        }
        public ModInt sub(ModInt mi) {
            return new ModInt(ma.sub(value, mi.value));
        }
        public ModInt sub(long mi) {
            return new ModInt(ma.sub(value, ma.remainder(mi)));
        }
        public ModInt mul(ModInt mi) {
            return new ModInt(ma.mul(value, mi.value));
        }
        public ModInt mul(ModInt mi1, ModInt mi2) {
            return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2);
        }
        public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3) {
            return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3);
        }
        public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
        }
        public ModInt mul(ModInt mi1, ModInt... mis) {
            ModInt mi = mul(mi1);
            for (ModInt m : mis) mi.mulAsg(m);
            return mi;
        }
        public ModInt mul(long mi) {
            return new ModInt(ma.mul(value, ma.remainder(mi)));
        }
        public ModInt div(ModInt mi) {
            return new ModInt(ma.div(value, mi.value));
        }
        public ModInt div(long mi) {
            return new ModInt(ma.div(value, ma.remainder(mi)));
        }
        public ModInt inv() {
            return new ModInt(ma.inv(value));
        }
        public ModInt pow(long b) {
            return new ModInt(ma.pow(value, b));
        }
        public ModInt addAsg(ModInt mi) {
            this.value = ma.add(value, mi.value);
            return this;
        }
        public ModInt addAsg(ModInt mi1, ModInt mi2) {
            return addAsg(mi1).addAsg(mi2);
        }
        public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
            return addAsg(mi1).addAsg(mi2).addAsg(mi3);
        }
        public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return addAsg(mi1).addAsg(mi2).addAsg(mi3).addAsg(mi4);
        }
        public ModInt addAsg(ModInt... mis) {
            for (ModInt m : mis) addAsg(m);
            return this;
        }
        public ModInt addAsg(long mi) {
            this.value = ma.add(value, ma.remainder(mi));
            return this;
        }
        public ModInt subAsg(ModInt mi) {
            this.value = ma.sub(value, mi.value);
            return this;
        }
        public ModInt subAsg(long mi) {
            this.value = ma.sub(value, ma.remainder(mi));
            return this;
        }
        public ModInt mulAsg(ModInt mi) {
            this.value = ma.mul(value, mi.value);
            return this;
        }
        public ModInt mulAsg(ModInt mi1, ModInt mi2) {
            return mulAsg(mi1).mulAsg(mi2);
        }
        public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
            return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3);
        }
        public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
            return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
        }
        public ModInt mulAsg(ModInt... mis) {
            for (ModInt m : mis) mulAsg(m);
            return this;
        }
        public ModInt mulAsg(long mi) {
            this.value = ma.mul(value, ma.remainder(mi));
            return this;
        }
        public ModInt divAsg(ModInt mi) {
            this.value = ma.div(value, mi.value);
            return this;
        }
        public ModInt divAsg(long mi) {
            this.value = ma.div(value, ma.remainder(mi));
            return this;
        }
        @Override
        public String toString() {
            return String.valueOf(value());
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof ModInt) {
                ModInt mi = (ModInt) o;
                return mod() == mi.mod() && value() == mi.value();
            }
            return false;
        }
        @Override
        public int hashCode() {
            return (1 * 37 + mod()) * 37 + value();
        }
    }

    private static abstract class ModArithmetic {
        abstract int mod();
        abstract int remainder(long value);
        abstract int add(int a, int b);
        abstract int sub(int a, int b);
        abstract int mul(int a, int b);
        int div(int a, int b) {
            return mul(a, inv(b));
        }
        int inv(int a) {
            int b = mod();
            if (b == 1) return 0;
            long u = 1, v = 0;
            while (b >= 1) {
                int t = a / b;
                a -= t * b;
                int tmp1 = a; a = b; b = tmp1;
                u -= t * v;
                long tmp2 = u; u = v; v = tmp2;
            }
            if (a != 1) {
                throw new ArithmeticException("divide by zero");
            }
            return remainder(u);
        }
        int pow(int a, long b) {
            if (b < 0) throw new ArithmeticException("negative power");
            int r = 1;
            int x = a;
            while (b > 0) {
                if ((b & 1) == 1) r = mul(r, x);
                x = mul(x, x);
                b >>= 1;
            }
            return r;
        }
    
        static ModArithmetic of(int mod) {
            if (mod <= 0) {
                throw new IllegalArgumentException();
            } else if (mod == 1) {
                return new ModArithmetic1();
            } else if (mod == 2) {
                return new ModArithmetic2();
            } else if (mod == 998244353) {
                return new ModArithmetic998244353();
            } else if (mod == 1000000007) {
                return new ModArithmetic1000000007();
            } else if ((mod & 1) == 1) {
                return new ModArithmeticMontgomery(mod);
            } else {
                return new ModArithmeticBarrett(mod);
            }
        }

        private static final class ModArithmetic1 extends ModArithmetic {
            int mod() {return 1;}
            int remainder(long value) {return 0;}
            int add(int a, int b) {return 0;}
            int sub(int a, int b) {return 0;}
            int mul(int a, int b) {return 0;}
            int pow(int a, long b) {return 0;}
        }
        private static final class ModArithmetic2 extends ModArithmetic {
            int mod() {return 2;}
            int remainder(long value) {return (int) (value & 1);}
            int add(int a, int b) {return a ^ b;}
            int sub(int a, int b) {return a ^ b;}
            int mul(int a, int b) {return a & b;}
        }
        private static final class ModArithmetic998244353 extends ModArithmetic {
            private final int mod = 998244353;
            int mod() {
                return mod;
            }
            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }
            int add(int a, int b) {
                int res = a + b;
                return res >= mod ? res - mod : res;
            }
            int sub(int a, int b) {
                int res = a - b;
                return res < 0 ? res + mod : res;
            }
            int mul(int a, int b) {
                return (int) (((long) a * b) % mod);
            }
        }
        private static final class ModArithmetic1000000007 extends ModArithmetic {
            private final int mod = 1000000007;
            int mod() {
                return mod;
            }
            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }
            int add(int a, int b) {
                int res = a + b;
                return res >= mod ? res - mod : res;
            }
            int sub(int a, int b) {
                int res = a - b;
                return res < 0 ? res + mod : res;
            }
            int mul(int a, int b) {
                return (int) (((long) a * b) % mod);
            }
        }
        private static final class ModArithmeticMontgomery extends ModArithmeticDynamic {
            private final long negInv;
            private final long r2;
    
            private ModArithmeticMontgomery(int mod) {
                super(mod);
                long inv = 0;
                long s = 1, t = 0;
                for (int i = 0; i < 32; i++) {
                    if ((t & 1) == 0) {
                        t += mod;
                        inv += s;
                    }
                    t >>= 1;
                    s <<= 1;
                }
                long r = (1l << 32) % mod;
                this.negInv = inv;
                this.r2 = (r * r) % mod;
            }
            private int generate(long x) {
                return reduce(x * r2);
            }
            private int reduce(long x) {
                x = (x + ((x * negInv) & 0xffff_ffffl) * mod) >>> 32;
                return (int) (x < mod ? x : x - mod);
            }
            @Override
            int remainder(long value) {
                return generate((value %= mod) < 0 ? value + mod : value);
            }
            @Override
            int mul(int a, int b) {
                return reduce((long) a * b);
            }
            @Override
            int inv(int a) {
                return super.inv(reduce(a));
            }
            @Override
            int pow(int a, long b) {
                return generate(super.pow(a, b));
            }
        }
        private static final class ModArithmeticBarrett extends ModArithmeticDynamic {
            private static final long mask = 0xffff_ffffl;
            private final long mh;
            private final long ml;
            private ModArithmeticBarrett(int mod) {
                super(mod);
                /**
                 * m = floor(2^64/mod)
                 * 2^64 = p*mod + q, 2^32 = a*mod + b
                 * => (a*mod + b)^2 = p*mod + q
                 * => p = mod*a^2 + 2ab + floor(b^2/mod)
                 */
                long a = (1l << 32) / mod;
                long b = (1l << 32) % mod;
                long m = a * a * mod + 2 * a * b + (b * b) / mod;
                mh = m >>> 32;
                ml = m & mask;
            }
            private int reduce(long x) {
                long z = (x & mask) * ml;
                z = (x & mask) * mh + (x >>> 32) * ml + (z >>> 32);
                z = (x >>> 32) * mh + (z >>> 32);
                x -= z * mod;
                return (int) (x < mod ? x : x - mod);
            }
            @Override
            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }
            @Override
            int mul(int a, int b) {
                return reduce((long) a * b);
            }
        }
        private static class ModArithmeticDynamic extends ModArithmetic {
            final int mod;
            ModArithmeticDynamic(int mod) {
                this.mod = mod;
            }
            int mod() {
                return mod;
            }
            int remainder(long value) {
                return (int) ((value %= mod) < 0 ? value + mod : value);
            }
            int add(int a, int b) {
                int sum = a + b;
                return sum >= mod ? sum - mod : sum;
            }
            int sub(int a, int b) {
                int sum = a - b;
                return sum < 0 ? sum + mod : sum;
            }
            int mul(int a, int b) {
                return (int) (((long) a * b) % mod);
            }
        }
    }
}