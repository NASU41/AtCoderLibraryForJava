class GraphBuilder{
	public static int[][] makeGraph(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to, boolean undirected){
		int[][] graph = new int[NumberOfNodes][];
		int[] outdegree = new int[NumberOfNodes];
		for(int i = 0; i< NumberOfEdges; i++){
		    outdegree[from[i]]++;
		    if(undirected)outdegree[to[i]]++;
		}
		for(int i = 0; i< NumberOfNodes; i++)graph[i] = new int[outdegree[i]];
		for(int i = 0; i< NumberOfEdges; i++){
		    graph[from[i]][--outdegree[from[i]]] = to[i];
		    if(undirected)graph[to[i]][--outdegree[to[i]]] = from[i];
		}
		return graph;
	}
	
	public static int[][][] makeGraphWithEdgeInfo(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to, boolean undirected){
		int[][][] graph = new int[NumberOfNodes][][];
		int[] outdegree = new int[NumberOfNodes];
		for(int i = 0; i< NumberOfEdges; i++){
		    outdegree[from[i]]++;
		    if(undirected)outdegree[to[i]]++;
		}
		for(int i = 0; i< NumberOfNodes; i++)graph[i] = new int[outdegree[i]][];
		for(int i = 0; i< NumberOfEdges; i++){
		    graph[from[i]][--outdegree[from[i]]] = new int[]{to[i], i, 0};
		    if(undirected)graph[to[i]][--outdegree[to[i]]] = new int[]{from[i], i, 1};
		}
		return graph;
	}
}
