# Class GraphBuilder

Creates graph in form of jagged arrays (as compared to Arraylist) for directed as well as undirected graphs from given set of edges in 0 indexing.

All members of this class are static, so no constructor defined.

## Methods

### makeGraph
```
public static int[][] makeGraph(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to, boolean undirected)
```
Returns the adjacency list representation of graph with specified Number of Nodes and Edges and the edges specified in from and to arrays in 0-based indexing. If undirected is set to true, the edges are deemed to be undirected, otherwise directed.

```graph[u]``` is the array of vertices having an edge outgoing from vertex u.

** Constraints **
* 0 \leq from[i], to[i] < NumberOfNodes
* from.length = to.length = NumberOfEdges

** Computational complexity **
* $ O (NumberOfNodes + NumberOfEdges) $

### makeGraphWithEdgeInfo
```
public static int[][][] makeGraphWithEdgeInfo(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to, boolean undirected)
```

Returns the adjacency list representation of graph with specified Number of Nodes and Edges and the edges specified in from and to arrays in 0-based indexing. If undirected is set to true, the edges are deemed to be undirected, otherwise directed. This method also returns useful information about edge index and edge direction.

```graph[u]``` is the array of tuples ```[v, idx, direction]``` where edge indexed ```idx``` is between node ```u``` and node ```v``` and direction takes value either $0$ or $1$.
- If direction is $0$, edge indexed ```idx``` is from ```u``` to ```v```
- If direction is $1$, edge indexed ```idx``` is from ```v``` to ```u```

** Constraints **
* 0 \leq from[i], to[i] < NumberOfNodes
* from.length = to.length = NumberOfEdges

** Computational complexity **
* $ O (NumberOfNodes + NumberOfEdges) $
