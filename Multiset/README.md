# クラス Multiset

本機能は AtCoderLibrary ではなく C++標準ライブラリ `std::multiset` の移植です.

多重集合を表すクラスです.

* 定数個の要素の追加
* 特定の要素の個数

を $O(\log N)$ の時間で求めることができるデータ構造です.

## コンストラクタ
### Multiset
```java
public Multiset()
```

空の多重集合を作ります. 計算量 $O(1)$

```java
public MultiSet(List<T> list)
```
`list`の各要素を持った多重集合を作ります. 計算量 $O(`list.size()`)$

## メソッド
### add
```java
public void add(T elm, long amount)
```
多重集合に要素`elm`を`amount`個加えます. 計算量 O(log N)

### addOne
```java
public void addOne(T elm)
```
多重集合に要素`elm`を1個加えます. 計算量 O(log N)

### removeOne
```java
public void removeOne(T elm)
```
多重集合から要素`elm`を1個取り除きます. 計算量 O(log N)

### removeAll
```java
public void removeAll(T elm)
```
多重集合から要素`elm`を全て取り除きます. 計算量 O(log N)

### merge
```java
public static<T> Multiset<T> merge(Multiset<T> a, MMultiset<T> b)
```
`a` と `b`の和集合となる多重集合を新たに構成して返します.
