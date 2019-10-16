package Redes;

import java.util.ArrayList;
import java.util.Hashtable;

public class Node {
    private String name;
    private Hashtable<String, Node> edges;
    public Node(String name){
        this.name = name;
        this.edges = new Hashtable<String, Node>();
    }

    public void addEdge(Node node){
        if(!edges.containsKey(node.getName()))
            edges.put(node.getName(), node);
    }

    public void deleteEdge(Node node){
        if(edges.containsKey(node.getName()))
            edges.remove(node.getName());
    }
    public boolean isEmpty(){
        return edges.size() == 0;
    }

    public boolean hasDestiny(Node node){
        return edges.containsKey(node.getName());
    }
    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }

    public ArrayList<Node> getdestinies(){
        return new ArrayList<Node>(edges.values());
    }
}
