# クラス MinCostFlow

## コンストラクタ

```java
public MinCostFlow(int n)
```

`n` 頂点 `0` 辺のグラフを作る。

計算量

$O(n)$

## メソッド

### addEdge

```java
public int addEdge(int from, int to, long cap, long cost)
```

`from` から `to` へ最大容量 `cap` , コスト `cost` の辺を追加する。何番目に追加された辺かを返す。

制約

- `0 <= from, to < n`
- `0 <= cap, cost`

計算量

ならし $O(1)$

### minCostFlow

```java
// (1)
public long[] minCostMaxFlow(int s, int t)
// (2)
public long[] minCostFlow(int s, int t, long flowLimit)
```

`s` から `t` へ流せるだけ流し、その流量とコストを返す。返り値は長さ 2 の配列で表現され、中身は `{flow, cost}` である。

- (1): `s` から `t` へ流せるだけ流す
- (2): `s` から `t` へ流量 `flowLimit` まで流せるだけ流す

制約

`minCostSlope` と同じ

計算量

`minCostSlope` と同じ

### minCostSlope

```java
// (1)
public java.util.ArrayList<long[]> minCostSlope(int s, int t)
// (2)
public java.util.ArrayList<long[]> minCostSlope(int s, int t, long flowLimit)
```

返り値に流量とコストの関係の折れ線が入る。全ての `x` について、流量 `x` の時の最小コストを `g(x)` とすると、`{x, g(x)}` は返り値を折れ線として見たものに含まれる。返り値のリストは以下のような性質を満たします。

- 返り値の最初の要素は `{0, 0}`
- `flow`, `cost` は狭義単調増加
- 3点が同一線上にあることはない
- (1): 返り値の最後の要素は最大流量 `x` として `{x, g(x)}`
- (2): 返り値の最後の要素は `y = min(x, flowLimit)` として `{y, g(y)}`

制約

- `s != t`
- `minCostSlope` や `minCostFlow` を合わせて複数回呼んだときの挙動は未定義 (複数回フローを流す場合は後述の `clearFlow` メソッドによりフローをリセットしてください)

計算量

`F` を流量、`m` を追加した辺の本数として $O(F (n + m) \log n)$

### clearFlow

```java
public void clearFlow()
```

全ての辺に流れているフローを 0 にします。

計算量

`m` を追加した辺の本数として $O(n + m)$

### getEdge / getEdges

`getEdge`、`getEdges` の返り値には以下のメソッドを持つ `MinCostFlow.WeightedCapEdge` 型が用いられています。

```java
class WeightedCapEdge {
    // (1)
    public int getFrom()
    // (2)
    public int getTo()
    // (3)
    public long getCap()
    // (4)
    public long getCost()
    // (4)
    public long getFlow()
};
```

- (1): 始点を返します。計算量: $O(1)$
- (2): 終点を返します。計算量: $O(1)$
- (3): 現在の容量を返します。計算量: $O(1)$
- (4): コストを返します。計算量: $O(1)$
- (5): 現在の流量を返しまず。計算量: $O(1)$

```java
// (1)
public CapEdge getEdge(int i)
// (2)
public java.util.ArrayList<WeightedCapEdge> getEdges()
```

今の内部の辺の状態を返す。辺の順番は `addEdge` で追加された順番と同一。

制約

- (1): 追加した辺の数を `m` として，`0 <= i < m`。

計算量

- (1): $O(1)$
- (2): $O(1)$

使用例

[AtCoder Library Practice Contest E - MinCostFlow](https://atcoder.jp/contests/practice2/submissions/16654383)
