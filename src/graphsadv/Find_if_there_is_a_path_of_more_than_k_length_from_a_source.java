/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;

import java.util.List;

/**
 *
 * @author Th3D4rKnight
 */
public class Find_if_there_is_a_path_of_more_than_k_length_from_a_source {

    Graph g;
    public Find_if_there_is_a_path_of_more_than_k_length_from_a_source(Graph g) 
    {
        this.g=g;
    }
    public void doesPathExist(int src,int k)
    {
        int visited[]=new int[g.getAllValuesList().size()];
        visited[src]=1;

        System.out.println(pathExistUtil(g.getVertex(src),k,visited));
    }
    private boolean pathExistUtil(Vertex v,int k,int[] visited){
        if(k<0)return true;
        List<Vertex<Integer>> all_adj=v.getAdjacentVertexes();
        for(Vertex vx:all_adj)
        {
                if(visited[(int)vx.getId()]!=1)
                {
                    int w=g.get_curr_weight(v, vx);
                    if(w>=k)return true;
                    visited[(int)vx.getId()]=1;
                    if(pathExistUtil(vx,k-w,visited))return true;
                    else visited[(int)vx.getId()]=0;
                }
        }
        return false;
    }
}
