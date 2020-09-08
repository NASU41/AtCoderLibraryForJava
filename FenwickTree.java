class FenwickTree{
    private int _n;
    private long[] data;

    public FenwickTree(int n){
        this._n = n;
        data = new long[n];
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

    private long sum(int r){
        long s = 0;
        while(r>0){
            s += data[r-1];
            r -= r&-r;
        }
        return s;
    }
}
