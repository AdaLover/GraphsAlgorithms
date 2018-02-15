package graphsadv;

import java.util.*;

public class BFS {
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
    BFS(Graph<Integer> g,int sr)
    {
        this.graph = g;
        this.src=sr;
    }

    void applyBFS()
    {
        List<Vertex<Integer>>vertexList =graph.getAllValuesList();
        for(Vertex<Integer> v :vertexList)
        {
            if(v.id==src)
            {
             source=v;
            }
            Node vert = new Node(v);
            map.put(v,vert);
        }
        Queue<Vertex<Integer>> q=new LinkedList<>();
        q.add(source);
        Node n = map.get(source);
        n.status="p";
        n.distance=0;
        map.put(source,n);
        while(!q.isEmpty())
        {
            Vertex<Integer> curr = q.poll();
            List<Vertex<Integer>> currList  = curr.getAdjacentVertexes();
            if(!currList.isEmpty()){
                for(Vertex<Integer> v:currList)
                {
                    if(map.get(v).status.equals("u"))
                    {
                        Node nm =map.get(v);
                        nm.status="p";
                        nm.parentNode=curr;
                        nm.distance=map.get(curr).distance+1;
                        map.put(v,nm);
                        q.add(v);
                    }
                }
            }
            System.out.println(curr.id);
            map.get(curr).status="v";
        }
    }
}