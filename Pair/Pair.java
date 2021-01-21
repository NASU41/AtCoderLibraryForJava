class Pair<S extends Comparable<S>, T extends Comparable<T>> implements Comparable<Pair<S,T>>{
    S first;
    T second;

    public Pair(S s, T t){
        first = s;
        second = t;
    }
    public boolean equals(Object another){
        if(this==another) return true;
        if(!(another instanceof Pair)) return false;
        Pair otherPair = (Pair)another;
        return this.first.equals(otherPair.first) && this.second.equals(otherPair.second);
    }
    public int compareTo(Pair<S,T> another){
        if(this.second.compareTo(another.second) == 0) return this.first.compareTo(another.first);
        else return this.second.compareTo(another.second);
    }
    public int hashCode(){
        return first.hashCode() * 10007 + second.hashCode();
    }
    public String toString(){
        return String.format("(%s, %s)", first, second);
    }
}
