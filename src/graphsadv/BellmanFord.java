package graphsadv;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord {
    Graph<Integer> graph;
    Vertex<Integer> source;
    int src;
    Map<Vertex<Integer>,Node> map= new HashMap<>();
    private class Node{
        Vertex<Integer> elementNode;
        String status;
        double distance;
        Vertex<Integer> parentNode;

        Node(Vertex<Integer> v)
        {
            elementNode=v;
            status="u";
            distance=Double.MAX_VALUE;
            parentNode=null;
        }
    }
    BellmanFord(Graph<Integer> g,int sr)
    {
        this.graph = g;
        this.src=sr;
    }

}
