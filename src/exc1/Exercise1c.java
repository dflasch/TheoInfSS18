package exc1;

import java.io.IOException;

import org.jgrapht.io.ExportException;

import lib.Exporter;
import lib.Parser;
import lib.Reader;

public class Exercise1c {

    public static void main(String[] args) throws IOException, ExportException {
        Reader fileReader = new Reader();
        Parser graphParser = new Parser();

        String graphDescription = fileReader.readFile("input/Dijkstra.txt");

        new Exporter(graphParser.createDirectedWeightedGraph(graphDescription)).printToConsole();

    }

}
