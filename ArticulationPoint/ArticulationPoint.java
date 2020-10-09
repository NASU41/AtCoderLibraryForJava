class ArticulationPoints{
	private static int time;
	private static int[] disc, low;
	private static boolean[] visited;

	public static boolean[] articulationPoints(int[][] graph){
		int NumberOfNodes = graph.length;

		time = -1;
		disc = new int[NumberOfNodes];
		low = new int[NumberOfNodes];
		visited = new boolean[NumberOfNodes];
		java.util.Arrays.fill(low, 2*NumberOfNodes);

		boolean[] isAP = new boolean[NumberOfNodes];
		for(int i = 0; i< NumberOfNodes; i++)if(!visited[i] && graph[i] != null)apUtil(graph, isAP, i, -1);
		return isAP;
	}

	private static void apUtil(int[][] g, boolean[] isAP, int u, int p){
		visited[u] = true;
		disc[u] = low[u] = ++time;

		int childCount = 0;
		for(int v:g[u]){
			if(!visited[v]){
				apUtil(g, isAP, v, u);

				low[u] = Math.min(low[u], low[v]);
				childCount++;

				if(p != -1 && low[v] >= disc[u])isAP[u] = true;
			}else if(v != p)low[u] = Math.min(low[u], disc[v]);
		}
		if(p == -1 && childCount > 1)isAP[u] = true;
	}
}
