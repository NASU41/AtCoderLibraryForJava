# クラス FenwickTree

長さ $N$ の long型配列に対し,

* 要素の1点変更
* 区間の要素の総和

を $O(\log N)$ の時間で求めることができるデータ構造です.


## コンストラクタ
### FenwickTree
```java
public FenwickTree(int n)
```

長さ $n$ の配列 $a_0, a_1, \dots, a_{n-1}$を作ります. 初期値はすべて0です.
計算量: $O(n)$

```java
public FenwickTree(long[] data)
```

長さ $n$ の配列 $a_0, a_1, \dots, a_{n-1}$を `data` により初期化します. 
計算量: $O(n)$

## メソッド
### add
```java
public void add(int p, long x)
```
配列の第$p$要素に$x$を加える. すなわち, `a[p] += x` のこと.
計算量: $O(\log N)$

### set
```java
public void set(int p, long x)
```
配列の第$p$要素を$x$に変更する. すなわち, `a[p] = x` のこと.
計算量: $O(\log N)$

### get
```java
public long get(int p)
```
配列の第$p$要素を取得する. 計算量: $O(\log n)$

### sum
```java
public long sum(int l, int r)
```
$a_l + a_{l+1} + \dots + a_{r-1}$の値を返す. 計算量: $O(\log n)$

## 使用例
[[https://atcoder.jp/contests/practice2/submissions/16573339]]
