/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;

import java.util.HashMap;

/**
 *
 * @author Th3D4rKnight
 */
public class Disjointsets {
    
    HashMap<Integer,Node> map=new HashMap<>();
    class Node
    {
        private int data;
        private int rank;
        private Node parent;
    }
    void makeset(int data)
    {
        Node node1=new Node();
        node1.data=data;
        node1.rank=0;
        node1.parent=node1;
        map.put(data, node1);
    }
    int findset(int data1)
    {
        return findset(map.get(data1)).data;
    }
    Node findset(Node n)
    {
        Node parent=n;
        if(parent==n.parent)
        { 
            return parent;
        }
        n.parent=findset(n.parent);
        return n.parent;
    }
    void union(int id1,int id2)
    {
        Node n1=map.get(id1);
        Node n2=map.get(id2);
        
        Node parent1=n1.parent;
        Node parent2=n2.parent;
        
        if(parent1.data==parent2.data)
        {
            return;
        }
        
        if(parent1.rank>parent2.rank)
        {
            parent2.parent=parent1;
        }
        else if(parent1.data==parent2.data)
        {
            parent1.rank++;
            parent2.parent=parent1;
        }
        else
        {
            parent1.parent=parent2;
        }
    }
}
