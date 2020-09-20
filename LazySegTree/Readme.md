# クラス LazySegTree

[モノイド](https://ja.wikipedia.org/wiki/%E3%83%A2%E3%83%8E%E3%82%A4%E3%83%89) `(S,⋅:S×S→S,e∈S)` と、`S` から `S` への写像の集合 `F` であって、以下の条件を満たすようなものについて使用できるデータ構造です。

- `F` は恒等写像 `id` を含む。つまり、任意の $x∈S$ に対し `id(x) = x` をみたす。
- `F` は写像の合成について閉じている。つまり、任意の `f,g∈F` に対し `f∘g∈F` である。
- 任意の `f∈F,x,y∈S` に対し `f(x⋅y)=f(x)⋅f(y)` をみたす。

長さ `N` の `S` の配列に対し、

- 区間の要素に一括で `F` の要素 `f` を作用 (`x = f(x)`)
- 区間の要素の総積の取得

を $O(\log N)$ で行うことが出来ます。

また、このライブラリはオラクルとして `op`, `mapping`, `composition` を使用しますが、これらが定数時間で動くものと仮定したときの計算量を記述します。オラクル内部の計算量が $O(f(n))$ である場合はすべての計算量が $O(f(n))$ 倍となります

## コンストラクタ

引数の意味は以下の通りです

- `S` : モノイドの型
- `F` : 写像の型
- `op` : `⋅:S×S→S` を計算する関数 S
- `e` : モノイドの単位元
- `mapping`: `f(x)` を返す関数
- `composition` : `f∘g` を返す関数
- `id` : 恒等写像

```java
public LazySegTree<S, F>(int n, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id)
```

長さ `n` の配列 `a[0], a[1], ..., a[n - 1]` を作ります. 初期値はすべて $e$ です.

計算量: $O(n)$

```java
public LazySegTree<S, F>(S[] dat, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id)
```

長さ `n` の配列 `a[0], a[1], ..., a[n - 1]` を `dat` により初期化します.

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

計算量: $O(\log n)$

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

### apply

```java
// (1)
public void apply(int p, F f)
// (2)
public void apply(int l, int r, F f)
```

- (1): `a[p]` に作用素 `f` を作用させます
- (2): `i∈[l, r)` に対して `a[i]` に作用素 `f` を作用させます

制約

- (1): `0 <= p < n`
- (2): `0 <= l <= r <= n`

計算量

$O(\log n)$

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

[AtCoder Library Practice Contest K - Range Affine Range Sum](https://atcoder.jp/contests/practice2/submissions/16646083)
