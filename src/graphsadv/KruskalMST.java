/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Th3D4rKnight
 */
public class KruskalMST<T> {
    Graph g;
    KruskalMST(Graph<T> grp)
    {
     this.g=grp;   
    }
    public class EdgeComparator implements Comparator<Edge<Integer>>
    {

        
        public int compare(Edge<Integer> o1, Edge<Integer> o2) {
            if(o1.getWeight()>=o2.getWeight())
                return 1;
            else
                return -1;
        }
    
    }
    void performKruskal()
    {
        List<Edge<Integer>> all_edges=g.getAllEdges();
        Collections.sort(all_edges,new EdgeComparator());
        
        Disjointsets set=new Disjointsets();
        
       
        
        
        List<Edge<Integer>> res=new ArrayList<>();
        List<Vertex<Integer>> all_vx=g.getAllValuesList();
        for(Vertex<Integer> vx:all_vx)
        {
            set.makeset((int)vx.getId());
        }
        int i=0;
        for(Edge<Integer> e:all_edges)
        {
            if(set.findset((int)e.getVertex1().getId())!=set.findset((int)e.getVertex2().getId()))
            {
                res.add(e);
                i++;
                
                set.union((int)e.getVertex1().getId(),(int)e.getVertex2().getId());
            }
            if(i==all_vx.size()-1)
                break;
        }
        
        for(Edge e:res)
        {
            System.out.println(e.getVertex1().getId()+"--"+e.getVertex2().getId()+"==weight=="+e.getWeight());
        }
    }
}
