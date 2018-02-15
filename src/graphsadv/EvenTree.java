/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;

import java.util.Arrays;

/**
 *
 * @author Th3D4rKnight
 */
public class EvenTree {
            Graph g;
            int src;
            int ans[];
            EvenTree(Graph gr,int s)
            {
                g=gr;
                src=s;
                ans=new int[g.getAllValuesList().size()+1];
            }
            void findMaxForest()
            {
                int count=0;
                int visited[]=new int[g.getAllValuesList().size()+1];Arrays.fill (ans, 1);
                
                findMaxForestUtil(g.getVertex((long)src),visited);
                
                for(int i=2;i<ans.length;i++)
                {
                    
                    if(ans[i]%2==0)count++;
                }
                System.out.println(count);
            }
            int findMaxForestUtil(Vertex<Integer> v,int visited[])            
            {
                visited[(int)v.getId()]=1;
                if(v.getAdjacentVertexes().size()==1)return 1;
                for(Vertex vx:v.getAdjacentVertexes())
                {
                    if(visited[(int)vx.getId()]==0)
                    ans[(int)v.getId()]+=findMaxForestUtil(vx,visited);
                }
                return 1;
            }
}
