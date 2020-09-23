# クラス Permutation

本機能は AtCoderLibrary ではなく C++標準ライブラリ `std::next_permutation` の移植です.

$N$が与えられたとき，長さ$N$の順列を辞書順に列挙することができます．

また，拡張 for 文によるイテレーションをサポートしています．
以下のイテレーションは，時間計算量 $O(N * N!)$で動作します．

```
Permutation perm = new Permutation(n);
for (int[] p : perm) {
    // code here
}
```

## コンストラクタ

### Permutation

```java
public Permutation(int n)
```

長さ$N$の順列を列挙するイテレータを作ります. 計算量 $O(N)$

## メソッド

### hasNext

```java
public boolean hasNext()
```

イテレータが後続の要素を保持する場合に真を返します．計算量 $O(1)$

### next

```java
public int[] next()
```

イテレータの後続の要素を取得します．計算量 $O(N)$

### iterator

```java
public Iterator<int[]> iterator()
```

順列を列挙するイテレータを取得します．

### nextPermutation

```java
public static int[] nextPermutation(int[] a)
```

与えられた順列に対して，辞書順でその直後となるような順列を返却します．
但し，入力の配列が辞書順最大の場合は`null`を返却します．
入力配列中の任意の 2 要素が相異なることを仮定しています．
また，入力で与えられた順列を破壊的に変更することに注意してください．
計算量 $O(N)$
