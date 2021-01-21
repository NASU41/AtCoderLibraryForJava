# クラス Convolution

- - -

NTTで剰余Convolutionを計算するクラスです。内部でModIntを使用しているメソッドが1個あるので、
それを削除するか、使用する場合はModIntも貼り付けてください。

## 使い方

1. staticな `Convolution.convolute` を呼び出します.

```java
public static void main(String[] args) {
    // NTT用素数のModを指定します
    int mod = 998244353;
    long[] a = { 1, 2, 3, 4, 5 };
    long[] b = { 6, 7, 8 };
    // 畳み込みを計算します。a.length + b.length - 1 の配列が帰ります。
    long[] ret = Convolution.convolution(a, b, mod);
    System.out.println(Arrays.toString(ret));
}
```

## メソッド

### convolution (NTT prime only)

```java
public static long[] convolution(long[] a, long[] b, int mod) 
```

リストまたは配列 `a` と `b` の剰余convolutionを計算します.
計算量: $O(n ¥log(n))$

制約
- `mod` NTT用素数(998244353, 1053818881, 1004535809, ...)


### convolution (Any mod)

```java
public static long[] convolutionLL(long[] a, long[] b, int mod) 
public static java.util.List<ModIntFactory.ModInt> convolution(
            java.util.List<ModIntFactory.ModInt> a,
            java.util.List<ModIntFactory.ModInt> b
)
```

リストまたは配列 `a` と `b` の剰余convolutionを計算します.
計算量: $O(n ¥log(n))$

制約
- `mod` 任意のmod
