class GraphBuilderUsage{
	//Usage Exmaple
	public static void makeGraphUsage(){
		int N = 6, E = 8;
		int[] from = new int[]{0,0,0,1,1,2,2,4};
		int[] to = new int[]{2,4,5,4,5,3,4,5};

		int[][] graph = GraphBuilder.makeGraph(N, E, from, to, true);
		for(int i = 0; i< N; i++){
			System.out.print(i+": ");
			for(int j:graph[i])System.out.print(j+" ");
			System.out.println();
		}
	}

	//Usage Example
	public static void makeGraphWithEdgeInfoUsage(){
		int N = 6, E = 8;
		int[] from = new int[]{0,0,0,1,1,2,2,4};
		int[] to = new int[]{2,4,5,4,5,3,4,5};

		int[][][] graph = GraphBuilder.makeGraphWithEdgeInfo(N, E, from, to, true);
		for(int i = 0; i< N; i++){
			System.out.print(i+": ");
			for(int[] j:graph[i])System.out.print("["+j[0]+", "+j[1]+", "+j[2]+"],");
			System.out.println();
		}
	}

	public static void main(String[] args){
		makeGraphUsage();
		makeGraphWithEdgeInfoUsage();
	}
}
