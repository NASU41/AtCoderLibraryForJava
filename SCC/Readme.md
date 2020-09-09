# クラス SCC (Strongly Connected Components: 強連結成分分解)

有向グラフを強連結成分分解します。

## コンストラクタ

```java
SCC(int n)
```

`n` 頂点 `0` 辺の有向グラフを作る。

計算量

$O(n)$

## メソッド

### addEdge

```java
public void addEdge(int from, int to)
```

頂点 `from` から頂点 `to` へ有向辺を足す。

制約

- `0 <= from < n`
- `0 <= to < n`

計算量

amortized $O(1)$

### build

```java
public int[][] build()
```

以下の条件を満たすような、「頂点の配列」の配列を返します。

- 全ての頂点がちょうど 1 つずつ、どれかのリストに含まれます。
- 内側のリストと強連結成分が一対一に対応します。リスト内での頂点の順序は未定義です。
- 配列はトポロジカルソートされています。異なる強連結成分の頂点 `u`, `v` について、`u` から `v` に到達できる時、`u` の属する配列は `v` の属する配列よりも前です。

計算量

追加した辺の本数を `m` として $O(n + m)$

### id

```java
public int id(int i)
```

頂点 `i` が (トポロジカル順序において) 何番目の強連結成分に属しているかを返します。即ち，`build` で得られる「頂点の配列」の配列を `g` とすると、`g[id(i)][j] = i` なる `j` が存在します。

__ただし、`build` を呼ぶ前にこのメソッドが呼ばれた場合は、実行時例外 `UnsupportedOperationException` が発生します。__

制約

- __`build` メソッドを既に呼んでいる__
- `0 <= i < n`

計算量

$O(1)$

## 使用例

[AtCoder Library Practice Contest G - SCC](https://atcoder.jp/contests/practice2/submissions/16603978)
