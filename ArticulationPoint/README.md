# Class ArticulationPoint

Given a graph generated using GraphBuilder.makeGraph(), this class finds all articulation points in graph. Since GraphBuilder assumes 0-based indexing, this class also works assuming 0-based indexing.

All members of this class are static, so no constructor defined.

## Methods

### articulationPoints
```
public static boolean[] articulationPoints(int[][] graph)
```
Returns boolean array of length $N$ where $N$ denotes the number of nodes in given graph, i-th element in this boolean array denotes whether i-th node in given graph is an articulation point or not.

** Constraints **
* graph is generated using GraphBuilder.makeGraph(), so all constraints applicable to makeGraph method applies.

** Computational complexity **
* $ O (NumberOfNodes + NumberOfEdges) $
