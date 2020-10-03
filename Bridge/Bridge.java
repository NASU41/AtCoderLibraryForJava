class Bridge{
	private static int time;
	private static int[] disc, low;
	private static boolean[] visited;

	public static boolean[] bridges(int[][][] graphWithEdgeInfo){
		int edgeCount = 0;
		for(int[][] row:graphWithEdgeInfo)edgeCount += row.length;
		return bridges(graphWithEdgeInfo, edgeCount/2);
	}

	public static boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount){
		int NumberOfNodes = graphWithEdgeInfo.length;

		time = -1;
		disc = new int[NumberOfNodes];
		low = new int[NumberOfNodes];
		visited = new boolean[NumberOfNodes];
		java.util.Arrays.fill(low, 2*NumberOfNodes);

		boolean[] isBridge = new boolean[edgeCount];
		for(int i = 0; i< NumberOfNodes; i++)if(!visited[i] && graphWithEdgeInfo[i] != null)bridgeUtil(graphWithEdgeInfo, isBridge, i, -1);
		return isBridge;
	}

	private static void bridgeUtil(int[][][] g, boolean[] isBridge, int u, int parentEdge){
		visited[u] = true;
		disc[u] = low[u] = ++time;

		for(int[] edge: g[u]){
			int v = edge[0], edgeIndex = edge[1];
			if(parentEdge == edgeIndex)continue;

			if(visited[v])
				low[u] = Math.min(low[u], disc[v]);
			else{
				bridgeUtil(g, isBridge, v, edgeIndex);
				low[u] = Math.min(low[u], low[v]);

				if(low[v] > disc[u])isBridge[edgeIndex] = true;
			}
		}
	}
}
