package exc1;

import exc1.graphBuilder.Parser;

public class Exercise1d {
	
	final static String GRAPH_DESCRIPTION = "" + "# 9 Knotendefinitionen, A bis I\n" + "knoten A 1\n" + "knoten B 2 \n"
			+ "knoten C 3\n" + "knoten D 4\n" + "knoten E 5\n" + "knoten F 6\n" + "knoten G 7\n" + "knoten H 8\n"
			+ "knoten I 9\n" + "\n" + "# Kantendefinitionen \n" + "kante A B 2\n" + "kante A F 9\n" + "kante A G 15\n"
			+ "kante B C 4\n" + "kante B G 6\n" + "kante C D 2\n" + "kante C I 15\n" + "kante D E 1\n" + "kante D I 1\n"
			+ "kante E F 6\n" + "kante E H 3\n" + "kante F H 11\n" + "kante G H 15\n" + "kante G I 2\n" + "kante H I 4";

	public static void main(String[] args) {

		Parser graphParser = new Parser();
		graphParser.createDirectedWeightedGraph(GRAPH_DESCRIPTION);

	}
}
