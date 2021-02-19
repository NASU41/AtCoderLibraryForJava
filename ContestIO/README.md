# クラス ContestScanner

競技プログラミングで利用できる入力クラスです.
`java.util.Scanner`等の一般的な入力クラスと比較して高速ですが, ASCII文字以外を入力した際に意図しない動作が発生することがあるので注意してください.

## コンストラクタ

```java
public ContestScanner(java.io.InputStream in)
```
指定された入力ストリームからスキャンされた値を生成する新しいContestScannerを構築します.

```java
public ContestScanner()
```
標準入力からスキャンされた値を生成する新しいContestScannerを構築します.


## メソッド

### hasNext
```java
public boolean hasNext()
```
このスキャナが入力内に別のトークンを保持する場合はtrueを返します.

### next
```java
public String next()
```
このスキャナから次の完全なトークンを検索して返します。

### 数値入力
```java
public int nextInt()
public long nextLong()
public double nextDouble()
```
次のトークンを`int`型/`long`型/`double`型として解釈して返します.

### 配列入力
```java
public long[] nextLongArray(int length)
public int[] nextIntArray(int length)
public double[] nextDoubleArray(int length)
```
`length`個の連続した値を読み取り, 配列として返します.

```java
public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map)
public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map)
public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map)
```
`length`個の連続した値を読み取り, 各要素に関数`map`を適用したものを配列として返します.
1-indexで与えられた入力を0-indexに変換するなどの用途を想定しています.

### 二次元配列入力
```java
public long[][] nextLongMatrix(int height, int width)
public int[][] nextIntMatrix(int height, int width)
public double[][] nextDoubleMatrix(int height, int width)
```
H行W列に並んだ値を読み取り, 二次元配列として返します.

```java
public char[][] nextCharMatrix(int height, int width)
```
H行W列に並んだ文字を読み取り, 二次元配列として返します.
前述の数値型に関するメソッドと異なり, 各行での空白区切りはないものとしていることに注意してください.

## 使用例

[ABC187 E - Through Path](https://atcoder.jp/contests/abc187/submissions/20257303)
最大100万個の入力, 20万個の出力が必要な問題です.

# クラス ContestPrinter
競技プログラミングで利用できる出力クラスです.
基本的な機能は[`java.io.PrintWriter`](https://docs.oracle.com/javase/jp/8/docs/api/java/io/PrintWriter.html)を利用しています. 自動フラッシュは行われないので, プログラムを終了する前に`flush()`や`close()`等のメソッドを実行することを忘れないように注意してください.
以下のドキュメントには, [`java.io.PrintWriter`](https://docs.oracle.com/javase/jp/8/docs/api/java/io/PrintWriter.html)との差分のみ記述します.

## コンストラクタ
```java
public ContestPrinter(java.io.PrintStream stream)
public ContestPrinter()
```
指定された出力ストリームから新しいContestPrinterを作成します. 指定を省略した場合は`System.out`が指定されます.

## メソッド
### print(double)
```java
public void print(float f)
public void println(float f)
public void print(double d)
public void println(double d)
```
通常の`printWriter`で浮動小数点数を出力しようとすると, `3.13E-10`のような指数表記になってしまうことがありますが, 本メソッドでは小数点以下20桁まで通常の形式での出力を行います. これにより, 表示形式の不一致による意図しないWAを防ぐことができます.
(多くの問題では「絶対誤差または相対誤差が一定値以下ならAC」というように判定されるため, 正しい計算で得られた値を十分な桁数出力すればACが得られます)

### printArray
```java
public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map)
public void printArray(int[] array, java.util.function.IntUnaryOperator map)
public void printArray(int[] array, String separator)
public void printArray(int[] array)

public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map)
public void printArray(long[] array, java.util.function.LongUnaryOperator map)
public void printArray(long[] array, String separator)
public void printArray(long[] array)

public void printArray(double[] array, String separator, java.util.function.DoubleUnaryOperator map)
public void printArray(double[] array, java.util.function.DoubleUnaryOperator map)
public void printArray(double[] array, String separator)
public void printArray(double[] array)
```
`array`の各要素に対して関数`map`を適用し, `separator`区切りで出力します. 行末には`separator`は含まれず, 改行が挿入されます.
`map`及び`separator`は省略可能で, 省略した場合は恒等写像/半角スペースが与えられた場合と同等の出力になります.

`map`は0-indexedの計算結果を1-indexedに変換するような場合に利用するとよいです.

## 使用例

[ABC187 E - Through Path](https://atcoder.jp/contests/abc189/submissions/20258750)
最大100万個の入力, 20万個の出力が必要な問題です.