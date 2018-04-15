package exc4;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.FordFulkerson;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.io.Reader;

public class Exercise4c {
    
    public static void main(String[] args) throws IOException, ExportException {
        GraphDescription CirculationDemandsDescription = Parser.parseDescription(new Reader().readFile("input/CirculationDemands.txt"));
        Graph<Vertex, Edge> CircularDemGraph = new GraphBuilder(CirculationDemandsDescription).buildDirectedWeightedMultigraph();

        FordFulkerson fordFulkersonAlgorithm = new FordFulkerson(CircularDemGraph);
        
        if(fordFulkersonAlgorithm.solvesCircularDemand())
            System.out.println("Circulation Demand possible");
        else 
            System.out.println("Circulation Demand NOT possible");
    }


}
