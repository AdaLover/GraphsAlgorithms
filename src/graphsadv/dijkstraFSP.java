package graphsadv;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class dijkstraFSP{

    Graph g;
    int src=0;
    int dest=0;
    Vertex<Integer> destV;
    Vertex<Integer> srcV ;
    public dijkstraFSP(Graph g,int src,int dest) {
        this.src=src;
        this.dest=dest;
        this.g=g;
    }

    public void performDijkstra(){
        pathTrace pTR=dijkstraHelper();
        if(pTR!=null)
        {
            System.out.println(pTR.distanceTotal);
        }
    }
    private pathTrace dijkstraHelper(){
        List<Vertex<Integer>> ls= g.getAllValuesList();

        for(Vertex<Integer> v:ls)
        {
            if(this.src==v.id)
            {
                srcV=v;
            }
            else if(this.dest==v.id)
            {
                destV=v;
            }
        }

        Vertex<Integer> i=srcV;
        pathTrace pt=new pathTrace();
        boolean found=false;
        while(i.getEdges().size()!=0)
        {

            List<Edge<Integer>> ed= i.getEdges();
            int smallestWeight=Integer.MAX_VALUE;
            for(Edge<Integer> m:ed)
            {
                if(m.getWeight()<smallestWeight)
                {
                    smallestWeight=m.getWeight();
                    i=m.getVertex2();
                }
            }
            pt.distanceTotal+=smallestWeight;
            pt.q.add(i);
              if(i.id==destV.id)
              {
                  found=true;
                  break;
              }
        }
        if(found==true)
        {
            return pt;
        }
        else
            return null;
    }
    private class pathTrace{
        Queue<Vertex<Integer>> q=new LinkedList<>();
        int distanceTotal;
    }
}
