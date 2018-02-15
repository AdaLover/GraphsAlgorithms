/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsadv;

/**
 *
 * @author Th3D4rKnight
 */
import graphsadv.Algor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
public class GraphsAdv {

    /**1 2 20
1 3 50
1 4 70
1 5 90
2 3 30
3 4 40
4 5 60
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Graph<Integer> g=new Graph<>(true);
     
    g.addEdge(0, 7, 8);
    g.addEdge(0, 1, 4);
    g.addEdge(1, 2, 8);
    g.addEdge(1, 7, 11);
    g.addEdge(2, 8, 2);
    g.addEdge(2, 3, 7);
    g.addEdge(2, 5, 4);
    g.addEdge(3, 4, 9);
    g.addEdge(3, 5, 14);
    g.addEdge(4, 5, 10);
    g.addEdge(5, 6, 2);
    g.addEdge(6, 8, 6);
    g.addEdge(6, 7, 1);
    g.addEdge(7, 8, 7);

    BellmanFord<Integer> bellmanFord = new BellmanFord<>(g,0);
    bellmanFord.applyBellmanFord();
}
}
