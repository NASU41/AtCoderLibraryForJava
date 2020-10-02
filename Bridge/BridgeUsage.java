class BridgeUsage{
	//Usage Example Bridge.bridges()
	public static void bridgesUsage(){
		int N = 6, E = 6;
		int[] from = new int[]{0,1,1,2,2,4};
		int[] to = new int[]{5,4,5,3,4,5};

		int[][][] graph = GraphBuilder.makeGraphWithEdgeInfo(N, E, from, to, true);
		boolean[] isBridge = Bridge.bridges(graph, E);

		for(int i = 0; i< E; i++){
			if(isBridge[i])
				System.out.println(from[i] + " - " + to[i] + " is a bridge");
		}
	}
	
	public static void main(String[] args){
		bridgesUsage();
	}
}
