# Class Bridge

Given a graph generated using GraphBuilder.makeGraphWithEdgeInfo(), this class finds all bridges in graph. Since GraphBuilder assumes 0-based indexing, this class also works assuming 0-based indexing.

All members of this class are static, so no constructor defined.

## Methods

### bridges
```
public static boolean[] bridges(int[][][] graphWithEdgeInfo, int EdgeCount)
```
Returns boolean array of length $EdgeCount$ where $EdgeCount$ denotes the number of edges in given graph, i-th element in this boolean array denotes whether i-th edge in given graph is a bridge or not. The edges are numbered as per their order in input, defined in GraphBuilder.makeGraphWithEdgeInfo()

** Constraints **
* graph is generated using GraphBuilder.makeGraphWithEdgeInfo(), so all constraints applicable to makeGraphWithEdgeInfo method applies.
* EdgeCount = Number of edges specified in GraphBuilder.makeGraphWithEdgeInfo()

** Computational complexity **
* $ O (NumberOfNodes + NumberOfEdges) $
