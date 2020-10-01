class GraphBuilder{
	public int[][] makeGraph(int NumberOfNoes, int NumberOfEdges, int[] from, int[] to, boolean undirected){
		int[][] graph = new int[NumberOfNoes][];
		int[] outdegree = new int[NumberOfNoes];
		for(int i = 0; i< NumberOfEdges; i++){
		    outdegree[from[i]]++;
		    if(undirected)outdegree[to[i]]++;
		}
		for(int i = 0; i< NumberOfNoes; i++)graph[i] = new int[outdegree[i]];
		for(int i = 0; i< NumberOfEdges; i++){
		    graph[from[i]][--outdegree[from[i]]] = to[i];
		    if(undirected)graph[to[i]][--outdegree[to[i]]] = from[i];
		}
		return graph;
	}
	
	public int[][][] makeGraphWithEdgeInfo(int NumberOfNoes, int NumberOfEdges, int[] from, int[] to, boolean undirected){
		int[][][] graph = new int[NumberOfNoes][][];
		int[] outdegree = new int[NumberOfNoes];
		for(int i = 0; i< NumberOfEdges; i++){
		    outdegree[from[i]]++;
		    if(undirected)outdegree[to[i]]++;
		}
		for(int i = 0; i< NumberOfNoes; i++)graph[i] = new int[outdegree[i]][];
		for(int i = 0; i< NumberOfEdges; i++){
		    graph[from[i]][--outdegree[from[i]]] = new int[]{to[i], i, 0};
		    if(undirected)graph[to[i]][--outdegree[to[i]]] = new int[]{from[i], i, 1};
		}
		return graph;
	}
	
	//Usage Exmaple
	public void makeGraphUsage(){
	    int N = 6, E = 8;
	    int[] from = new int[]{0,0,0,1,1,2,2,4};
	    int[] to = new int[]{2,4,5,4,5,3,4,5};
	    
	    int[][] graph = makeGraph(N, E, from, to, true);
	    for(int i = 0; i< N; i++){
	        System.out.print(i+": ");
	        for(int j:graph[i])System.out.print(j+" ");
	        System.out.println();
	    }
	}
	
	//Usage Example
	public void makeGraphWithEdgeInfoUsage(){
	    int N = 6, E = 8;
	    int[] from = new int[]{0,0,0,1,1,2,2,4};
	    int[] to = new int[]{2,4,5,4,5,3,4,5};
	    
	    int[][][] graph = makeGraphWithEdgeInfo(N, E, from, to, true);
	    for(int i = 0; i< N; i++){
	        System.out.print(i+": ");
	        for(int[] j:graph[i])System.out.print("["+j[0]+", "+j[1]+", "+j[2]+"],");
	        System.out.println();
	    }
	}
}
