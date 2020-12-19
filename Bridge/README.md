# Class Bridge

Given an undirected graph generated using GraphBuilder.makeGraphWithEdgeInfo(), this class finds all bridges in graph. Since GraphBuilder assumes 0-based indexing, this class also works assuming 0-based indexing. This class can handle multi-edges as well as self loops.

All members of this class are static, so no constructor defined.

## Methods

### bridges
```
public static boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount)
```
Returns boolean array of length $edgeCount$ where $EdgeCount$ denotes the number of edges in given graph, i-th element in this boolean array denotes whether i-th edge in given graph is a bridge or not. The edges are numbered as per their order in input, defined in GraphBuilder.makeGraphWithEdgeInfo()

** Constraints **
* graph is generated using GraphBuilder.makeGraphWithEdgeInfo(), so all constraints applicable to makeGraphWithEdgeInfo method applies.
* edgeCount = Number of edges specified in GraphBuilder.makeGraphWithEdgeInfo()

** Computational complexity **
* $ O (NumberOfNodes + NumberOfEdges) $


```
public static boolean[] bridges(int[][][] graphWithEdgeInfo)
```
This method computed the number of edges in specified graph, and invokes ```boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount)``` to return boolean array, i-th element in this boolean array denotes whether i-th edge in given graph is a bridge or not.

** Constraints **
* graph is generated using GraphBuilder.makeGraphWithEdgeInfo(), so all constraints applicable to makeGraphWithEdgeInfo method applies.

** Computational complexity **
* $ O (NumberOfNodes + NumberOfEdges) $
