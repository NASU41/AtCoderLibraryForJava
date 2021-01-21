# クラス DSU
- - -

無向グラフに対して,

* 辺の追加
* 2 頂点が連結かの判定

を $O(α(N))$ 時間で処理することが出来ます。

また、内部的に各連結成分ごとに代表となる頂点を 1 つ持っています。辺の追加により連結成分がマージされる時、新たな代表元は元の連結成分の代表元のうちどちらかになります。

## コンストラクタ
### DSU
```java
public DSU(int n){
```

* $n$頂点$0$辺の無向グラフを作ります。

制約
頂点数 $n$ の配列 $a_0, a_1, \dots, a_{n-1}$を作ります. 
初期値はすべて-1です.
(C++版だと10^8まで可能と書いてありますが、Javaだと厳しいかもしれません)
計算量: $O(n)$

## メソッド
### merge
```java
int merge(int a, int b)
```
辺$(a,b)$を足します。
$a,b$ が連結だった場合はその代表元、非連結だった場合は新たな代表元を返します。(index外の数字を入れた場合は-1を返します。)
**制約**
* 0 \leq a < n
* 0 \leq b < n

**計算量**
* ならし$O(α(n))$

### same
```java
boolean same(int a, int b){
```
頂点$a,b$が連結かどうかを返します。

**制約**
* 0 \leq a < n
* 0 \leq b < n

**計算量**
* ならし$O(α(n))$

### leader
```java
int leader(int a){
```
頂点$a$の属する連結成分の代表元を返します。

**制約**
* 0 \leq a < n

**計算量**
* ならし$O(α(n))$

### size
```java
int size(int a) {
```
頂点$a$の属する連結成分のサイズを返します。

**制約**
* 0 \leq a < n

**計算量**
* ならし$O(α(n))$

### groups
```java
ArrayList<ArrayList<Integer>> groups(){
```
グラフを連結成分に分け、その情報を返します。
返り値は「「一つの連結成分の頂点番号のリスト」のリスト」です。 (内側外側限らず)vector内でどの順番で頂点が格納されているかは未定義です。

**計算量**
* $O(n)$

## 使用例
- Java11
[[https://atcoder.jp/contests/practice2/submissions/16582269]]
- Java8
[[https://atcoder.jp/contests/practice2/submissions/16582277]]
