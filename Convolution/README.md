# クラス Convolution

- - -

NTTで剰余Convolutionを計算するクラスです。

## 使い方

1. staticな `Convolution.convolute` を呼び出します.

```java
public static void main(String[] args) {
    // 3以上の素数のModを指定します
    int mod = 998244353;
    long[] a = { 1, 2, 3, 4, 5 };
    long[] b = { 6, 7, 8 };
    // 畳み込みを計算しまqす。a.length + b.length - 1 の配列が帰ります。
    long[] ret = Convolution.convolution(a, b, mod);
    System.out.println(Arrays.toString(ret));
}
```

## メソッド

### convolution

```java
public static long[] convolution(long[] a, long[] b, int mod) 
public static java.util.List<ModIntFactory.ModInt> convolution(
            java.util.List<ModIntFactory.ModInt> a,
            java.util.List<ModIntFactory.ModInt> b
)
```

リストまたは配列 `a` と `b` の剰余convolutionを計算します.
計算量: $O(n ¥log(n))$

制約
- `mod` 3以上の素数
