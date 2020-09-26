# Test of Lazy Segment Tree

遅延評価セグメント木のテストです．

- `Gen.java` : テストケースを LazySegTree/test/in 内に生成します．
- `NaiveSolution.java` : LazySegTree/test/in 内のテストを愚直に解いて，解答を LazySegTree/test/answer 内に生成します．
- `Solution.java` : LazySegTree/test/in 内のテストを遅延評価セグメント木を用いて解いて，解答を LazySegTree/test/out 内に生成します．
- `Test.java` : LazySegTree/test/answer 内の解答と LazySegTree/test/out 内の解答が一致しているかをテストします．
- `Main.java` : `Gen.java`，`NaiveSolution.java`，`Solution.java`，`Test.java` を順に実行します．

## テスト概要

整数列 $A_0, \dots, A_{N-1}$ が与えられる．以下に示すクエリが合計 $Q$ 個与えられるので，順に処理する．

- `0 p v` : $a_p = v$ とする．
- `1 p v` : $a_p$ に $v$ を加算する．
- `2 l r v` : $a_l, \dots, a_{r-1}$ に $v$ を加算する．
- `3 p` : $a_p$ の値を求めて出力する．
- `4 l r` : $\min\{a_i\mid l\leq i \lt r\}$ を求めて出力する．但し，$l=r$ の場合は `INF` と出力する．
- `5` : $\min\{a_i\mid 0\leq i \lt N\}$ を求めて出力する．
- `6 l v` : $\max\{r\mid\min\{a_i\mid l\leq i \lt r\}\gt v\}$ を求めて出力する．但し，$a_l\leq v$ なら $l$ を出力する．
- `7 r v` : $\min\{l\mid\min\{a_i\mid l\leq i \lt r\}\gt v\}$ を求めて出力する．但し，$a_{r-1}\leq v$ なら $r$ を出力する．

## 制約

- $1\leq N \leq 5000$
- $1\leq Q \leq 10000$
- $-10^9\leq A_i\leq 10^9$
- $-10^9\leq v\leq 10^9$
- $0\leq p\lt N$
- $0\leq l\leq r\leq N$

## 入力

```math
N Q
A_{0} \dots A_{N-1}
Query_{0}
\vdots
Query_{Q-1}
```
