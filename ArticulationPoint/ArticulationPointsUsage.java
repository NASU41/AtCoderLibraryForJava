class ArticulationPointsUsage{
	//Usage example ArticulationPoint.articulationPoints()
	public static void articulationPointsUsage(){
		int N = 6, E = 6;
		int[] from = new int[]{0,1,1,2,2,4};
		int[] to = new int[]{5,4,5,3,4,5};

		int[][] graph = GraphBuilder.makeGraph(N, E, from, to, true);
		boolean[] articulationPoint = ArticulationPoints.articulationPoints(graph);

		for(int i = 0; i< N; i++){
			if(articulationPoint[i])
				System.out.println(i+" is an articulation point");
		}
	}
	
	public static void main(String[] args){
		articulationPointsUsage();
	}
}
