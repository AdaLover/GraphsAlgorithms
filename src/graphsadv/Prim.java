/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Th3D4rKnight
 */
public class Prim {
    int src;
    Graph gr;
    HashMap<Vertex<Integer>,Edge<Integer>> vertEdge=new HashMap<>();
    HashMap<Vertex<Integer>,Integer> vertWeight=new HashMap<>();
    
    Prim(Graph g,int s)
    {
        gr=g;
        src=s;
    }
    void inintialize_vertWeight(int src)
    {
        List<Vertex<Integer>> ls=gr.getAllValuesList();
        for(Vertex<Integer> vx:ls)
        {
            if((int)vx.getId()==src)
                vertWeight.put(vx, 0);
            else
                vertWeight.put(vx, Integer.MAX_VALUE);
        }
    }
    Vertex<Integer> extractMinFromMap()
    {
        Vertex<Integer> vx=null;
        int min=Integer.MAX_VALUE;
        for(Map.Entry<Vertex<Integer>,Integer> c:vertWeight.entrySet())
        {
            if(c.getValue()<min)
            {
                vx=c.getKey();
                min=c.getValue();
            }
        }
        vertWeight.remove(vx);
        return vx;
    }
    void perform_Prim()
    {
        inintialize_vertWeight(src);
        while(!vertWeight.isEmpty())
        {
            performPrimUtil();
        }
        for (Map.Entry<Vertex<Integer>, Edge<Integer>> entry : vertEdge.entrySet()) {
           
            System.out.println(entry.getValue());
            
        }
    }
    private void performPrimUtil() 
    {
        Vertex<Integer> curr=extractMinFromMap();
        List<Vertex<Integer>> adj=curr.getAdjacentVertexes();
        
        for(Vertex<Integer> vr:adj)
        {
            Edge<Integer> currEdge=null;
            List<Edge<Integer>> allEdges=gr.getAllEdges();
            for(Edge<Integer> e:allEdges)
            {
                if(e.getVertex1()==curr&&e.getVertex2()==vr||e.getVertex1()==vr&&e.getVertex2()==curr)
                {currEdge=e; 
                    break;
                } 
            }
           
           if(vertWeight.containsKey(vr)&&vertWeight.get(vr)>currEdge.getWeight())
           {
               vertWeight.put(vr, currEdge.getWeight());
               vertEdge.put(vr,currEdge);
           }
           
        }
    }
}
