package graphsadv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BellmanFord<T> {
    Graph<T> graph;
    Vertex<T> source;
    int src;
    Map<Vertex<T>,Node> map= new HashMap<>();
    private class Node{
        double distance;
        Vertex<T> parentNode;
        Node()
        {
            distance=Double.MAX_VALUE;
            parentNode=null;
        }
        Node(Vertex<T> v,double distance)
        {
            this.distance=distance;
            this.parentNode=v;
        }
    }
    BellmanFord(Graph<T> g,int sr)
    {
        this.graph = g;
        this.src=sr;
    }

    void applyBellmanFord(){
        List<Vertex<T>> vertexList= graph.getAllValuesList();
        List<Edge<T>> edgesList = graph.getAllEdges();
        initializeList(vertexList);
        for(int i=0;i<vertexList.size();i++)
        {
            for(Edge<T> edge:edgesList)
            {
                relax(edge);
            }
        }
        print();
    }

    private void print() {
        for(Map.Entry<Vertex<T>,Node> en:map.entrySet())
        {
            System.out.println("distance ="+en.getValue().distance+" to "+en.getKey());
        }
    }

    private void initializeList(List<Vertex<T>> vertexList) {
        for(Vertex<T> v:vertexList)
        {
            if(v.id==src)
            {
                map.put(v,new Node(null,0));
            }
        else
            map.put(v,new Node());
        }
    }

    private void relax(Edge<T> edge) {
        if(map.get(edge.getVertex2()).distance>map.get(edge.getVertex1()).distance+edge.getWeight())
        {
            map.put(edge.getVertex2(),new Node(edge.getVertex1(),map.get(edge.getVertex1()).distance+edge.getWeight()));
        }
    }

}
