# クラス ModIntFactory, ModIntFactory$ModInt
- - -

剰余演算をサポートするクラスです.

## 使い方

1. `ModIntFactory` のインスタンス (ModInt のファクトリ) を生成
2. 生成した Factory から `ModIntFactory$ModInt` を生成

```java
public static void main(String[] args) {
    // ModIntFactory のインスタンスを生成 (ここで mod を設定する)
    ModIntFactory factory = new ModIntFactory(998244353);
    // ModIntFactory を用いて ModInt を生成する
    ModIntFactory.ModInt n = factory.create(10000);
    ModIntFactory.ModInt m = factory.create(100000);
    // (n * m) % mod を計算
    ModIntFactory.ModInt k = n.mul(m);
    // value() メソッドにより値を取得する
    int kVal = k.value();
}
```

`Java 11` を使うことの出来る環境では，以下のように簡潔に書くことが出来ます.

```java
public static void main(String[] args) {
    var factory = new ModIntFactory(998244353);
    var n = factory.create(10000);
    var m = factory.create(100000);
    var k = n.mul(m);
    int kVal = k.value();
}
```

## コンストラクタ
### ModIntFactory
```java
public ModIntFactory(int mod)
```

`ModInt` を生成するファクトリを生成します.
計算量: $O(1)$

### ModIntFactory$ModInt
外部からコンストラクタを呼ぶことは出来ません. (呼ばないで下さい)

## メソッド
### ModIntFactory
```java
public ModInt ModIntFactory(int value)
```

値 `value % mod` を持つ `ModInt` を生成します.
計算量: $O(1)$

### ModIntFactory$ModInt
#### mod
```java
public int mod()
```

`mod` を返します．

計算量: $O(1)$

#### value
```java
public int value()
```

保持している値を返します．__注意: `ModInt` のフィールド `int value` に直接アクセスはしないで下さい. 正しい値が取得できない可能性があります.__

計算量: $O(1)$

#### add
```java
public ModInt add(ModInt mi)
```

値 `(a + b) % mod` を持つ `ModInt` を新たに生成します (`a` および `b` の値は書き換わりません).

計算量: $O(1)$

#### sub
```java
public ModInt sub(ModInt mi)
```

値 `(a - b) % mod` を持つ `ModInt` を新たに生成します (`a` および `b` の値は書き換わりません).

計算量: $O(1)$

#### mul
```java
public ModInt mul(ModInt mi)
```

値 `(a * b) % mod` を持つ `ModInt` を新たに生成します (`a` および `b` の値は書き換わりません).

計算量: $O(1)$

#### div
```java
public ModInt div(ModInt mi)
```

値 `(a * b^(-1)) % mod` を持つ `ModInt` を新たに生成します (`a` および `b` の値は書き換わりません). ただし，`b^(-1)` は `(b * x) % mod = 1` を満たす `x` です.

計算量: $O(\log \mod)$

制約
- `gcd(b, mod) = 1`

#### inv
```java
public ModInt inv()
```
`ModInt a` に対して, `(a * x) % mod = 1` を満たす値 `x` を持つ `ModInt` を新たに生成します (`a` の値は書き換わりません).

計算量: $O(\log \rm{mod})$

制約
- `gcd(a, mod) = 1`

#### pow
```java
public ModInt pow(long n)
```

`(a ^ n) % mod ` を満たす値 `x` を持つ `ModInt` を新たに生成します (`a` の値は書き換わりません).

計算量: $O(\log n)$
