class Bridge{
	private static int time;
	private static int[] disc, low;
	private static boolean[] visited;
	
	public static boolean[] bridges(int[][][] graphWithEdgeInfo, int EdgeCount){
		int NumberOfNodes = graphWithEdgeInfo.length;

		time = -1;
		disc = new int[NumberOfNodes];
		low = new int[NumberOfNodes];
		visited = new boolean[NumberOfNodes];
		java.util.Arrays.fill(low, 2*NumberOfNodes);

		boolean[] isBridge = new boolean[EdgeCount];
		for(int i = 0; i< NumberOfNodes; i++)if(!visited[i] && graphWithEdgeInfo[i] != null)bridgeUtil(graphWithEdgeInfo, isBridge, i, -1);
		return isBridge;
	}

	private static void bridgeUtil(int[][][] g, boolean[] isBridge, int u, int p){
		visited[u] = true;
		disc[u] = low[u] = ++time;

		for(int[] edge: g[u]){
			int v = edge[0], edgeIndex = edge[1];
			if(v == p)continue;

			if(visited[v])
				low[u] = Math.min(low[u], disc[v]);
			else{
				bridgeUtil(g, isBridge, v, u);
				low[u] = Math.min(low[u], low[v]);

				if(low[v] > disc[u])isBridge[edgeIndex] = true;
			}
		}
	}
}
