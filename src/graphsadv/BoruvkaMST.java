package graphsadv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;


public class BoruvkaMST {
   
    Graph g;
    HashMap<Component,Edge<Integer>> res;
    List<Edge<Integer>> sorted;
    List<Edge<Integer>> ls=new LinkedList<>();
     public class EdgeComparator implements Comparator<Edge<Integer>>
    {

        @Override
        public int compare(Edge<Integer> o1, Edge<Integer> o2) {
            if(o1.getWeight()>=o2.getWeight())
                return 1;
            else
                return -1;
        }
    
    }
    
    BoruvkaMST(Graph gr)
    {
        g=gr;
        res=new LinkedHashMap<>();
        init();
    }
     class Component
    {
        Set<Vertex<Integer>> component_list;
        
        Component()
        {
            component_list=new LinkedHashSet<>();
        }
        
        void add(Vertex<Integer> v)
        {
            component_list.add(v);
        }
        
        
    }
     void init()
     {
         sorted=g.getAllEdges();
         sorted.sort(new EdgeComparator());
         List<Vertex<Integer>>all_vert=g.getAllValuesList();
         for(Vertex<Integer> vr:all_vert)
         {
              Component c=new Component();
              c.add(vr);
              res.put(c, findCheapest(vr));
         }
         clearsorted();
     }
     
     void clearsorted()
     {
         for(Edge tib:ls)
         {
             sorted.remove(tib);
         }
         ls.clear();
     }
     Edge findCheapest(Vertex v)
     {
         Edge cheap=sorted.get(0);
         for(Edge e:sorted)
         {
            if(e.getVertex1()==v||e.getVertex2()==v)
            {   
                cheap=e;
                ls.add(e);
                break;
            }
         }
         return cheap;
     }
     Edge findCheapestEdgeForComponent(Component c)
     {
         
         Edge cheap=sorted.get(0);
         for(Edge e:sorted)
         {
             if(c.component_list.contains(e.getVertex1())||c.component_list.contains(e.getVertex2()))
             {
                 cheap=e;
                 ls.add(e);
                 break;
             }
         }
         return cheap;
     }
    Component mergecomps(Component c1,Component c2)
    {
        for(Vertex<Integer> v:c2.component_list)
        {
            c1.add(v);
        }
        return c1;
    }
    
    Component makeComponent(Edge currEdge)
    {
    Component new_comp=new Component();
     new_comp.add(currEdge.getVertex1());
     new_comp.add(currEdge.getVertex2());
     return new_comp;
    }
    
    void performBoruvka()
    {
        Set<Edge<Integer>> real_ans=new LinkedHashSet();
       while(res.size()!=1)
       {
           HashMap<Component,Edge<Integer>> temp=new LinkedHashMap();
          
           for(Entry<Component,Edge<Integer>> es:res.entrySet())
           {
               boolean found=false;
               
               Edge currEdge=es.getValue();
               
               if(!temp.isEmpty())
               {
                   for(Entry<Component,Edge<Integer>> tes:temp.entrySet())
                   {
                       Component tcurrcomp=tes.getKey();
                       if(tcurrcomp.component_list.contains(currEdge.getVertex1())||tcurrcomp.component_list.contains(currEdge.getVertex2()))
                       {
                           tcurrcomp=mergecomps(tcurrcomp,makeComponent(currEdge));
                           temp.put(tcurrcomp, currEdge);
                           found=true;
                           break;
                       }
                   }
                       if(!found)
                       {
                           temp.put(makeComponent(currEdge), currEdge);
                       }
                   
               }
               else
               {
                   for(Entry<Component,Edge<Integer>> ej:res.entrySet())
                   {
                       temp.put(ej.getKey(),ej.getValue());
                       break;
                   }
               }    
               
               real_ans.add(currEdge);
           }
           res.clear();
           for(Entry<Component,Edge<Integer>> tes:temp.entrySet())
           {
               res.put(tes.getKey(), tes.getValue());
           }
           for(Entry<Component,Edge<Integer>> tes:res.entrySet())
           {
               res.put(tes.getKey(), findCheapestEdgeForComponent(tes.getKey()));
           }
           temp.clear();
           clearsorted();
       }
       for(Edge e:real_ans)
       {
           System.out.println(e);
       }
    }
}
