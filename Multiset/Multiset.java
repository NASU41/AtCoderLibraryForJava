class Multiset<T> extends java.util.TreeMap<T,Long>{
    public Multiset(){
        super();
    }
    public Multiset(java.util.List<T> list){
        super();
        for(T e: list) this.addOne(e);
    }
    public long count(Object elm){
        return getOrDefault(elm,0L);
    }
    public void add(T elm, long amount){
        if(!this.containsKey(elm)) put(elm, amount);
        else replace(elm, get(elm)+amount);
        if(this.count(elm)==0) this.remove(elm);
    }
    public void addOne(T elm){
        this.add(elm, 1);
    }
    public void removeOne(T elm){
        this.add(elm, -1);
    }
    public void removeAll(T elm){
        this.add(elm, -this.count(elm));
    }
    public static<T> Multiset<T> merge(Multiset<T> a, Multiset<T> b){
        Multiset<T> c = new Multiset<>();
        for(T x: a.keySet()) c.add(x, a.count(x));
        for(T y: b.keySet()) c.add(y, b.count(y));
        return c;
    }
}