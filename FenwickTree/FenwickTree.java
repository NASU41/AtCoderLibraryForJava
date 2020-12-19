class FenwickTree{
    private int _n;
    private long[] data;

    public FenwickTree(int n){
        this._n = n;
        data = new long[n];
    }

    /**
     * @verified https://atcoder.jp/contests/practice2/tasks/practice2_b
     * @submission https://atcoder.jp/contests/practice2/submissions/16580495
     */
    public FenwickTree(long[] data) {
        this(data.length);
        build(data);
    }

    public void set(int p, long x){
        add(p, x - get(p));
    }

    public void add(int p, long x){
        assert(0<=p && p<_n);
        p++;
        while(p<=_n){
            data[p-1] += x;
            p += p&-p;
        }
    }
    public long sum(int l, int r){
        assert(0<=l && l<=r && r<=_n);
        return sum(r)-sum(l);
    }

    public long get(int p){
        return sum(p, p+1);
    }

    private long sum(int r){
        long s = 0;
        while(r>0){
            s += data[r-1];
            r -= r&-r;
        }
        return s;
    }

    private void build(long[] dat) {
        System.arraycopy(dat, 0, data, 0, _n);
        for (int i=1; i<=_n; i++) {
            int p = i+(i&-i);
            if(p<=_n){
                data[p-1] += data[i-1];
            }
        }
    }
}
