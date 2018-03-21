package exc1.graphBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.StringComponentNameProvider;
import org.jgrapht.io.ComponentNameProvider;

public class Exporter {
    private Graph<Vertex, Edge> graph;

    public Exporter(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public void printToConsole() throws ExportException {
        toDotFormat(graph).exportGraph(graph, System.out);
    }

    public void printToFile(String path) throws ExportException, IOException {
        File file = new File(path);

        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        file.createNewFile();

        FileOutputStream out = new FileOutputStream(file);

        toDotFormat(graph).exportGraph(graph, out);
    }

    private DOTExporter<Vertex, Edge> toDotFormat(Graph<Vertex, Edge> graph) {
        return new DOTExporter<Vertex, Edge>(new StringComponentNameProvider<Vertex>(), null, new WeightProvider());
    }

    private class WeightProvider implements ComponentNameProvider<Edge> {

        @Override
        public String getName(Edge edge) {
            return "" + Double.valueOf(graph.getEdgeWeight(edge)).intValue();
        }

    }
}
