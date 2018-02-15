/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Th3D4rKnight
 */
public class Algor<T> {
    
    public Stack topological_sort(Graph<T> grph)
    {
        Stack<Vertex<T>> sorted=new Stack();
        Set<Vertex<T>> visited=new HashSet<>();
        for (Vertex<T> vx:grph.getAllVertex())
            {
                if(!visited.contains(vx))
                {
                    topological_sort_helper(vx,sorted,visited);
                }
            }
        return sorted;
        }
    private void topological_sort_helper(Vertex<T> vx,Stack<Vertex<T>> sorted,Set<Vertex<T>> visited)
    {
          visited.add(vx);
          for(Vertex<T> vc:vx.getAdjacentVertexes())
          {
              if(!visited.contains(vc))
              {
                  topological_sort_helper(vc, sorted, visited);
              }
          }
          sorted.push(vx);
    }
    public void longest_path_list(int x,Graph g)
    {
        Stack <Vertex<Integer>> st=topological_sort(g);
        Stack <Vertex<Integer>> st_print=topological_sort(g);
        while(!st_print.isEmpty())
        {
            System.out.print(st_print.pop()+" ");
        }
        System.out.println("");
        int dist[]=new int[g.getAllVertex().size()];
        Arrays.fill(dist, Integer.MIN_VALUE);
        int i=0;
        while(st.peek().getId()!=x)
        {
            st.pop();
            i++;
        }
        dist[i]=0;
        Vertex source=st.pop();
        Vertex prev=source;
        while(!st.isEmpty())
        {
            Vertex curr=st.pop();
            int indir=dist[i++]+g.get_curr_weight(prev, curr);
            int dir=g.get_curr_weight(source, curr);
            if(indir>dir)
                dist[i]=indir;
            else
                dist[i]=dir;
            
            prev=curr;
        }
        for(Integer m:dist)
        {
            System.out.print(m+" ");
        }
    }
}
