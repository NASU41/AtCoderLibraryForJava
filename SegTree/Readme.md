# クラス SegTree

[モノイド](https://ja.wikipedia.org/wiki/%E3%83%A2%E3%83%8E%E3%82%A4%E3%83%89) $(S,⋅:S×S→S,e∈S)$、つまり

- 結合律: $\forall a,b,c∈S, (a⋅b)⋅c = a⋅(b⋅c)$

- 単位元の存在: $\forall a∈S, a⋅e = e⋅a = a$
を満たす代数構造に対し使用できるデータ構造です。

長さ $N$ の $S$ の配列に対し、以下のクエリを $O(\log N)$ で処理することが出来ます。

- 要素の 1 点変更
- 区間の要素の総積の取得

を $O(\log N)$ で行うことが出来ます。

また、このライブラリはオラクルとして `op` を使用しますが、これらが定数時間で動くものと仮定したときの計算量を記述します。オラクル内部の計算量が $O(f(n))$ である場合はすべての計算量が $O(f(n))$倍となります。

## コンストラクタ

```java
public SegTree(int n, java.util.function.BinaryOperator<S> op, S e)
```

長さ $n$ の配列 $a_0, a_1, \dots, a_{n-1}$を作ります. 初期値はすべて $e$ です.

計算量: $O(n)$

```java
public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e)
```

長さ $n$ の配列 $a_0, a_1, \dots, a_{n-1}$ を `dat` により初期化します.

計算量: $O(n)$

## メソッド

### set

```java
public void set(int p, S x)
```

`a[p]=x` とします．

計算量: $O(\log n)$

制約: `0 <= p < n`

### get

```java
public S get(int p)
```

`a[p]` を取得します．

計算量: $O(1)$

制約: `0 <= p < n`

### prod

```java
public S prod(int l, int r)
```

`op(a[l], ..., a[r - 1])` を、モノイドの性質を満たしていると仮定して計算します。`l = r` のときは単位元 `e` を返します。

計算量: $O(\log n)$

制約: `0 <= l <= r <= n`

### allProd

```java
public S allProd()
```

`op(a[0], ..., a[n - 1])` を、モノイドの性質を満たしていると仮定して計算します。`n = 0` のときは単位元 `e` を返します。

計算量: $O(1)$

### maxRight

```java
public int maxRight(int l, java.util.function.Predicate<S> f)
```

`S` を引数にとり `boolean` を返す関数を渡して使用します。
以下の条件を両方満たす `r` を (いずれか一つ) 返します。

- `r = l` もしくは `f(op(a[l], a[l + 1], ..., a[r - 1])) = true`
- `r = n` もしくは `f(op(a[l], a[l + 1], ..., a[r])) = false`

`f` が単調だとすれば、`f(op(a[l], a[l + 1], ..., a[r - 1])) = true` となる最大の `r`、と解釈することが可能です。

制約

- `f` を同じ引数で呼んだ時、返り値は等しい(=副作用はない)
- __`f(e) = true`__
- `0 <= l <= n`
計算量

$O(\log n)$

### minLeft

```java
public int minLeft(int r, java.util.function.Predicate<S> f)
```

`S` を引数にとり `boolean` を返す関数オブジェクトを渡して使用します。
以下の条件を両方満たす `l` を (いずれか一つ) 返します。

- `l = r` もしくは `f(op(a[l], a[l + 1], ..., a[r - 1])) = true`
- `l = 0` もしくは `f(op(a[l - 1], a[l + 1], ..., a[r - 1])) = false`

fが単調だとすれば、`f(op(a[l], a[l + 1], ..., a[r - 1])) = true` となる最小の `l`、と解釈することが可能です。

制約

- `f` を同じ引数で呼んだ時、返り値は等しい(=副作用はない)
- `f(e) = true`
- `0 <= r <= n`

計算量

$O(\log n)$

## 使用例

[AtCoder Library Practice Contest J - Segment Tree](https://atcoder.jp/contests/practice2/submissions/16646450)